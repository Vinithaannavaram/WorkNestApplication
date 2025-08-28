package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Comment;
import com.example.model.Task;
import com.example.model.User;
import com.example.service.CommentService;
import com.example.service.TaskService;
import com.example.service.UserService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
  

    // ✅ ADMIN DASHBOARD: Show all tasks
    @RequestMapping("/adminDashboard")
    public ModelAndView showAdminDashboard(
            @RequestParam(value = "showTasks", required = false) Boolean showTasks,
            HttpSession session) {

        User user = (User) session.getAttribute("currentUser");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mv = new ModelAndView("adminDashboard");

        if (Boolean.TRUE.equals(showTasks)) {
            List<Task> tasks = taskService.getAllTasks();
            mv.addObject("tasks", tasks);
            mv.addObject("showTasks", true);
        }

        return mv;
    }
    // ✅ Show Task Allocation Form (Admin)
    @RequestMapping("/createTask")
    public ModelAndView showCreateTaskForm(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || !"ADMIN".equals(user.getRole())) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mv = new ModelAndView("taskForm");
        mv.addObject("task", new Task());
        mv.addObject("mode", "create");
        mv.addObject("userList", userService.getAllUsers());
        return mv;
    }

    // ✅ Save Task (Admin assigns to selected user)
    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task,
                           @RequestParam("assignedToId") int assignedToId,
                           HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/login";
        }

        User assignedUser = userService.getUserById(assignedToId);
        task.setAssignedTo(assignedUser);
        task.setStatus("Pending");
        taskService.saveTask(task);
        return "redirect:/adminDashboard";
    }

    // ✅ View Task
    @RequestMapping("/taskView")
    public ModelAndView viewTask(@RequestParam int id) {
        Task task = taskService.getTaskById(id);
        List<Comment> comments = commentService.getCommentsByTaskId(id);

        ModelAndView mv = new ModelAndView("taskForm");
        mv.addObject("task", task);
        mv.addObject("comments", comments);
        mv.addObject("mode", "view");
        return mv;
    }

    // ✅ Edit Task
    @RequestMapping("/editTask")
    public ModelAndView editTask(@RequestParam int id) {
        Task task = taskService.getTaskById(id);
        ModelAndView mv = new ModelAndView("taskForm");
        mv.addObject("task", task);
        mv.addObject("mode", "edit");
        mv.addObject("userList", userService.getAllUsers());
        return mv;
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task,
                             @RequestParam("assignedToId") int assignedToId) {
        User assignedUser = userService.getUserById(assignedToId);
        task.setAssignedTo(assignedUser);
        taskService.updateTask(task);
        return "redirect:/adminDashboard";
    }
    // ✅ Edit Task (User)
    @RequestMapping("/editUserTask")
    public ModelAndView editUserTask(@RequestParam int id, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        Task task = taskService.getTaskById(id);

        if (user == null || task == null || task.getAssignedTo().getId() != user.getId()) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mv = new ModelAndView("taskForm");
        mv.addObject("task", task);
        mv.addObject("mode", "editUser");
        return mv;
    }

    // ✅ Update Task (User)
    @PostMapping("/updateUserTask")
    public String updateUserTask(@ModelAttribute Task task, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null || task.getAssignedTo().getId() != user.getId()) {
            return "redirect:/login";
        }

        taskService.updateTask(task);
        return "redirect:/userDashboard";
    }

    // ✅ Delete Task
    @RequestMapping("/deleteTask")
    public String deleteTask(@RequestParam int id) {
        taskService.deleteTask(id);
        return "redirect:/adminDashboard";
    }

    // ✅ Update Status
    @RequestMapping("/updateStatus")
    public String updateStatus(@RequestParam int id, @RequestParam String status) {
        taskService.updateStatus(id, status);
        return "redirect:/adminDashboard";
    }

    // ✅ Assign Task to User (optional if separate logic needed)
    @PostMapping("/assignTask")
    public String assignTask(@RequestParam int taskId,
                             @RequestParam int userId,
                             HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/login";
        }

        taskService.assignTaskToUser(taskId, userId);
        return "redirect:/adminDashboard";
    }

    // ✅ User Dashboard (unchanged)
    @RequestMapping("/userDashboard")
    public ModelAndView showUserDashboard(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) return new ModelAndView("redirect:/login");

        List<Task> tasks = taskService.getTasksByUserId(user.getId());
        ModelAndView mv = new ModelAndView("userDashboard");
        mv.addObject("tasks", tasks);
        return mv;
    }
    @PostMapping("/addComment")
    public String addComment(@RequestParam("taskId") Long taskId,
                             @RequestParam("commentText") String commentText,
                             HttpSession session) {
        // ✅ Get current user from session
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            // Redirect to login if user is not logged in
            return "redirect:/login";
        }


        // ✅ Create new comment object
        Comment comment = new Comment();
        comment.setTaskId(taskId);
        comment.setText(commentText);
        comment.setAuthorName(currentUser.getName()); // Optional
        comment.setTimestamp(LocalDateTime.now());    // Optional

        // ✅ Save comment using service or DAO
        commentService.saveComment(comment); // Or commentDAO.save(comment)

        // ✅ Redirect back to task view
        return "redirect:/taskView?id=" + taskId;
    }
}
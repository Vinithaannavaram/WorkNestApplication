package com.example.controller;

import com.example.model.Comment;
import com.example.model.Task;
import com.example.model.User;
import com.example.service.CommentService;
import com.example.service.TaskService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller

public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	
    @RequestMapping("/")
    public String redirectToLogin() {
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUser(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String password,
                                     @RequestParam String role) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userService.registerUser(user);
        return new ModelAndView("login", "message", "Registration successful!");
    }
    @RequestMapping("/login")
    public ModelAndView showLoginForm() {
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView loginUser(@RequestParam String email,
                                  @RequestParam String password,
                                  HttpSession session) {
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("currentUser", user);
            if ("ADMIN".equals(user.getRole())) {
                return new ModelAndView("redirect:/adminDashboard");
            } else {
                return new ModelAndView("redirect:/userDashboard");
            }
        } else {
            return new ModelAndView("login", "error", "Invalid credentials");
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // ✅ Clears the session
        return "redirect:/login"; // ✅ Redirects to login page
    }

	/*
	 * @RequestMapping("/userDashboard") public ModelAndView
	 * showDashboard(HttpSession session) { int userId = (int)
	 * session.getAttribute("userId"); List<Task> tasks =
	 * taskService.getTasksByUserId(userId);
	 * 
	 * // For each task, fetch its comments Map<Integer, List<Comment>>
	 * taskCommentsMap = new HashMap<>(); for (Task task : tasks) { List<Comment>
	 * comments = commentService.getCommentsByTaskId(task.getId());
	 * taskCommentsMap.put(task.getId(), comments); }
	 * 
	 * ModelAndView mv = new ModelAndView("userDashboard"); mv.addObject("tasks",
	 * tasks); mv.addObject("taskCommentsMap", taskCommentsMap); return mv; }
	 */
}
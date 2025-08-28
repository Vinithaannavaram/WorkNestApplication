package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class AdminController {
 
	@Autowired
	private UserService userService;

	/*
	 * @RequestMapping("/adminDashboard") public ModelAndView
	 * showAdminDashboard(HttpSession session) { User currentUser = (User)
	 * session.getAttribute("currentUser"); if (currentUser == null ||
	 * !"ADMIN".equals(currentUser.getRole())) { return new ModelAndView("login",
	 * "error", "Access denied"); }
	 * 
	 * List<User> users = userService.getAllUsers(); ModelAndView mv = new
	 * ModelAndView("adminDashboard"); mv.addObject("users", users); return mv; }
	 */
    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("currentUser", user); // 👈 This is what AdminController expects
            if ("ADMIN".equals(user.getRole())) {
                return new ModelAndView("redirect:/adminDashboard");
            } else {
                return new ModelAndView("userDashboard");
            }
        } else {
            return new ModelAndView("login", "error", "Invalid credentials");
        }
    }
}

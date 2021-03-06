package com.ioproj.niekopce;

import com.ioproj.niekopce.Model.UserAccount;
import com.ioproj.niekopce.Security.CustomizeLogoutSuccessHandler;
import com.ioproj.niekopce.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/main")
@AllArgsConstructor
public class MainController {

    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    private UserService userService;

    @GetMapping("/mainPage")
    public String getMainPage(Model model, Principal principal) {
        String name;
        try {
            name = principal.getName();
            if (userService.isAdmin(name)) {
                return "redirect:/admin/adminPage";
            } else {
                return "redirect:/user/standard";
            }
        } catch (NullPointerException e) {
            name = "niezalogowany";
        }
        model.addAttribute("username", name);
        return "main/mainPage";
    }

    @GetMapping("/returnMain")
    public String returnToMainPage(Model model, Principal principal) {
        String name;
        try {
            name = principal.getName();
        } catch (NullPointerException e) {
            name = "niezalogowany";
        }
        model.addAttribute("username", name);
        return "main/mainPage";
    }


    @GetMapping("/logout")
    public String logoutDo(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        String name;
        try {
            name = principal.getName();
        } catch (NullPointerException e) {
            name = "niezalogowany";
        }
        // TODO tu wywołąć metodę JS która pobierze stronę na nowo
        return "redirect:/main/mainPage";
    }
}

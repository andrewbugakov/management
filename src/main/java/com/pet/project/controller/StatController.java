package com.pet.project.controller;

import com.pet.project.model.User;
import com.pet.project.service.StatService;
import com.pet.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StatController {
    @Autowired
    StatService statService;
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/lates"}, method = RequestMethod.GET)
    public String getLates(ModelMap model) {
        User user = userService.findBySSO(getPrincipal());
        List<Map<String, String>> lates = statService.getLates(user.getId());
        model.addAttribute("lates", lates);
        List<Map<String, String>> earlies = statService.getEarlies(user.getId());
        model.addAttribute("earlies", earlies);
        model.addAttribute("days", user.getDays());
        model.addAttribute("loggedinuser", getPrincipal());
        return "lates";
    }

    @RequestMapping(value = {"/efficient"}, method = RequestMethod.GET)
    public String getEfficient(ModelMap model) {
        System.out.println("эфф");
        User user = userService.findBySSO(getPrincipal());
        List<Map<String, String>> efs = statService.getEfficient(user.getId());

        System.out.println("efs " + efs.size());
        model.addAttribute("days", userService.getDays(getPrincipal()));
        model.addAttribute("efs", efs);
        model.addAttribute("loggedinuser", getPrincipal());
        return "efficients";
    }

    @RequestMapping(value = {"/tasks"}, method = RequestMethod.GET)
    public String getTasks(ModelMap model) {
        User user = userService.findBySSO(getPrincipal());
        List<Map<String, String>> efs = statService.getEfficient(user.getId());
        System.out.println("efs " + efs.size());
        model.addAttribute("efs", efs);
        model.addAttribute("loggedinuser", getPrincipal());
        return "tasks";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


}

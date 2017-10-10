package com.pet.project.controller;

import com.pet.project.converter.UserConverter;
import com.pet.project.model.User;
import com.pet.project.service.StatService;
import com.pet.project.service.UserService;
import com.pet.project.service.meeting.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OrderController {
    @Autowired
    UserService userService;
    @Autowired
    MeetingService meetingService;
    @Autowired
    UserConverter userConverter;
    @Autowired
    StatService statService;
    @RequestMapping(value = {  "/sub-orders" }, method = RequestMethod.GET)
    public String getSubOrders(ModelMap model) {
        model.addAttribute("subordinates",userService.findSubordinates(getPrincipal()));
        model.addAttribute("loggedinuser", getPrincipal());
        return "order_for_manager";
    }
    @RequestMapping(value = {  "/sub-orders/{id}/{type}" }, method = RequestMethod.GET)
    public String postSubOrders(@PathVariable int id, @PathVariable String type, ModelMap model) {
        User user=userService.findById(id);
        if(user!=null) {
            List<Map<String, String>> lates = statService.getLates(user.getId());
            List<Map<String, String>> earlies = statService.getEarlies(user.getId());
            List<Map<String, String>> efs = statService.getEfficient(user.getId());

            switch (type) {
                case "all":
                    model.addAttribute("lates", lates);
                    model.addAttribute("earlies", earlies);
                    model.addAttribute("efs", efs);

                break;
                case "opozd":
                    model.addAttribute("lates", lates);
                    break;
                case "early":
                    model.addAttribute("earlies", earlies);
                break;
                case "efficient":
                    model.addAttribute("efs", efs);

                break;
                default:
                    break;
            }
        }
        model.addAttribute("days", user.getDays());
        model.addAttribute("loggedinuser", getPrincipal());
        return "orders";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


}

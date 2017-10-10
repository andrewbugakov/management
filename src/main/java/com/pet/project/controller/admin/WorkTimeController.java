package com.pet.project.controller.admin;


import com.pet.project.model.User;
import com.pet.project.model.UserProfile;
import com.pet.project.service.UserProfileService;
import com.pet.project.service.UserService;
import com.pet.project.service.time.TimeService;
import com.pet.project.model.time.WorkingTime;
import com.pet.project.service.office_structure.BranchOfficeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("roles")
public class WorkTimeController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    BranchOfficeService branchOfficeService;
    @Autowired
    TimeService timeService;
    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = { "/saveworktime" }, method = RequestMethod.GET)
    public JSONObject ajaxSaveWorkTime(@RequestParam("opozd") int opozd,
                                       @RequestParam("zaversh") int zaversh,
                                       @RequestParam("pereriv") int pereriv,
                                       @RequestParam("kolvorabdnei") int kolvorabdnei,
                                       @RequestParam("edVremya") String edVremya,
                                       HttpServletResponse response) throws IOException {
        User user=userService.findBySSO(getPrincipal());
        WorkingTime workingTime=timeService.getTimeModel();
        workingTime.setOpazdanie(opozd);
        workingTime.setZavershenie(zaversh);
        workingTime.setPereriv(pereriv);
        workingTime.setColvo_dnei(kolvorabdnei);
        workingTime.setEdVremya(edVremya);
        timeService.save(workingTime);
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();
        json.put("status","ok");
        out.print(json.toString());
        out.close();
        return json;
    }

    @RequestMapping(value = { "/worktime" }, method = RequestMethod.GET)
    public String getWork(ModelMap model) {
        model.addAttribute("workingtime",timeService.getTimeModel());
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/worktime";
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

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

}

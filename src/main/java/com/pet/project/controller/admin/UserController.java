package com.pet.project.controller.admin;


import com.pet.project.model.User;
import com.pet.project.model.UserProfile;
import com.pet.project.model.meeting.Meeting;
import com.pet.project.service.UserProfileService;
import com.pet.project.service.UserService;
import com.pet.project.service.office_structure.DepartamentService;
import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.model.office_structure.Department;
import com.pet.project.service.office_structure.BranchOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"roles","depts","branches","meetings"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    DepartamentService departamentService;
    @Autowired
    BranchOfficeService branchOfficeService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = { "/","/list" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/userslist";
    }
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
    public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {
        System.out.println(user.getUserProfiles().size());
        if (result.hasErrors()) {
            return "admin/registration";
        }
        userService.updateUser(user);
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/userslist";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/admin/list";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        User user = userService.findBySSO(ssoId);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/registration";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("depts",departamentService.findAll());
        model.addAttribute("branches",branchOfficeService.findAll());
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {
//        System.out.println(user.getUserProfiles().size());
        if (result.hasErrors()) {
            return "admin/registration";
        }

        System.out.println("Dept where play");
        System.out.println(user.getDepartment()==null?0:user.getDepartment().getTitle());
        System.out.println("Dept where head");
        System.out.println("firstname "+user.getFirstName());
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "admin/registration";
        }
        System.out.println("user profiles "+user.getUserProfiles().size());
        userService.saveUser(user);


        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "admin/userslist";
    }
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
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

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
    @ModelAttribute("depts")
    public List<Department> initializeDepartments() {
        return departamentService.findAll();
    }
    @ModelAttribute("branches")
    public List<BranchOffice> initializeBranches() {
        return branchOfficeService.findAll();
    }
    @ModelAttribute("meetings")
    public List<Meeting> initializeEvents() {
        return userService.getAllMeetings(getPrincipal());
    }
}

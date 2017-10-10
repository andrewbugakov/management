package com.pet.project.controller.admin;


import com.pet.project.model.UserProfile;
import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.service.UserProfileService;
import com.pet.project.service.UserService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("roles")
public class BranchOfficeController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    BranchOfficeService branchOfficeService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = { "/branches" }, method = RequestMethod.GET)
    public String listBranches(ModelMap model) {
        List<BranchOffice> branches=branchOfficeService.findAll();
        model.addAttribute("branches", branches);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/branchlist";
    }
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-branch-{id}" }, method = RequestMethod.POST)
    public String updateDept(@Valid BranchOffice branchOffice, BindingResult result,
                             ModelMap model, @PathVariable long id) {
        //// TODO: 20.05.2016
        if (result.hasErrors()) {
            return "admin/regbranch";
        }
        branchOfficeService.update(branchOffice);
//        userService.updateUser(user);
        model.addAttribute("success", "Офис " + branchOffice.getName_office() + " обновлен удачно");
        model.addAttribute("loggedinuser", getPrincipal());
        return "redirect:/admin/branches";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = { "/delete-branch-{id}" }, method = RequestMethod.GET)
    public String deleteDept(@PathVariable int id) {
        branchOfficeService.delete(id);
        return "redirect:/admin/branches";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-branch-{id}" }, method = RequestMethod.GET)
    public String editDept(@PathVariable int id, ModelMap model) {
        BranchOffice branchOffice=branchOfficeService.findById(id);
        model.addAttribute("branch", branchOffice);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/regbranch";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newbranch" }, method = RequestMethod.GET)
    public String newDept(ModelMap model) {
        BranchOffice branchOffice=new BranchOffice();
        model.addAttribute("branch",branchOffice);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/regbranch";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newbranch" }, method = RequestMethod.POST)
    public String saveUser(@Valid BranchOffice branchOffice, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "admin/regbranch";
        }

        branchOfficeService.save(branchOffice);
//        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
//            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//            result.addError(ssoError);
//            return "registration";
//        }
//
//        userService.saveUser(user);
//
        List<BranchOffice> branches=branchOfficeService.findAll();
        model.addAttribute("branches", branches);
        model.addAttribute("success", "Филиал " + branchOffice.getName_office() + " создан успешно");
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/branchlist";
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

}

package com.pet.project.controller.admin;


import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.model.office_structure.Department;
import com.pet.project.service.UserProfileService;
import com.pet.project.service.UserService;
import com.pet.project.service.office_structure.BranchOfficeService;
import com.pet.project.service.office_structure.DepartamentService;
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
@SessionAttributes("branches")
public class DeptController {

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

    @RequestMapping(value = { "/depts" }, method = RequestMethod.GET)
    public String listDepts(ModelMap model) {
        //// TODO: 20.05.2016  deptsservice
        List<Department> depts=departamentService.findAll();
//        model.addAttribute("success",success);
        model.addAttribute("departments", depts);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/departmentlist";
    }
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-dept-{id}" }, method = RequestMethod.POST)
    public String updateDept(@Valid Department dept, BindingResult result,
                             ModelMap model, @PathVariable long id) {
        if (result.hasErrors()) {
            return "admin/regdept";
        }
        departamentService.update(dept);
        List<Department> depts=departamentService.findAll();

        model.addAttribute("departments", depts);
        model.addAttribute("success", "Отдел " + dept.getTitle() + " успешно обновлен");
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/departmentlist";
    }

    @RequestMapping(value = { "/delete-dept-{id}" }, method = RequestMethod.GET)
    public String deleteDept(@PathVariable int id) {
        departamentService.delete(id);
        return "redirect:/admin/depts";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "/edit-dept-{id}" }, method = RequestMethod.GET)
    public String editDept(@PathVariable int id, ModelMap model) {
        Department department=departamentService.findById(id);
        model.addAttribute("department", department);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/regdept";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = { "/newdept" }, method = RequestMethod.GET)
    public String newDept(ModelMap model) {
        Department department=new Department();
        model.addAttribute("department",department);
        model.addAttribute("branches",branchOfficeService.findAll());
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/regdept";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = { "/newdept" }, method = RequestMethod.POST)
    public String saveUser(@Valid Department department, BindingResult result,
                           ModelMap model) {
        System.err.println(department);
        System.err.println(department.getBranchOffice()==null?"null":department.getBranchOffice().getName_office());
        if (result.hasErrors()) {
            return "admin/regdept";
        }
        departamentService.save(department);
//        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
//            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
//            result.addError(ssoError);
//            return "registration";
//        }
//
//        userService.saveUser(user);
//
        List<Department> depts=departamentService.findAll();

        model.addAttribute("departments", depts);
        model.addAttribute("success", "Отдел " + department.getTitle() + " создан успешно");
        model.addAttribute("loggedinuser", getPrincipal());
        return "admin/departmentlist";
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
    @ModelAttribute("branches")
    public List<BranchOffice> initializeBranches() {
        return branchOfficeService.findAll();
    }

}

package com.pet.project.controller;

import com.pet.project.model.User;
import com.pet.project.model.task.Task;
import com.pet.project.service.day.DayService;
import com.pet.project.model.day.Day;
import com.pet.project.service.UserService;
import com.pet.project.service.task.TaskService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class TaskController {

    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    DayService dayService;


    @RequestMapping(value = { "/mytsks" }, method = RequestMethod.GET)
    public JSONObject ajaxGetMyTasks(HttpServletResponse response) throws IOException, ParseException {
        List<Task> tasks=userService.getAllTaskForEmp(getPrincipal());
        System.err.println("Tasks size:"+tasks.size());
        User user=userService.findBySSO(getPrincipal());
        List<Task> tasks1=taskService.findByID(user.getId());
        JSONObject json = new JSONObject();
        json.put("status","ok");
        json.put("tasks",tasks1);
        json.put("closedtasks",taskService.findByIdDay(dayService.getCurrDay(user).getId_day()));
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.close();
        System.err.println("mytasks");
        return json;
    }
    @RequestMapping(value = { "/startstoptask" }, method = RequestMethod.GET)
    public JSONObject ajaxStartStopTask(@RequestParam("idtask") long idtask,HttpServletResponse response) throws IOException, ParseException {

        JSONObject json = new JSONObject();
        json.put("status",taskService.startstopday(idtask));
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.close();
        return json;
    }
    @RequestMapping(value = { "/addtask" }, method = RequestMethod.GET)
    public JSONObject ajaxAddTask(@RequestParam("title") String title,
                                  @RequestParam("deadline") String deadline,HttpServletResponse response) throws IOException, ParseException {
        Task task=new Task();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        task.setDeadline(formatter.parse(deadline));
        task.setTitleTask(title);
        User user=userService.findBySSO(getPrincipal());
        System.out.println("user id: "+user.getId());
        task.setUserWhoCreate(user);
        task.setUserWhoDo(user);
        task.setTimeStampCreate(new Date());
        taskService.save(task);
        JSONObject json = new JSONObject();
        json.put("status","added");
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.close();
        return json;
    }
    @RequestMapping(value = { "/alltasks" }, method = RequestMethod.GET)
    public String allTasks(ModelMap model) throws IOException {
        model.addAttribute("tasks", userService.getAllTaskForEmp(getPrincipal()));
        model.addAttribute("edit", false);
        model.addAttribute("users",userService.findAllUsers());

//        model.addAttribute("users",userService.findSubordinates(getPrincipal()));
        model.addAttribute("loggedinuser", userService.findBySSO(getPrincipal()).getFullname());
        return "createdtasks";
    }
    @RequestMapping(value = { "/outtasks" }, method = RequestMethod.GET)
    public String outTasks(ModelMap model) throws IOException {
        model.addAttribute("tasks", userService.getOutTasks(getPrincipal()));
        model.addAttribute("edit", false);
        model.addAttribute("users",userService.findAllUsers());
        model.addAttribute("loggedinuser", userService.findBySSO(getPrincipal()).getFullname());
        return "tasks/prosrtasks";
    }
    @RequestMapping(value = { "/formetasks" }, method = RequestMethod.GET)
    public String forMeTasks(ModelMap model) throws IOException {
        model.addAttribute("tasks", userService.getAllTaskForEmp(getPrincipal()));
        model.addAttribute("edit", false);
        model.addAttribute("users",userService.findAllUsers());
        model.addAttribute("loggedinuser", userService.findBySSO(getPrincipal()).getFullname());
        return "tasks/kVipolneniu";
    }
    @RequestMapping(value = { "/endedtasks" }, method = RequestMethod.GET)
    public String endedTasks(ModelMap model) throws IOException {
        model.addAttribute("tasks", userService.getEndedTasks(getPrincipal()));
        model.addAttribute("edit", false);
        model.addAttribute("users",userService.findAllUsers());
        model.addAttribute("loggedinuser", userService.findBySSO(getPrincipal()).getFullname());
        return "tasks/vipolnennie";
    }

    @RequestMapping(value = { "/sovm" }, method = RequestMethod.GET)
    public String sovmTasks(ModelMap model) throws IOException {
        model.addAttribute("tasks", userService.getSovmTasks(getPrincipal()));
        model.addAttribute("edit", false);
        model.addAttribute("users",userService.findAllUsers());
        model.addAttribute("loggedinuser", userService.findBySSO(getPrincipal()).getFullname());
        return "tasks/vipolnennie";
    }
    @RequestMapping(value = { "/newtask" }, method = RequestMethod.GET)
    public String newTask(ModelMap model) throws IOException {
        model.addAttribute("task", new Task());
        model.addAttribute("edit", false);
        model.addAttribute("users",userService.findAllUsers());

//        model.addAttribute("users",userService.findSubordinates(getPrincipal()));
        model.addAttribute("loggedinuser", getPrincipal());
        return "newtask";
    }
    @RequestMapping(value = { "/newtask" }, method = RequestMethod.POST)
    public String saveMeeting(@Valid Task task, BindingResult result,
                              ModelMap model) {
        if (result.hasErrors()) {
            return "newtask";
        }
        User user =userService.findBySSO(getPrincipal());
        task.setUserWhoCreate(user);
        task.setTimeStampCreate(new Date());
//        user.getTaskCreatedBy().add(task);
        taskService.save(task);
        model.addAttribute("success", "Задача успешно создана");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "tasksForDay";
    }
    @RequestMapping(value = { "/delete-task-{id}" }, method = RequestMethod.GET)
    public String deleteTask(@PathVariable long id) {
        taskService.deleteById(id);
        return "redirect:tasksForDay";
    }
    @RequestMapping(value = { "/edit-task-{id}" }, method = RequestMethod.GET)
    public String editMeeting(@PathVariable int id, ModelMap model) {
        model.addAttribute("meeting", taskService.findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "addmeeting";
    }
    @RequestMapping(value = { "/daydesc" }, method = RequestMethod.GET)
    public JSONObject ajaxDayDesc(@RequestParam("myArray") List<Integer> tasks,@RequestParam("desc") String desc,HttpServletResponse response) throws IOException {
        User user=userService.findBySSO(getPrincipal());
        System.out.println(tasks.get(0));
        System.out.println(desc);
        PrintWriter out = response.getWriter();
        Day day=dayService.getCurrDay(user);
        dayService.updateDay(user,desc,tasks);
        JSONObject json = new JSONObject();
        json.put("status","ok");
        out.print(json.toString());
        out.close();
        return json;
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

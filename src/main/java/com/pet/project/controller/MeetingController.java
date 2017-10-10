package com.pet.project.controller;

import com.pet.project.converter.UserConverter;
import com.pet.project.model.User;
import com.pet.project.model.meeting.Meeting;
import com.pet.project.service.UserService;
import com.pet.project.service.meeting.MeetingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class MeetingController {
    @Autowired
    UserService userService;
    @Autowired
    MeetingService meetingService;
    @Autowired
    UserConverter userConverter;

    @RequestMapping(value = {  "/meetings" }, method = RequestMethod.GET)
    public String getMeetings(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        List<Meeting> meetings=userService.getAllMeetings(getPrincipal());
        System.out.println(meetings.size());
        model.addAttribute("meetings",meetings);
        return "meetings";
    }
    @RequestMapping(value = {  "/newmeeting" }, method = RequestMethod.GET)
    public String getNewMeeting(ModelMap model) {
        Meeting meeting=new Meeting();
        model.addAttribute("meeting",meeting);
        model.addAttribute("users",userService.findAllUsers());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("meetings",userService.getAllMeetings(getPrincipal()));
        return "addmeeting";
    }
    @RequestMapping(value = { "/newmeeting" }, method = RequestMethod.POST)
    public String saveMeeting(@Valid Meeting meeting, BindingResult result,
                              ModelMap model) {
        if (result.hasErrors()) {
            return "addmeeting";
        }
        User user =userService.findBySSO(getPrincipal());
        user.getMeetings().add(meeting);
        meeting.getEmployees().add(user);
        meetingService.save(meeting);
        model.addAttribute("success", "Митинг успешно создан");
        model.addAttribute("loggedinuser", getPrincipal());
        return "meetings";
    }
    @RequestMapping(value = { "/addmeeeting" }, method = RequestMethod.GET)
    public JSONObject ajaxAddMeeting(@RequestParam("desc") String desc,
                                     @RequestParam("startTime") Date startTime,
                                     @RequestParam("endTime") Date endTime) throws IOException, ParseException {
        Meeting meeting=new Meeting();
        meeting.setDesc(desc);
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);
        meetingService.save(meeting);
        JSONObject json = new JSONObject();
        json.put("status","created");
        return json;
    }
    @RequestMapping(value = { "/updmeeeting" }, method = RequestMethod.GET)
    public JSONObject ajaxUpdMeeting( @RequestParam("id") int id_meeting,
                                      @RequestParam("id_emp") int id_emp) throws IOException, ParseException {
        meetingService.addToMeeting(userService.findById(id_emp),meetingService.findById(id_meeting));
        JSONObject json = new JSONObject();
        json.put("status","updated");
        return json;
    }
    @RequestMapping(value = { "/delete-meeting-{id}" }, method = RequestMethod.GET)
    public String deleteMeeting(@PathVariable long id) {
        meetingService.delete(id);
        return "redirect:meetings";
    }
    @RequestMapping(value = { "/edit-meeting-{id}" }, method = RequestMethod.GET)
    public String editMeeting(@PathVariable int id, ModelMap model) {
        model.addAttribute("meeting", meetingService.findById(id));
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "addmeeting";
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

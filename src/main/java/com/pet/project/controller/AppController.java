package com.pet.project.controller;

import com.pet.project.model.User;
import com.pet.project.model.UserProfile;
import com.pet.project.model.day.Pause;
import com.pet.project.service.UserProfileService;
import com.pet.project.service.day.DayService;
import com.pet.project.model.day.Day;
import com.pet.project.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;
	
	@Autowired
    UserProfileService userProfileService;
	@Autowired
    DayService dayService;
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@RequestMapping(value = { "/user-{ssoId}" }, method = RequestMethod.GET)
	public String getUserInfo(ModelMap model, @PathVariable String ssoId) {
		model.addAttribute("loggedinuser", getPrincipal());
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		return "userinfo";
	}
	@RequestMapping(value = { "/", "/main","/mytasks" }, method = RequestMethod.GET)
	public String getTaskForDay(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("tasks",userService.getAllTaskForEmp(getPrincipal()));
		return "tasksForDay";
	}


	@RequestMapping(value = {  "/calendar" }, method = RequestMethod.GET)
	public String getCalendar(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "calendar";
	}
	@RequestMapping(value = {  "/documentsflow" }, method = RequestMethod.GET)
	public String getDocumentsFlow(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "documentsFlow";
	}
	@RequestMapping(value = {  "/birthdates" }, method = RequestMethod.GET)
	public String getBirthdates(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "birthdates";
	}
	@RequestMapping(value = {  "/fames" }, method = RequestMethod.GET)
	public String getBoardOfFame(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "boardOfFame";
	}
	@RequestMapping(value = {  "/calendar-events" }, method = RequestMethod.GET)
	public String getCalendarOfEvents(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "calendarOfEvents";
	}
	@RequestMapping(value = {  "/companystructure" }, method = RequestMethod.GET)
	public String getCompanyStructure(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "companystructure";
	}

	@RequestMapping(value = {  "/findemployee" }, method = RequestMethod.GET)
	public String getFindEmployee(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "findemployee";
	}

	@RequestMapping(value = {  "/mail-to-admin" }, method = RequestMethod.GET)
	public String getMailToAdmin(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "mailToAdmin";
	}
	@RequestMapping(value = {  "/managements" }, method = RequestMethod.GET)
	public String getManagements(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "managements";
	}
	@RequestMapping(value = {  "/phonebook" }, method = RequestMethod.GET)
	public String getPhoneBook(ModelMap model) {
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "phonebook";
	}


	@RequestMapping(value = {  "/timetable" }, method = RequestMethod.GET)
	public String getTimetable(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "timetable";
	}
	@RequestMapping(value = {  "/vacations" }, method = RequestMethod.GET)
	public String getVacation(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "vacations";
	}

	@RequestMapping(value = { "/late" }, method = RequestMethod.GET)
	public JSONObject ajaxLatesDay(ModelMap model,@RequestParam("lates") String latedesc) throws IOException {
		System.out.println("lates");
		User user=userService.findBySSO(getPrincipal());
		Day day=dayService.getCurrDay(user);
		day.setLatedesc(latedesc);
		dayService.save(day);
		JSONObject json = new JSONObject();
		json.put("status","added");
		return json;
	}
	@RequestMapping(value = { "/early" }, method = RequestMethod.GET)
	public JSONObject ajaxEarlyDay(ModelMap model,@RequestParam("early") String early) throws IOException {
		System.out.println("lates");
		User user=userService.findBySSO(getPrincipal());

		Day day=dayService.getCurrDay(user);
		day.setEarlydesc(early);
		dayService.save(day);
		JSONObject json = new JSONObject();
		json.put("status","added");
		return json;
	}

	@RequestMapping(value = { "/startStopAnotherTime" }, method = RequestMethod.GET)
	public JSONObject ajaxStartStopMyDayAnotherTime(ModelMap model,
													@RequestParam("type") String type,
													@RequestParam("time") String time,
													@RequestParam("cas") String cas,
													HttpServletResponse response) throws IOException {
		System.out.println("start or stop day");
		User user=userService.findBySSO(getPrincipal());
		PrintWriter out = response.getWriter();
		Day day=dayService.getCurrDay(user);
		JSONObject json=new JSONObject();
		switch (type){
			case "start":day.setStartTime(Time.valueOf(time)); day.setEarlydesc(cas);json.put("status","ok");break;
			case "end":day.setEndTime(Time.valueOf(time)); day.setLatedesc(cas);json.put("status","ok");break;
			default: json.put("status","not ok");
		}
		dayService.save(day);
		out.print(json.toString());
		out.close();
		return json;
	}
	@RequestMapping(value = { "/startstopday" }, method = RequestMethod.GET)
	public JSONObject ajaxStartStopMyDay(ModelMap model,HttpServletResponse response) throws IOException {
//		model.addAttribute("users",userService.findByFirstNameOrLastName(search));
		System.out.println("start or stop day");
		User user=userService.findBySSO(getPrincipal());
		PrintWriter out = response.getWriter();
		Day day=dayService.getCurrDay(user);
		JSONObject json = new JSONObject();
		Calendar c= Calendar.getInstance();
		int hour=c.get(c.HOUR_OF_DAY);
		int min=c.get(c.MINUTE);
		if (day.getStartTime()==null){
			if(hour<=8&&min<=30){
				dayService.startDay(user);
				json.put("status","started normally");
			}else{
				dayService.startDay(user);
				json.put("status","started with late");
			}

		}else{
			if(hour>=17&&min>=30){
				dayService.stopDay(user);
				json.put("status","stopped normally");
			}else{
				dayService.stopDay(user);
				json.put("status","stopped early");
			}

		}
//		return json;
		out.print(json.toString());
		out.close();
		return json;
	}
	@RequestMapping(value = { "/addDescPause" }, method = RequestMethod.GET)
	public JSONObject addDescPause(ModelMap model,HttpServletResponse response,@RequestParam("message") String desc) throws IOException {
		System.out.println("add desc");
		User user=userService.findBySSO(getPrincipal());
		PrintWriter out = response.getWriter();
		dayService.addCurPauseDesc(user,desc);
		//System.out.println(pause);
		JSONObject json = new JSONObject();
		//dayService.save(pause);
		json.put("status","addded");
		out.print(json.toString());
		out.close();
		return json;
	}
	@RequestMapping(value = { "/pause" }, method = RequestMethod.GET)
	public JSONObject ajaxPauseMyDay(ModelMap model,HttpServletResponse response) throws IOException {
//		model.addAttribute("users",userService.findByFirstNameOrLastName(search));
		System.out.println("pause my day");
		User user=userService.findBySSO(getPrincipal());
		PrintWriter out = response.getWriter();
		Pause pause=dayService.getCurrPause(user);
		JSONObject json = new JSONObject();
		if (pause.getStartTime()==null){
			dayService.startPause(user);
			json.put("status","paused");
		}else{
			dayService.stopPause(user);
			json.put("status","resumed");
		}
//		return json;
		out.print(json.toString());
		out.close();
		return json;
	}


	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String postSearch(ModelMap model,HttpServletRequest request,@RequestParam("search") String search) {
		model.addAttribute("users",userService.findByFirstNameOrLastName(search));
		System.out.println(search);
		model.addAttribute("loggedinuser", getPrincipal());
		return "search";
	}
	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
//	    	return "redirect:/list";
			return "redirect:/main";

		}
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
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


}
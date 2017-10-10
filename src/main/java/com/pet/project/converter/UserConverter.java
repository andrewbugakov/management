package com.pet.project.converter;

import com.pet.project.model.User;
import com.pet.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class UserConverter implements Converter<String, User>{

	static final Logger logger = LoggerFactory.getLogger(UserConverter.class);

	@Autowired
    UserService userService;

	@Override
	public User convert(String s) {
		System.err.println(s);
		Integer id = Integer.parseInt((String)s);
		User user=userService.findById(id);
		logger.info("User : {}",user);
		return user;
	}
}
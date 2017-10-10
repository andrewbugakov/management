package com.pet.project.converter;

import com.pet.project.service.office_structure.DepartamentService;
import com.pet.project.model.office_structure.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class DeptConverter implements Converter<Object, Department>{

	static final Logger logger = LoggerFactory.getLogger(DeptConverter.class);

	@Autowired
    DepartamentService departamentService;

	/**
	 * Gets UserProfile by Id
	 * @see Converter#convert(Object)
	 */
	public Department convert(Object element) {
		try {
			Integer id = Integer.parseInt((String)element);
			if(id!=-1) {
				Department department = departamentService.findById(id);
				logger.info("Dept : {}", department);
				return department;
			}else {
				return null;
			}
		}catch (Exception t){
			System.out.println("hoh");
			t.printStackTrace();
			return (Department) element;
		}

	}
	
}
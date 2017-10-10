package com.pet.project.converter;

import com.pet.project.model.office_structure.BranchOffice;
import com.pet.project.service.office_structure.BranchOfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class BranchToBranchConverter implements Converter<Object, BranchOffice>{

	static final Logger logger = LoggerFactory.getLogger(BranchToBranchConverter.class);

	@Autowired
	BranchOfficeService branchOfficeService;

	/**
	 * Gets UserProfile by Id
	 * @see Converter#convert(Object)
	 */
	public BranchOffice convert(Object element) {
		try {
			Integer id = Integer.parseInt((String)element);
			if(id!=-1) {
				BranchOffice branchOffice = branchOfficeService.findById(id);
				logger.info("Profile : {}", branchOffice);
				return branchOffice;
			}else {
				return null;
			}
		}catch (Exception t){
			System.out.println("hoh");
			t.printStackTrace();
			return (BranchOffice)element;
		}

	}
	
}
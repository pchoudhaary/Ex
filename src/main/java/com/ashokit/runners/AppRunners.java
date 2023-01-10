package com.ashokit.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashokit.entity.EligibilityDetails;
import com.ashokit.repo.EligibilityDetailRepo;

@Component
public class AppRunners implements ApplicationRunner {

	
	@Autowired
	private EligibilityDetailRepo repo;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
       
       EligibilityDetails eligdetails = new EligibilityDetails();
       
       eligdetails.setId(101);
       eligdetails.setName("Paras");
       eligdetails.setEmail("abc@gmail.com");
       eligdetails.setMobile(909099090L);
       eligdetails.setSsn("89890");
       eligdetails.setGender('M');
       eligdetails.setPlanName("SNAP");
       eligdetails.setPlanStatus("Denied");
       
       repo.save(eligdetails);
      
  EligibilityDetails eligdetails1 = new EligibilityDetails();
       
       eligdetails1.setId(102);
       eligdetails1.setName("Shivam");
       eligdetails1.setEmail("xyz@gmail.com");
       eligdetails1.setMobile(9096767090L);
       eligdetails1.setSsn("45450");
       eligdetails1.setGender('M');
       eligdetails1.setPlanName("CCAP");
       eligdetails1.setPlanStatus("Approved");
       
       repo.save(eligdetails1);
       
  EligibilityDetails eligdetails2 = new EligibilityDetails();
       
       eligdetails2.setId(101);
       eligdetails2.setName("Sahdev");
       eligdetails2.setEmail("rty@gmail.com");
       eligdetails2.setMobile(903390L);
       eligdetails2.setSsn("434330");
       eligdetails2.setGender('M');
       eligdetails1.setPlanName("CCAP");
       eligdetails1.setPlanStatus("Approved");
       
       repo.save(eligdetails2);
      
      }

}

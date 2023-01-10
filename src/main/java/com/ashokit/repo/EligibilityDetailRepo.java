package com.ashokit.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ashokit.entity.EligibilityDetails;


public interface EligibilityDetailRepo extends JpaRepository<EligibilityDetails, Integer> {
   
	@Query(value="select planName FROM eligibility_dtl")
	public List<String> getUniquePlanName();
	
	
	@Query(value="select planStatus FROM eligibility_dtl")
    public List<String> getUniquePlanStatus();
	
}

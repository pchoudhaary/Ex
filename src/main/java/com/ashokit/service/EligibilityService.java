package com.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ashokit.request.SearchRequest;
import com.ashokit.response.SearchResponse;


public interface EligibilityService {
     
	public List<String> getUniquePlanName();
	public List<String> getUniquePlanStatus();
	
	public List<SearchResponse> search(SearchRequest request);
	public void exportReportExecel(HttpServletResponse res) throws Exception;  //using httpservletresponse to send response directley to client 
	public void exportReportPdf(HttpServletResponse res) throws Exception;
	
	

}

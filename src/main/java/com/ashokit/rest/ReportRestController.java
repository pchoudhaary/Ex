package com.ashokit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.request.SearchRequest;
import com.ashokit.response.SearchResponse;
import com.ashokit.service.EligibilityService;

@RestController
@EnableAutoConfiguration

public class ReportRestController {
    
	 @Autowired
	private EligibilityService eservice;
	
	
	@GetMapping("/planes")
	public ResponseEntity<List<String>> getPlanName(){
		
		List<String> PlanName = eservice.getUniquePlanName();
		return new ResponseEntity<>(PlanName , HttpStatus.OK);
    }
	
	@GetMapping("/statuses")
	public ResponseEntity<List<String>> getPlanStatus(){
		
		List<String> PlanStatus = eservice.getUniquePlanStatus();
	    return new ResponseEntity<>(PlanStatus,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request){
		List<SearchResponse> searches = eservice.search(request);
		return new ResponseEntity<>(searches,HttpStatus.OK);
	}
	
	@GetMapping("/exel")
	public void exportExel(HttpServletResponse res) throws Exception {
		res.setContentType("application/octet-stream");
		String keyheader = "Content-Disposition";
		String valueheader = "attachment;filename=data.xls";
		
	res.setHeader(keyheader, valueheader);
	eservice.exportReportExecel(res);
    }
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse res) throws Exception {
		res.setContentType("application/pdf");
	    String key = "Content-Disposition";
	    String value = "attachment;filename=data.pdf";
	    res.setHeader(key, value);
	    eservice.exportReportPdf(res);
      }
	
	
}

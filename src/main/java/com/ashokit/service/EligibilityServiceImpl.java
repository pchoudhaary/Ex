package com.ashokit.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ashokit.entity.EligibilityDetails;
import com.ashokit.repo.EligibilityDetailRepo;
import com.ashokit.request.SearchRequest;
import com.ashokit.response.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
@Service
@EnableAutoConfiguration
public class EligibilityServiceImpl implements EligibilityService {
        
	   @Autowired
	    private EligibilityDetailRepo eligibilityDetail;
	
	 
	
	@Override
	public List<String> getUniquePlanName() {
          return  eligibilityDetail.getUniquePlanName();
	}

	@Override
	public List<String> getUniquePlanStatus() {
          return eligibilityDetail.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
              
		
		List<SearchResponse> searchres = new ArrayList<>();
		
		EligibilityDetails eldetalils = new EligibilityDetails();
		
		String planName = request.getPlanName();
		if(planName!=null && !planName.equals("")) {
	        		eldetalils.setPlanName(planName);
		}
		
	    String planStatus = request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals("")) {
			eldetalils.setPlanStatus(planStatus);
		}
		
		LocalDate planStartDate = request.getPlanStartDate();
		if(planStartDate!=null) {
			eldetalils.setPlanStartDate(planStartDate);
		}
		
		LocalDate planEndDate = request.getPlanEndDate();
		if(planEndDate!=null) {
			eldetalils.setPlanEndDate(planEndDate);	
		}
		
                    
	    Example<EligibilityDetails> example =Example.of(eldetalils);
        List<EligibilityDetails> findAll = eligibilityDetail.findAll(example);
		
        for(EligibilityDetails eligib: findAll) {
			
				SearchResponse sr = new SearchResponse();
			    BeanUtils.copyProperties(eligib, sr);
	            searchres.add(sr);
	            }
		
		
		
		return searchres;
	}

	@Override
	public void exportReportExecel(HttpServletResponse res) throws Exception {

		List<EligibilityDetails> entities = eligibilityDetail.findAll();
		
	  XSSFWorkbook Workbook = new XSSFWorkbook();
	 XSSFSheet createSheet = Workbook.createSheet();
	 XSSFRow header = createSheet.createRow(0);
	   
	 header.createCell(0).setCellValue("sno");
	 header.createCell(1).setCellValue("name");
	 header.createCell(2).setCellValue("email");
	 header.createCell(3).setCellValue("mobile");
	 header.createCell(4).setCellValue("ssn");
	 
	 entities.forEach( entity-> {
		 
		 XSSFRow datarow = createSheet.createRow(1);
		 datarow.createCell(0).setCellValue(entity.getId());
		datarow.createCell(1).setCellValue(entity.getName());
		datarow.createCell(2).setCellValue(entity.getEmail());
		datarow.createCell(3).setCellValue(entity.getMobile());
		datarow.createCell(4).setCellValue(entity.getSsn());
	 });
	 
	 
	ServletOutputStream outputStream = res.getOutputStream();
	Workbook.write(outputStream);
    Workbook.close();
    outputStream.close();
	}

	@Override
	public void exportReportPdf(HttpServletResponse res) throws Exception {

		List<EligibilityDetails> findAll = eligibilityDetail.findAll();
		
		Document document = new Document(PageSize.A4);
	PdfWriter.getInstance(document, res.getOutputStream());
	document.open();
	  Font font = FontFactory.getFont(FontFactory.HELVETICA);
	  font.setSize(18);
      font.setColor(Color.RED);
      
      Paragraph p = new Paragraph("Search Report",font);
      p.setAlignment(Paragraph.ALIGN_CENTER);
      
      document.add(p);
      
      PdfPTable table =  new PdfPTable(5);
      table.setWidthPercentage(100f);
      table.setWidths(new float[] {1.5f,3.5f,3.0f,1.5f,3.5f});
      table.setSpacingBefore(10);
      
       PdfPCell cell = new PdfPCell();
      cell.setBackgroundColor(Color.DARK_GRAY);
      cell.setPadding(5);
      
      font = FontFactory.getFont(FontFactory.HELVETICA);
      font.setColor(Color.white);
      
      cell.setPhrase(new Phrase("Name",font));
      table.addCell(cell);
      
      cell.setPhrase(new Phrase("E-mail",font));
      table.addCell(cell);
      
      cell.setPhrase(new Phrase("phon",font));
      table.addCell(cell);
      
      cell.setPhrase(new Phrase("Gender",font));
      table.addCell(cell);
      
      cell.setPhrase(new Phrase("SSN",font));
      table.addCell(cell);
      
      for(EligibilityDetails entity: findAll) {
   	   table.addCell(entity.getName());
   	   table.addCell(entity.getEmail());
   	   table.addCell(String.valueOf(entity.getMobile()));
   	   table.addCell(String.valueOf(entity.getGender()));
   	   table.addCell(String.valueOf(entity.getSsn()));
      }
      
      document.add(table);
      document.close();

		
		
		
		
	}

}

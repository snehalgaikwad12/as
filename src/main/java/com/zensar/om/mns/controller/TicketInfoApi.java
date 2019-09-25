package com.zensar.om.mns.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


import javax.servlet.http.HttpSession;

import org.apache.commons.text.WordUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zensar.om.mns.entity.Classification;
import com.zensar.om.mns.entity.FunctionalArea;
import com.zensar.om.mns.entity.HistoryTable;
import com.zensar.om.mns.entity.RcCategory;
import com.zensar.om.mns.entity.ReasonForHelp;
import com.zensar.om.mns.entity.SmeName;
import com.zensar.om.mns.entity.SubFunction;
import com.zensar.om.mns.entity.TicketInfo;
import com.zensar.om.mns.service.TicketService;
import com.zensar.om.mns.util.ExcelGenerator;

@CrossOrigin(origins = "*")
@RestController
public class TicketInfoApi {

	ArrayList al=new ArrayList<>();
	private static String UPLOADED_FOLDER = "D://Temp1//";
	@Autowired
	private TaskScheduler scheduler;
	
	@Autowired
	TicketService ticketService;
	String projectName;
	 private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@GetMapping(value = "/api/DropdownList/Classification")
	public ResponseEntity<ArrayList<Classification>> getClassificationData() {
		ArrayList<Classification> classification = (ArrayList<Classification>) ticketService.getTableData();
		return new ResponseEntity<ArrayList<Classification>>(classification, HttpStatus.OK);
	}

	@GetMapping(value = "/api/DropdownList/FunctionalArea")
	public ResponseEntity<ArrayList<FunctionalArea>> getFunctionalAreaData() {
		ArrayList<FunctionalArea> functionalArea = (ArrayList<FunctionalArea>) ticketService.getFunctionalAreaData();
		return new ResponseEntity<ArrayList<FunctionalArea>>(functionalArea, HttpStatus.OK);
	}

	@GetMapping(value = "/api/DropdownList/SmeNameData")
	public ResponseEntity<ArrayList<SmeName>> getSmeNameData() {
		ArrayList<SmeName> smeName = (ArrayList<SmeName>) ticketService.getSmeNameData();
		return new ResponseEntity<ArrayList<SmeName>>(smeName, HttpStatus.OK);
	}

	@GetMapping(value = "/api/DropdownList/SubFunction")
	public ResponseEntity<ArrayList<SubFunction>> getSubFunctionData() {
		ArrayList<SubFunction> subFunction = (ArrayList<SubFunction>) ticketService.getSubFunctionData();
		return new ResponseEntity<ArrayList<SubFunction>>(subFunction, HttpStatus.OK);
	}

	@GetMapping(value = "/api/DropdownList/RcCategory")
	public ResponseEntity<ArrayList<RcCategory>> getRcCategoryData() {
		ArrayList<RcCategory> rcCategory = (ArrayList<RcCategory>) ticketService.getRcCategoryData();
		return new ResponseEntity<ArrayList<RcCategory>>(rcCategory, HttpStatus.OK);
	}

	@GetMapping(value = "/api/DropdownList/ReasonForHelp")
	public ResponseEntity<ArrayList<ReasonForHelp>> getReasonForHelpData() {
		ArrayList<ReasonForHelp> reasonForHelp = (ArrayList<ReasonForHelp>) ticketService.getReasonForHelpData();
		return new ResponseEntity<ArrayList<ReasonForHelp>>(reasonForHelp, HttpStatus.OK);
	}

	@GetMapping(value = "/api/TicketInformation")
	public ResponseEntity<ArrayList<TicketInfo>> getAllTicketInformation() {
		ArrayList<TicketInfo> tickets = (ArrayList<TicketInfo>) ticketService.getAllTickets();
		return new ResponseEntity<ArrayList<TicketInfo>>(tickets, HttpStatus.OK);
	}

	@GetMapping(value = "/api/TicketInformation/byDateForAllUser")
	public ResponseEntity<ArrayList<TicketInfo>> getTicketInformationByDate(@RequestParam("fdate") String fdate,
			@RequestParam("secondDate") String sdate) {
		ArrayList<TicketInfo> tickets = (ArrayList<TicketInfo>) ticketService.searchByDate(fdate, sdate);
		return new ResponseEntity<ArrayList<TicketInfo>>(tickets, HttpStatus.OK);
	}

	@GetMapping(value = "/api/TicketInformation/byDate")
	public ResponseEntity<ArrayList<TicketInfo>> getTicketInfoByDateForUser(@RequestParam("fdate") String fdate,
			@RequestParam("secondDate") String sdate, @RequestParam("userName") String userName) {
		ArrayList<TicketInfo> tickets = (ArrayList<TicketInfo>) ticketService.searchByDateForUser(fdate, sdate,
				userName);
		return new ResponseEntity<ArrayList<TicketInfo>>(tickets, HttpStatus.OK);
	}

	private String[] initMethods = { "setTopsNumber", "setProblemDesc",  "setStatus", "setClasification", "setFunctionalArea", "setSubFunction",
			"setTypeOfTicket", "setSmeHelp", "setSmeName", "setReasonForHelp", "setResolution", "setPriority",
			"setComplexity", "setRcCategory", "setRootCause", "setPermFixApp", "setRemarks" };

	
	private String[] getMethods = { "getTopsNumber", "getProblemDesc",
			 "getStatus", "getClasification", "getFunctionalArea", "getSubFunction",
			"getTypeOfTicket", "getSmeHelp", "getSmeName", "getReasonForHelp", "getResolution", "getPriority",
			"getComplexity", "getRcCategory", "getRootCause", "getPermFixApp", "getRemarks" };

	
	private Method getMethod(int index) throws NoSuchMethodException {
		if ((index < 0) || (index > initMethods.length - 1))
			return null;
		Class clazz = TicketInfo.class;
		return clazz.getMethod(initMethods[index], String.class);
	}
	
	private Method callGetMethod(int index) throws NoSuchMethodException {
		if ((index < 0) || (index > initMethods.length - 1))
			return null;
		Class clazz = TicketInfo.class;
		return clazz.getMethod(getMethods[index]);
	}


	@PostMapping(value = "/api/TicketInformation")
	public String saveTicketDetail(@RequestBody TicketInfo ticketDetails) {
		
		ticketService.saveTickectInfo(ticketDetails);
		return "SAVED";
	}
	
	@PostMapping(value = "/api/TicketInformation/projectName/{event}")
	public String sendProjectName(@PathVariable("event") String event) {
		projectName=event;
		return null;
	}

	@PostMapping(value = "/api/TicketInformation/test")
	public ResponseEntity<TicketInfo> saveTicketDetailTest(@RequestBody TicketInfo ticketDetails) {
		if (getMethods.length<0 )
			 return null; 
		  
		  for (int i = 0; i < getMethods.length; i++)
		  { 
			  Method m;
			try {
				m = callGetMethod(i);
				al.add(m.invoke(ticketDetails));
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  
			}
		  System.out.println("array list values "+al);
		  if (al.size() != initMethods.length) return null; 
		  
		  for (int i = 0; i < initMethods.length; i++)
		  { Method m;
		try {
			m = getMethod(i);
			System.out.println("check "+al.get(i));
			
			//WordUtils.capitalizeFully(String.valueOf(al.get(i)));
			if((al.get(i))!=null) {
					
					  
					
					  m.invoke(ticketDetails,WordUtils.capitalizeFully(String.valueOf(al.get(i))));
					 
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		  
		  
		  System.out.println("TICKET .."+ticketDetails);
		  
		  al.clear();
		 
		if (ticketService.saveTickectInfo(ticketDetails).equals("SAVED"))
			return new ResponseEntity<TicketInfo>(ticketDetails, HttpStatus.OK);
		else
			return new ResponseEntity<TicketInfo>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping(value = "/api/TicketInformation/updateTicket")
	public String updateTicket(@RequestBody TicketInfo ticketInfo) {
		ticketService.updateTicket(ticketInfo);
		return "UPDATED";
	}

	@PutMapping(value = "/api/TicketInformation")
	public ResponseEntity<TicketInfo> updateTicket1(@RequestBody TicketInfo ticketInfo) {
		if (ticketService.updateTicket(ticketInfo).equals("UPDATED"))
			return new ResponseEntity<TicketInfo>(ticketInfo, HttpStatus.OK);
		else
			return new ResponseEntity<TicketInfo>(HttpStatus.NOT_MODIFIED);
	}

	@GetMapping(value = "/api/TicketInformation/duplicateRecord")
	public ArrayList<TicketInfo> getDuplicateTicketInformation() {

		System.out.println("api called ");
		return (ArrayList<TicketInfo>) ticketService.getDuplicateTickets();
	}

	/*
	 * @GetMapping(value = "/api/TicketInformation/duplicateRecord") public
	 * ResponseEntity<ArrayList<TicketInfo>> getDuplicateTicketInformation() { if(
	 * ticketService.getDuplicateTickets().size()>0) { return new
	 * ResponseEntity<ArrayList<TicketInfo>>((ArrayList<TicketInfo>)
	 * ticketService.getDuplicateTickets(),HttpStatus.OK); } else { return new
	 * ResponseEntity<ArrayList<TicketInfo>>(HttpStatus.NOT_FOUND); } }
	 */
	@GetMapping(value = "/api/TicketInformation/closedTicket")
	public ArrayList<TicketInfo> getPreviousWeekTicketInformation() {
		return (ArrayList<TicketInfo>) ticketService.getPreviousWeekRecord();
	}

	@DeleteMapping(value = "/api/TicketInformation/{seqNum}")
	public ResponseEntity<String> deleteTicket(@PathVariable("seqNum") int seqNum) {
		if (ticketService.deleteTicketBySeqNo(seqNum))
			return new ResponseEntity(HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/api/TicketHistoryInformation")
	public String saveTicketHistory(@RequestBody Map ticketHistory) {
		// System.out.println("in api of hstory");
		HistoryTable history = new HistoryTable();
		history.setRemark(ticketHistory.get("Remark").toString());
		history.setTicketInfoJson(ticketHistory.get("JsonObject").toString());
		history.setUsername(ticketHistory.get("userName").toString());
		ticketService.storeTicketHistory(history);
		return "SAVED";
	}
	
	@Scheduled(cron="0 30 19 * * ?")
	
	public void downloadExcelReport()
	{
		System.out.println("DOWNLOADING ");	
		List<TicketInfo> ticketinfo=(List<TicketInfo>)  ticketService.getAllTicketsWithBothStatus();
			 System.out.println("ticketinfo"+ticketinfo.toString());

			try {
				ByteArrayInputStream in = ExcelGenerator.ticketsToExcel(ticketinfo);
				IOUtils.copy(in,new FileOutputStream(new File("D:\\TicketInformation(AllUser).xlsx")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	@GetMapping(value =  "/api/TicketInformation/downloadForSpecificUser/{userName}")
	public ResponseEntity<InputStreamResource> excelTicketReport1(@PathVariable("userName") String userName) throws IOException {
	System.out.println("DOWNLOADING ");	
	List<TicketInfo> ticketinfo=(List<TicketInfo>)  ticketService.getUserSpecificTickets(userName);
		 System.out.println("ticketinfo"+ticketinfo.toString());

		ByteArrayInputStream in = ExcelGenerator.ticketsToExcel(ticketinfo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Ticket_Information.xlsx");
		System.out.println("aa");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	@GetMapping(value =  "/api/TicketInformation/downloadForAllUsers")
	public ResponseEntity<InputStreamResource> excelTicketReport2() throws IOException {
		System.out.println("DOWNLOADING ");	
		List<TicketInfo> ticketinfo=(List<TicketInfo>)  ticketService.getTicketsForAllUsers();
			 System.out.println("ticketinfo"+ticketinfo.toString());

			ByteArrayInputStream in = ExcelGenerator.ticketsToExcel(ticketinfo);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=Ticket_Information.xlsx");
			System.out.println("aa");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

		}
//	@GetMapping(value = "/api/TicketInformation/download/ticketsInformation.xlsx")
//	public ResponseEntity<InputStreamResource> excelTicketReport(Model model, HttpSession session) throws IOException {
//		System.out.println("TESTING ");
//		List<TicketInfo> ticketinfo=(List<TicketInfo>) scheduler.scheduleAtFixedRate((Runnable) ticketService.getAllTicketsWithBothStatus(), Duration.ofMinutes(5));
//		 //(List<TicketInfo>) 
//
//		ByteArrayInputStream in = ExcelGenerator.ticketsToExcel(ticketinfo);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Disposition", "attachment; filename=Ticket_Information.xlsx");
//
//		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
//
//	}
	
	
	@GetMapping(value = "/api/TicketInformation/downloadByProject/ticketsInformation.xlsx")
	public ResponseEntity<InputStreamResource> excelReportBasedOnProject(Model model, HttpSession session) throws IOException {

		List<TicketInfo> ticketinfo = (List<TicketInfo>) ticketService.getTicketsBasedOnProject(projectName);
		ByteArrayInputStream in = ExcelGenerator.ticketsToExcel(ticketinfo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Ticket_Information.xlsx");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

	}
	
	

	@PostMapping(value = "/api/TicketInformation/upload")

	public TicketInfo autoFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			Model model, HttpSession session) {
		try {

			String name = file.getOriginalFilename();
			String name1 = name.substring(name.lastIndexOf("\\") + 1);
			System.out.println("NAME ! " + name1);
			byte[] bytes = file.getBytes();

			Path path = Paths.get(UPLOADED_FOLDER + name1);
			Files.write(path, bytes);
			ticketService.readExcelFile(UPLOADED_FOLDER + name1);

			System.out.println("FILE READING CMPLTED");
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping(value = "/api/TicketHistoryInformation")
	public ResponseEntity<ArrayList<HistoryTable>> getAllTicketHistory() {
		ArrayList<HistoryTable> tickets = (ArrayList<HistoryTable>) ticketService.getAllTicketsHistory();
		return new ResponseEntity<ArrayList<HistoryTable>>(tickets, HttpStatus.OK);
	}

	@GetMapping(value = "/api/TicketInformation/UserBased")
	public ResponseEntity<ArrayList<TicketInfo>> getWipTicketByUser(@RequestParam("userName") String userName) {
		System.out.println("username for wip " + userName);
		ArrayList<TicketInfo> tickets = (ArrayList<TicketInfo>) ticketService.getWipTicketByUser(userName);
		System.out.println("ticket size"+tickets.size());
		System.out.println("ticket info "+tickets.toString());
		return new ResponseEntity<ArrayList<TicketInfo>>(tickets, HttpStatus.OK);
	}
	@GetMapping(value = "/api/TicketInformation/Duplicate/UserBased")
	public ResponseEntity<ArrayList<TicketInfo>> getDuplicateTicketByUser(@RequestParam("userName") String userName) {
		System.out.println("username for search  " + userName);
		ArrayList<TicketInfo> tickets = (ArrayList<TicketInfo>) ticketService.getDuplicateTicketByUser(userName);
		System.out.println("ticket size"+tickets.size());
		System.out.println("ticket info "+tickets.toString());
		return new ResponseEntity<ArrayList<TicketInfo>>(tickets, HttpStatus.OK);
	}

}

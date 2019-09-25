package com.zensar.om.mns.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zensar.om.mns.entity.Classification;
import com.zensar.om.mns.entity.FunctionalArea;
import com.zensar.om.mns.entity.HistoryTable;
import com.zensar.om.mns.entity.Owner;
import com.zensar.om.mns.entity.RcCategory;
import com.zensar.om.mns.entity.ReasonForHelp;
import com.zensar.om.mns.entity.SmeName;
import com.zensar.om.mns.entity.SubFunction;
import com.zensar.om.mns.entity.TicketInfo;
import com.zensar.om.mns.exception.ExcelSequenceInvalid;
import com.zensar.om.mns.exception.GlobalException;
import com.zensar.om.mns.repository.FunctionalAreaRepo;
import com.zensar.om.mns.repository.HistoryTableRepo;
import com.zensar.om.mns.repository.OwnerRepo;
import com.zensar.om.mns.repository.RcCategoryRepo;
import com.zensar.om.mns.repository.ReasonForHelpRepo;
import com.zensar.om.mns.repository.SmeNameRepo;
import com.zensar.om.mns.repository.SubFunctionalRepo;
import com.zensar.om.mns.repository.TicketInfoRepo;
import com.zensar.om.mns.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	FunctionalAreaRepo functionalAreaRepo;
	@Autowired
	SmeNameRepo smeNameRepo;
	@Autowired
	OwnerRepo ownerRepo;
	@Autowired
	SubFunctionalRepo subFunctionalRepo;
	@Autowired
	ReasonForHelpRepo reasonForHelpRepo;
	@Autowired
	RcCategoryRepo rcCategoryRepo;
	@Autowired
	TicketInfoRepo ticketInfoRepo;
	@Autowired
	HistoryTableRepo historyRepo;

	@Override
	public List<Classification> getTableData() {
		List<Classification> classification = (List<Classification>) ticketRepository.findAll();
		return classification;
	}

	@Override
	public List<FunctionalArea> getFunctionalAreaData() {
		List<FunctionalArea> functionalArea = (List<FunctionalArea>) functionalAreaRepo.findAll();
		return functionalArea;
	}

	@Override
	public List<SubFunction> getSubFunctionData() {
		List<SubFunction> subFunction = (List<SubFunction>) subFunctionalRepo.findAll();
		return subFunction;
	}

	@Override
	public List<Owner> getOwnerData() {
		List<Owner> owner = (List<Owner>) ownerRepo.findAll();
		return owner;
	}

	@Override
	public List<SmeName> getSmeNameData() {
		List<SmeName> smeName = (List<SmeName>) smeNameRepo.findAll();
		return smeName;
	}

	@Override
	public List<ReasonForHelp> getReasonForHelpData() {
		List<ReasonForHelp> reasonForHelp = (List<ReasonForHelp>) reasonForHelpRepo.findAll();
		return reasonForHelp;

	}

	@Override
	public List<RcCategory> getRcCategoryData() {
		List<RcCategory> rcCategory = (List<RcCategory>) rcCategoryRepo.findAll();
		return rcCategory;

	}

	@Override
	public String saveTickectInfo(TicketInfo ticketInfo) {

		if(ticketInfoRepo.save(ticketInfo)!=null)
			return "SAVED";
		else
			return "ERROR SAVING";
	}

	@Override
	public List<TicketInfo> getAllTickets() {
		List<TicketInfo> getAll = (List<TicketInfo>) ticketInfoRepo.searchByStatus();
		return getAll;
	}

	@Override
	public String updateTicket(TicketInfo ticketInfo) {
		
		if(ticketInfoRepo.save(ticketInfo)!=null)
		return "UPDATED";
		else
			return "ERROR UPDATING";
	}

	@Override
	public List<TicketInfo> searchByDate(String date1, String date2) {
		List<TicketInfo> recordsByDate = (List<TicketInfo>) ticketInfoRepo.searchByDate(date1, date2);
		return recordsByDate;
	}
	
	@Override
	public List<TicketInfo> searchByDateForUser(String date1, String date2,String userName) {
		List<TicketInfo> recordsByDate = (List<TicketInfo>) ticketInfoRepo.searchByDateForUser(date1, date2,userName);
		return recordsByDate;
	}

	/*
	 * private String[] initMethods = { "setTopsNumber", "setProblemDesc",
	 * "setRecvdDate", "setPickedDate", "setClosedDate", "setStatus",
	 * "setClasification", "setFunctionalArea", "setSubFunction", "setOwner",
	 * "setTypeOfTicket", "setSmeHelp", "setSmeName", "setReasonForHelp",
	 * "setSeverity", "setPriority", "setComplexity", "setRcCategory",
	 * "setRootCause", "setPermFixApp", "setRemarks" };
	 * 
	 * private Method getMethod(int index) throws NoSuchMethodException { if ((index
	 * < 0) || (index > initMethods.length - 1)) return null; Class clazz =
	 * TicketInfo.class; return clazz.getMethod(initMethods[index], String.class); }
	 * 
	 * public TicketInfo retrieveWithValues(ArrayList values) throws
	 * NoSuchMethodException, InvocationTargetException, IllegalAccessException {
	 * 
	 * if (values.size() != initMethods.length) return null; // Handle the fact you
	 * don't have the same amount of initialisations as the // amount of fields
	 * TicketInfo o = new TicketInfo(); for (int i = 0; i < initMethods.length; i++)
	 * { Method m = getMethod(i); m.invoke(o, String.valueOf(values.get(i))); }
	 * saveTickectInfo(o); return o; }
	 */
	public void readExcelFile(String filePath) throws IOException {
		String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
		System.out.println("FILE EXTN " + fileExtension);
		if (!fileExtension.equals("xlsx")) {
			throw new GlobalException("Please select a file with extension xlsx to Upload");
		}
		FileInputStream excelFile = new FileInputStream(new File(filePath));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheet("Ticket_Information");
		if (sheet == null) {
			sheet = workbook.getSheetAt(0);
		}
		Iterator<Row> rows = sheet.iterator();

		int length = 0;
		while (rows.hasNext()) {
			System.out.println("Row present");
			Row currentRow = rows.next();
			currentRow.cellIterator();
			Iterator<Cell> cellsInRow = currentRow.cellIterator();
			if (currentRow.getRowNum() > 0) {
				ArrayList al = new ArrayList<>();
				for (int i = 0; i < length; i++) {
					if (currentRow.getCell(i) == null || currentRow.getCell(i).getCellType() == Cell.CELL_TYPE_BLANK) {
						
						al.add("");
					} else if (currentRow.getCell(i).getColumnIndex() == 2
							|| currentRow.getCell(i).getColumnIndex() == 3
							|| currentRow.getCell(i).getColumnIndex() == 4) {
						currentRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						
						al.add(currentRow.getCell(i).getStringCellValue());
						
					} else {
						currentRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						
						al.add(currentRow.getCell(i));
					}
				}
			
				try {
					TicketInfo t = new TicketInfo();
					t.setTopsNumber(String.valueOf(al.get(0)));
					t.setProblemDesc(String.valueOf(al.get(1)));
					t.setRecvdDate(String.valueOf(al.get(2)));
					t.setPickedDate(String.valueOf(al.get(3)));
					t.setClosedDate(String.valueOf(al.get(4)));
					t.setStatus(String.valueOf(al.get(5)));
					t.setClasification(String.valueOf(al.get(6)));
					t.setFunctionalArea(String.valueOf(al.get(7)));
					t.setSubFunction(String.valueOf(al.get(8)));
					t.setOwner(String.valueOf(al.get(9)));
					t.setTypeOfTicket(String.valueOf(al.get(10)));
					t.setSmeHelp(String.valueOf(al.get(11)));
					t.setSmeName(String.valueOf(al.get(12)));
					t.setReasonForHelp(String.valueOf(al.get(13)));
					t.setResolution(String.valueOf(al.get(14)));
					t.setPriority(String.valueOf(al.get(15)));
					t.setComplexity(String.valueOf(al.get(16)));
					t.setRcCategory(String.valueOf(al.get(17)));
					t.setRootCause(String.valueOf(al.get(18)));
					t.setPermFixApp(String.valueOf(al.get(19)));
					t.setRemarks(String.valueOf(al.get(20)));
					saveTickectInfo(t);
					System.out.println("ticket saved");

				} catch (Exception e) {
					System.out.println("ROW NO " + currentRow.getRowNum());
					continue;
				}
			}

			else {
                length = currentRow.getLastCellNum();                                 
                String[] headingArray= {"TOPS Number","Problem Description","Received","Picked","Closed","Status","Clasification","Functional Area","Sub Function","Owner","Type of ticket","SME Help","SME Name","Reason For Help","Severity","Priority","Complexity","RC Cateogory","Root Cause","Permanent Fix Applied","Remarks"};
                if(length==headingArray.length){                                                              
                                for(int i=0;i<length;i++){                
                                                currentRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                                                if(!(currentRow.getCell(i).getStringCellValue().equalsIgnoreCase(headingArray[i]))){
                                                                throw new ExcelSequenceInvalid("Excel sheet should maintain a proper order ");
                                                }              
                                }
                                rows.next();
                }
                else
                                throw new ExcelSequenceInvalid("Excel sheet should maintain a proper order of Headings ");                                                                  

}

			System.out.println();
		}
		workbook.close();
		excelFile.close();
	}

	
	@Override
	public List<TicketInfo> getDuplicateTickets() {
		System.out.println("api call ");
		List<TicketInfo> getAll = (List<TicketInfo>) ticketInfoRepo.searchForDuplicateRecords();
		System.out.println("DUP LIST SIZE "+getAll.size());
		return getAll;
	}

	@Override
	public List<TicketInfo> getPreviousWeekRecord() {
		LocalDate dt = LocalDate.now();
		List<TicketInfo> getAll = (List<TicketInfo>) ticketInfoRepo.searchForPreviousWeekRecord(
				"" + dt.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)),
				"" + dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
		return getAll;
	}

	@Override
	public List<TicketInfo> getAllTicketsWithBothStatus() {
		System.out.println("IN SERVICE");
		List<TicketInfo> getAll = (List<TicketInfo>) ticketInfoRepo.findAll();
		System.out.println("size "+getAll.size());
		return getAll;
	}

	@Override
	public Optional<TicketInfo> getTicketBySeqNum(long sequenceNum) {
		return ticketInfoRepo.findById(sequenceNum);
	}

	@Override
	public boolean deleteTicketBySeqNo(long sequenceNum) {
		ticketInfoRepo.deleteById(sequenceNum);
		if (ticketInfoRepo.existsById(sequenceNum)) {
			return false;
		}
		return true;
	}

	@Override
	public String storeTicketHistory(HistoryTable historyTable) {
		historyRepo.save(historyTable);
		return "Successful";
	}

	@Override
	public List<HistoryTable> getAllTicketsHistory() {

		List<HistoryTable> getAll = (List<HistoryTable>) historyRepo.findAll();
		return getAll;
	}

	@Override
	public List<TicketInfo> getWipTicketByUser(String uname) {
		List<TicketInfo> getAll = (List<TicketInfo>)ticketInfoRepo.searchWipByUser(uname);
		System.out.println("get all ticket of user size "+getAll.size());
		System.out.println("get wip tic detail "+getAll.toString());
		return getAll;
	}
	@Override
	public List<TicketInfo> getDuplicateTicketByUser(String uname) {
		List<TicketInfo> getAll = (List<TicketInfo>)ticketInfoRepo.searchDuplicateByUser(uname);
		System.out.println("get all ticket of user size "+getAll.size());
		System.out.println("get wip tic detail "+getAll.toString());
		return getAll;
	}

	@Override
	public List<TicketInfo> getTicketsBasedOnProject(String projectName) {
		List<TicketInfo> getAll = ticketInfoRepo.searchTicketByProject(projectName);
		System.out.println("getAll "+getAll.toString());
		return getAll;
	}

	@Override
	public List<TicketInfo> getUserSpecificTickets(String userName) {
		List<TicketInfo> getAll = ticketInfoRepo.downloadUserSpecificTickets(userName);
		System.out.println("getAll "+getAll.toString());
		return getAll;
	}

	@Override
	public List<TicketInfo> getTicketsForAllUsers() {
		
		List<TicketInfo> getAll = (List<TicketInfo>) ticketInfoRepo.findAll();
		System.out.println("getAll "+getAll.toString());
		return getAll;

	}
}

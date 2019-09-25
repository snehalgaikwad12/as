package com.zensar.om.mns.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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

@Service
public interface TicketService {

	public List<Classification> getTableData();

	public List<FunctionalArea> getFunctionalAreaData();

	public List<SubFunction> getSubFunctionData();

	public List<Owner> getOwnerData();

	public List<ReasonForHelp> getReasonForHelpData();

	public List<RcCategory> getRcCategoryData();

	public List<SmeName> getSmeNameData();

	public String saveTickectInfo(TicketInfo ticketInfo);

	public List<TicketInfo> getAllTickets();

	public String updateTicket(TicketInfo ticketInfo);

	public List<TicketInfo> searchByDate(String date1, String date2);
	public List<TicketInfo> searchByDateForUser(String date1, String date2,String userName) ;
	public void readExcelFile(String filePath) throws IOException;

	public List<TicketInfo> getDuplicateTickets();

	public List<TicketInfo> getPreviousWeekRecord();

	public List<TicketInfo> getAllTicketsWithBothStatus();

	public Optional<TicketInfo> getTicketBySeqNum(long sequenceNum);
	
	public boolean deleteTicketBySeqNo(long sequenceNum);
	
	public String storeTicketHistory(HistoryTable historyTable);
	public List<HistoryTable> getAllTicketsHistory();
	
	public List<TicketInfo> getWipTicketByUser(String uname);

	public List<TicketInfo> getTicketsBasedOnProject(String projectName);

	public List<TicketInfo> getUserSpecificTickets(String userName);

	public List<TicketInfo> getTicketsForAllUsers();

	public List<TicketInfo> getDuplicateTicketByUser(String uname);
	
	
}

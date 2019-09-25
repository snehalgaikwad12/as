package com.zensar.om.mns.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zensar.om.mns.entity.TicketInfo;

@Repository
public interface TicketInfoRepo extends CrudRepository<TicketInfo, Long> {

	@Query(value = "select * from ticket_info t where t.recvd_Date between :torecvdDate and :frmrecvdDate" , nativeQuery = true)
	public List<TicketInfo> searchByDate(@Param("torecvdDate") String torecvdDate,@Param("frmrecvdDate") String frmrecvdDate);
	
	@Query(value = "select * from ticket_info t where t.recvd_Date between :torecvdDate and :frmrecvdDate and t.owner= :userName" , nativeQuery = true)
	public List<TicketInfo> searchByDateForUser(@Param("torecvdDate") String torecvdDate,@Param("frmrecvdDate") String frmrecvdDate,@Param("userName")String userName);

	@Query(value = "select * from ticket_info t where UPPER(t.status) = 'WIP'  ", nativeQuery = true)
	public List<TicketInfo> searchByStatus();

	@Query(value = "SELECT a.*\r\n" + "FROM TICKET_INFO a\r\n" + "JOIN (SELECT TOPS_NUMBER, RECVD_DATE, COUNT(*)\r\n"
			+ "FROM TICKET_INFO\r\n" + "GROUP BY TOPS_NUMBER, RECVD_DATE\r\n" + "HAVING count(*) > 1 ) b\r\n"
			+ "ON a.TOPS_NUMBER = b.TOPS_NUMBER\r\n" + "AND a.RECVD_DATE = b.RECVD_DATE\r\n"
			+ "ORDER BY a.TOPS_NUMBER", nativeQuery = true)
	public List<TicketInfo> searchForDuplicateRecords();

	@Query(value = "select * from ticket_info t where t.recvd_Date between :preSaturday and :nextFriday", nativeQuery = true)
	public List<TicketInfo> searchForPreviousWeekRecord(@Param("preSaturday") String preSaturday,@Param("nextFriday") String nextFriday);
	
	@Query(value = "select * from ticket_info t where  UPPER(t.status) = 'WIP' and t.owner= :userName", nativeQuery = true)
	public List<TicketInfo> searchWipByUser(@Param("userName") String userName);

	@Query(value="Select * from ticket_info WHERE owner IN (SELECT user_name FROM user_login where project_name=:projectName)", nativeQuery = true)
	public List<TicketInfo> searchTicketByProject(String projectName);
	
	@Query(value = "select * from ticket_info t where t.owner= :userName", nativeQuery = true)
	public List<TicketInfo> downloadUserSpecificTickets(String userName);

	@Query(value = "SELECT a.*\r\n" + "FROM TICKET_INFO a\r\n" + "JOIN (SELECT TOPS_NUMBER, RECVD_DATE, COUNT(*)\r\n"
			+ "FROM TICKET_INFO\r\n" + "GROUP BY TOPS_NUMBER, RECVD_DATE\r\n" + "HAVING count(*) > 1 ) b\r\n"
			+ "ON a.TOPS_NUMBER = b.TOPS_NUMBER\r\n" + "AND a.RECVD_DATE = b.RECVD_DATE  \r\n"+" AND a.owner= :userName\r\n"
			+ "ORDER BY a.TOPS_NUMBER", nativeQuery = true)
	public List<TicketInfo> searchDuplicateByUser(@Param("userName") String userName);

	

}

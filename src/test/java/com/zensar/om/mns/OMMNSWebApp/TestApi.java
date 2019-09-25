/*
 * package com.zensar.om.mns.OMMNSWebApp;
 * 
 * import static org.junit.Assert.assertEquals; import static
 * org.mockito.Mockito.when; import java.util.ArrayList; import java.util.List;
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.mockito.ArgumentCaptor; import org.mockito.InjectMocks; import
 * org.mockito.Mock; import
 * org.springframework.test.context.junit4.SpringRunner; import
 * com.zensar.om.mns.entity.Classification; import
 * com.zensar.om.mns.entity.FunctionalArea; import
 * com.zensar.om.mns.entity.Owner; import com.zensar.om.mns.entity.RcCategory;
 * import com.zensar.om.mns.entity.ReasonForHelp; import
 * com.zensar.om.mns.entity.SmeName; import
 * com.zensar.om.mns.entity.SubFunction; import
 * com.zensar.om.mns.entity.TicketInfo; import
 * com.zensar.om.mns.repository.FunctionalAreaRepo; import
 * com.zensar.om.mns.repository.OwnerRepo; import
 * com.zensar.om.mns.repository.RcCategoryRepo; import
 * com.zensar.om.mns.repository.ReasonForHelpRepo; import
 * com.zensar.om.mns.repository.SmeNameRepo; import
 * com.zensar.om.mns.repository.SubFunctionalRepo; import
 * com.zensar.om.mns.repository.TicketInfoRepo; import
 * com.zensar.om.mns.repository.TicketRepository; import
 * com.zensar.om.mns.service.TicketServiceImpl; import static
 * org.mockito.Mockito.*;
 * 
 * @RunWith(SpringRunner.class) public class TestApi {
 * 
 * @InjectMocks TicketServiceImpl ticketService;
 * 
 * @Mock TicketRepository ticketRepository;
 * 
 * @Mock TicketInfoRepo ticketInfoRepo;
 * 
 * @Mock FunctionalAreaRepo functionalAreaRepo;
 * 
 * @Mock SubFunctionalRepo subFunctionalRepo;
 * 
 * @Mock OwnerRepo ownerRepo;
 * 
 * @Mock SmeNameRepo smeNameRepo;
 * 
 * @Mock ReasonForHelpRepo reasonForHelpRepo;
 * 
 * @Mock RcCategoryRepo rcCategoryRepo;
 * 
 * 
 * 
 * @Test public void getTableData(){ List<Classification> list = new
 * ArrayList<Classification>(); Classification c1=new Classification();
 * c1.setClassificationData("a"); Classification c2=new Classification();
 * c2.setClassificationData("b"); list.add(c1); list.add(c2);
 * when(ticketRepository.findAll()).thenReturn(list); List<Classification>
 * classificationList = ticketService.getTableData(); assertEquals(2,
 * classificationList.size()); }
 * 
 * @Test public void getTicketByStatus(){
 * 
 * TicketInfo ticketInfo=new TicketInfo("aaa", "bbb", "ccc", "ddd", "eee",
 * "fff", "ggg", "hh", "ii", "jj", "kk", "ll", "mm", "nn", "o", "p", "q", "r",
 * 1, 2, 3, 11); List<TicketInfo> listOfTicket=new ArrayList<TicketInfo>();
 * listOfTicket.add(ticketInfo);
 * when(ticketInfoRepo.searchByStatus()).thenReturn(listOfTicket);
 * assertEquals(1,ticketService.getAllTickets().size()); }
 * 
 * @Test public void SearchTicketByDate(){
 * 
 * TicketInfo ticketInfo1=new TicketInfo("1", "bbb", "ccc", "26-02-2019",
 * "26-02-2019", "26-02-2019", "ggg", "hh", "ii", "jj", "kk", "ll", "mm", "nn",
 * "o", "p", "q", "r", 1, 2, 3, 11); TicketInfo ticketInfo2=new TicketInfo("2",
 * "bbb", "ccc", "20-02-2019", "20-02-2019", "20-02-2019", "ggg", "hh", "ii",
 * "jj", "kk", "ll", "mm", "nn", "o", "p", "q", "r", 1, 2, 3, 11); TicketInfo
 * ticketInfo3=new TicketInfo("3", "bbb", "ccc", "24-02-2019", "24-02-2019",
 * "26-02-2019", "ggg", "hh", "ii", "jj", "kk", "ll", "mm", "nn", "o", "p", "q",
 * "r", 1, 2, 3, 11); List<TicketInfo> listOfTicket=new ArrayList<TicketInfo>();
 * listOfTicket.add(ticketInfo1); listOfTicket.add(ticketInfo3);
 * when(ticketInfoRepo.searchByDate("21-02-2019",
 * "27-02-2019")).thenReturn(listOfTicket); assertEquals(2,
 * ticketService.searchByDate("21-02-2019", "27-02-2019").size());
 * assertEquals(listOfTicket, ticketService.searchByDate("21-02-2019",
 * "27-02-2019")); }
 * 
 * @Test public void getDuplicateTickets() {
 * 
 * TicketInfo ticketInfo1=new TicketInfo("1", "bbb", "ccc", "26-02-2019",
 * "26-02-2019", "26-02-2019", "ggg", "hh", "ii", "jj", "kk", "ll", "mm", "nn",
 * "o", "p", "q", "r", 1, 2, 3, 11); TicketInfo ticketInfo2=new TicketInfo("1",
 * "bbb", "ccc", "26-02-2019", "20-02-2019", "20-02-2019", "ggg", "hh", "ii",
 * "jj", "kk", "ll", "mm", "nn", "o", "p", "q", "r", 1, 2, 3, 11);
 * List<TicketInfo> listOfTicket=new ArrayList<TicketInfo>();
 * listOfTicket.add(ticketInfo1); listOfTicket.add(ticketInfo2);
 * when(ticketInfoRepo.searchForDuplicateRecords()).thenReturn(listOfTicket);
 * assertEquals(2, ticketService.getDuplicateTickets().size());
 * assertEquals(listOfTicket, ticketService.getDuplicateTickets()); }
 * 
 * @Test public void getFunctionalAreaData(){ List<FunctionalArea> list = new
 * ArrayList<FunctionalArea>(); FunctionalArea f1=new FunctionalArea();
 * f1.setFunctionalAreaData("a"); FunctionalArea f2=new FunctionalArea();
 * f2.setFunctionalAreaData("b"); list.add(f1); list.add(f2);
 * when(functionalAreaRepo.findAll()).thenReturn(list); List<FunctionalArea>
 * functionalAreaList = ticketService.getFunctionalAreaData(); assertEquals(2,
 * functionalAreaList.size()); }
 * 
 * @Test public void getSubFunctionData(){ List<SubFunction> list = new
 * ArrayList<SubFunction>(); SubFunction s1=new SubFunction();
 * s1.setSubFunctionData("a"); SubFunction s2=new SubFunction();
 * s2.setSubFunctionData("b"); list.add(s1); list.add(s2);
 * when(subFunctionalRepo.findAll()).thenReturn(list); List<SubFunction>
 * subFunctionList = ticketService.getSubFunctionData(); assertEquals(2,
 * subFunctionList.size()); }
 * 
 * 
 * @Test public void getOwnerData(){ List<Owner> list = new ArrayList<Owner>();
 * Owner o1=new Owner(); o1.setOwnerData("a"); Owner o2=new Owner();
 * o2.setOwnerData("b"); list.add(o1); list.add(o2);
 * when(ownerRepo.findAll()).thenReturn(list); List<Owner> ownerList =
 * ticketService.getOwnerData(); assertEquals(2, ownerList.size()); }
 * 
 * @Test public void getReasonForHelpData(){ List<ReasonForHelp> list = new
 * ArrayList<ReasonForHelp>(); ReasonForHelp o1=new ReasonForHelp();
 * o1.setReasonForHelpData("a"); ReasonForHelp o2=new ReasonForHelp();
 * o2.setReasonForHelpData("b"); list.add(o1); list.add(o2);
 * when(reasonForHelpRepo.findAll()).thenReturn(list); List<ReasonForHelp>
 * reasonForHelpList = ticketService.getReasonForHelpData(); assertEquals(2,
 * reasonForHelpList.size()); }
 * 
 * 
 * 
 * @Test public void getRcCategoryData(){ List<RcCategory> list = new
 * ArrayList<RcCategory>(); RcCategory o1=new RcCategory();
 * o1.setRcCategoryData("a"); RcCategory o2=new RcCategory();
 * o2.setRcCategoryData("b"); list.add(o1); list.add(o2);
 * when(rcCategoryRepo.findAll()).thenReturn(list); List<RcCategory>
 * RcCategoryList = ticketService.getRcCategoryData(); assertEquals(2,
 * RcCategoryList.size()); }
 * 
 * 
 * @Test public void getSmeNameData(){ List<SmeName> list = new
 * ArrayList<SmeName>(); SmeName o1=new SmeName(); o1.setSmeNameData("a");
 * SmeName o2=new SmeName(); o2.setSmeNameData("b"); list.add(o1); list.add(o2);
 * when(smeNameRepo.findAll()).thenReturn(list); List<SmeName> RcCategoryList =
 * ticketService.getSmeNameData(); assertEquals(2, RcCategoryList.size()); }
 * 
 * @Test public void saveTickectInfo() {
 * 
 * TicketInfo ticketInfo1=new TicketInfo("1", "bbb", "ccc", "26-02-2019",
 * "26-02-2019", "26-02-2019", "ggg", "hh", "ii", "jj", "kk", "ll", "mm", "nn",
 * "o", "p", "q", "r", 1, 2, 3, 11);
 * when(ticketInfoRepo.save(any(TicketInfo.class))).thenReturn(ticketInfo1);
 * 
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */
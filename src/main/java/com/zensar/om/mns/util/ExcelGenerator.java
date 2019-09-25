package com.zensar.om.mns.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.zensar.om.mns.entity.TicketInfo;

public class ExcelGenerator {

	
	public static ByteArrayInputStream ticketsToExcel(List<TicketInfo> ticketinfo) throws IOException {

		System.out.println("EXCEL GEN");
		String[] COLUMNs = { "SEQUENCE_NO", "TOPS_NUMBER", "PROLEM_DESC", "RECVD_DATE", "PICKED_DATE", "CLOSED_DATE",
				"STATUS", "CLASSIFICATION", "FUNCTIONAL_AREA", "SUB_FUNCTION", "OWNER", "TYPE_OF_TICKET", "SME_HELP",
				"SME_NAME", "REASON_FOR_HELP", "RESOLUTION", "PRIORITY", "COMPLEXITY", "RC_CATEGORY", "ROOT_CAUSE",
				"PERM_FIX_APP", "REMARKS","ACTUAL TIME TAKEN" };
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();
			Sheet sheet = workbook.createSheet("Ticket_Information");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			CellStyle cellStyle = workbook.createCellStyle();
			CreationHelper createHelper1 = workbook.getCreationHelper();
			short dateFormat = createHelper1.createDataFormat().getFormat("yyyy-dd-MM");
			cellStyle.setDataFormat(dateFormat);

			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowIdx = 1;
			for (TicketInfo ticketInfo : ticketinfo) {
				
				Row row = sheet.createRow(rowIdx++);
				System.out.println("ROW "+row);
				
				row.createCell(0).setCellValue(ticketInfo.getSequenceNo());
				System.out.println("ticketInfo.getSequenceNo()"+ticketInfo.getSequenceNo());
				row.createCell(1).setCellValue(ticketInfo.getTopsNumber());
				row.createCell(2).setCellValue(ticketInfo.getProblemDesc());
				Cell cell = row.createCell(3);
				cell.setCellValue(ticketInfo.getRecvdDate());
				cell.setCellStyle(cellStyle);
				row.createCell(3).setCellValue(ticketInfo.getRecvdDate());
				row.createCell(4).setCellValue(ticketInfo.getPickedDate());
				row.createCell(5).setCellValue(ticketInfo.getClosedDate());
				row.createCell(6).setCellValue(ticketInfo.getStatus());
				row.createCell(7).setCellValue(ticketInfo.getClasification());
				row.createCell(8).setCellValue(ticketInfo.getFunctionalArea());
				row.createCell(9).setCellValue(ticketInfo.getSubFunction());
				row.createCell(10).setCellValue(ticketInfo.getOwner());
				row.createCell(11).setCellValue(ticketInfo.getTypeOfTicket());
				row.createCell(12).setCellValue(ticketInfo.getSmeHelp());
				row.createCell(13).setCellValue(ticketInfo.getSmeName());
				row.createCell(14).setCellValue(ticketInfo.getReasonForHelp());
				row.createCell(15).setCellValue(ticketInfo.getResolution());
				row.createCell(16).setCellValue(ticketInfo.getPriority());
				row.createCell(17).setCellValue(ticketInfo.getComplexity());
				row.createCell(18).setCellValue(ticketInfo.getRcCategory());
				row.createCell(19).setCellValue(ticketInfo.getRootCause());
				row.createCell(20).setCellValue(ticketInfo.getPermFixApp());
				row.createCell(21).setCellValue(ticketInfo.getRemarks());
				row.createCell(22).setCellValue(ticketInfo.getActualTimeTaken());
			}
			System.out.println("END ");
		
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}

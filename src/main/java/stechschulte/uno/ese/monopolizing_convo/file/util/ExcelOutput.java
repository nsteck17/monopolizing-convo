package stechschulte.uno.ese.monopolizing_convo.file.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelOutput {
    public static void createExcelFile(Date currentDate,
                                       List<String> commentToxicAnalysisList,
                                       int version) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Run Data");

        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Original Comment");
        Cell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue("Is Original Toxic");
        Cell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue("ChatGPT Reply");
        Cell headerCell3 = headerRow.createCell(3);
        headerCell3.setCellValue("Is Reply Toxic");
        Cell headerCell4 = headerRow.createCell(4);
        headerCell4.setCellValue("Is Toxic Unsure");
        Cell headerCell5 = headerRow.createCell(5);
        headerCell5.setCellValue("Evaluation Value");
        Cell headerCell6 = headerRow.createCell(6);
        headerCell6.setCellValue("Error Reason");

        //Iterate over commentToxicAnalysisList and write to excel each row
        for (int i = 0; i < commentToxicAnalysisList.size(); i++) {
            //CommentToxicAnalysis commentToxicAnalysis = commentToxicAnalysisList.get(i);
            Row dataRow = sheet.createRow(i + 1);
            Cell dataCell = dataRow.createCell(0);
            //dataCell.setCellValue(commentToxicAnalysis.getOriginalComment());
            Cell dataCell1 = dataRow.createCell(1);
            //dataCell1.setCellValue(commentToxicAnalysis.isOriginalToxic());
            Cell dataCell2 = dataRow.createCell(2);
            //dataCell2.setCellValue(commentToxicAnalysis.getReplyFromChatGpt());
            Cell dataCell3 = dataRow.createCell(3);
            //dataCell3.setCellValue(commentToxicAnalysis.isReplyToxic());
            Cell dataCell4 = dataRow.createCell(4);
            //dataCell4.setCellValue(commentToxicAnalysis.isToxicUnsure());
            Cell dataCell5 = dataRow.createCell(5);
            //dataCell5.setCellValue(commentToxicAnalysis.getEvaluationValue().toString());
            Cell dataCell6 = dataRow.createCell(6);
            //dataCell6.setCellValue(commentToxicAnalysis.getErrorReason().toString());
        }

		/*
		Row dataRow = sheet.createRow(1);
		Cell dataCell = dataRow.createCell(0);
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		dataCell.setCellValue(currentDateTime);
		*/

        String fileName = "RunData_"
                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
                + "_v" + version
                + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}

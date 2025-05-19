package stechschulte.uno.ese.monopolizing_convo.file.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageConvoLengthResultsVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MonopolizedConvoVO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelOutput {
    public static void createExcelFileConvoResults(Date currentDate,
                                       List<MessageConvoLengthResultsVO> resultList,
                                       String filePath) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Run Data");

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Nbr Of Msgs");

        Cell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue("Cnt of Next Msg Is Same");

        Cell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue("Cnt of Next Msg Is New User");

        Cell headerCell3 = headerRow.createCell(3);
        headerCell3.setCellValue("Total Cnt of Instances");


        //Iterate over commentToxicAnalysisList and write to excel each row
        for (int i = 0; i < resultList.size(); i++) {
            MessageConvoLengthResultsVO result = resultList.get(i);
            Row dataRow = sheet.createRow(i + 1);

            Cell dataCell = dataRow.createCell(0);
            dataCell.setCellValue(result.getNumberOfMessagesBetweenGroup());
            Cell dataCell1 = dataRow.createCell(1);
            dataCell1.setCellValue(result.getNumberOfNextUserSame());
            Cell dataCell2 = dataRow.createCell(2);
            dataCell2.setCellValue(result.getNumberOfNextUserNew());
            Cell dataCell3 = dataRow.createCell(3);
            dataCell3.setCellValue(
                    result.getNumberOfNextUserSame()
                    + result.getNumberOfNextUserNew()
            );
        }


        String fileName =
                filePath
                + "Data_"
                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
                + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    public void createExcelFileConvoDurationResults(
            Date programRunDate,
            List<MonopolizedConvoVO> monoConvoList,
            String filePath) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Run Data Mono Convo");

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Convo Nbr");

        Cell headerCell1 = headerRow.createCell(1);
        headerCell1.setCellValue("GroupSize");

        Cell headerCell2 = headerRow.createCell(2);
        headerCell2.setCellValue("Nbr of Msg in Convo");

        Cell headerCell3 = headerRow.createCell(3);
        headerCell3.setCellValue("UserList");

        Cell headerCell4 = headerRow.createCell(4);
        headerCell4.setCellValue("Duration of Convo (seconds)");

        Cell headerCell5 = headerRow.createCell(5);
        headerCell5.setCellValue("Duration until Next User Msg (seconds)");

        Cell headerCell6 = headerRow.createCell(6);
        headerCell6.setCellValue("Duration of Convo (txt)");

        Cell headerCell7 = headerRow.createCell(7);
        headerCell7.setCellValue("Duration until Next User Msg (txt)");

        //Iterate over commentToxicAnalysisList and write to excel each row
        for (int i = 0; i < monoConvoList.size(); i++) {
            MonopolizedConvoVO result = monoConvoList.get(i);
            Row dataRow = sheet.createRow(i + 1);

            Cell dataCell = dataRow.createCell(0);
            dataCell.setCellValue(i);

            Cell dataCell1 = dataRow.createCell(1);
            dataCell1.setCellValue(result.getGroupSize());

            Cell dataCell2 = dataRow.createCell(2);
            dataCell2.setCellValue(result.getMonopolizedConversationList().size());

            Cell dataCell3 = dataRow.createCell(3);
            dataCell3.setCellValue(result.getGroupUserList().toString());

            Cell dataCell4 = dataRow.createCell(4);
            dataCell4.setCellValue(result.getDurationOfMonopolizedConversation().getSeconds());

            Cell dataCell5 = dataRow.createCell(5);
            dataCell5.setCellValue(result.getDurationUntilNextUserAdded().getSeconds());

            Cell dataCell6 = dataRow.createCell(6);
            dataCell6.setCellValue(FormatUtil.formatDuration(
                    result.getDurationOfMonopolizedConversation()));

            Cell dataCell7 = dataRow.createCell(7);
            dataCell7.setCellValue(FormatUtil.formatDuration(
                    result.getDurationUntilNextUserAdded()));
        }


        String fileName =
                filePath
                        + "MonoConvoDurationData_"
                        + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
                        + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
}

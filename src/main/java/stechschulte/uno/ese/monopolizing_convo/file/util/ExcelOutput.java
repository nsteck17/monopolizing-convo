package stechschulte.uno.ese.monopolizing_convo.file.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageConvoLengthResultsVO;

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
}

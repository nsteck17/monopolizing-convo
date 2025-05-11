package stechschulte.uno.ese.monopolizing_convo.file.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stechschulte.uno.ese.monopolizing_convo.vo.AnalysisSummaryVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageAnalysisVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MonopolizedConvoVO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TextFileExport {

    private static final Logger logger = LoggerFactory.getLogger(TextFileExport.class);


    public static void writeStringToFile(String content,
                                         String fileName,
                                         String filePath,
                                         Date dateOf
    ) throws IOException {
        //output the parameters
        System.out.println("Writing to file: "+filePath
                + fileName
                + "_"
                + new SimpleDateFormat("yyyyMMdd_HHmmss").format(dateOf)
                + ".txt");
        //Create the file
        try (FileWriter fileWriter = new FileWriter(
                filePath
                        + fileName
                        + "_"
                        + new SimpleDateFormat("yyyyMMdd_HHmmss").format(dateOf)
                        + ".txt")) {
            fileWriter.write(content);
        } catch (Exception e){
            logger.error("Exception occurred while writing to file: " + e.getMessage());
        }
    }

    public String prepareMonopolizedConvoOutput(List<MonopolizedConvoVO> monoConvoList) {
        String output = "";
        boolean isConvoBreakdownOutputEnabled = false;
        for(MonopolizedConvoVO convo : monoConvoList){
            if(convo.getMonopolizedConversationList().size() >= 39 && isConvoBreakdownOutputEnabled) {
                logger.info("--------------------------------------------------");
                logger.info("Group Size: " + convo.getGroupSize());
                logger.info("Group Users: " + convo.getGroupUserList());
                logger.info("Messages Size: " + convo.getMonopolizedConversationList().size());
                logger.info("Duration of Monopolized Convo: " + FormatUtil.formatDuration(convo.getDurationOfMonopolizedConversation()));
                logger.info("Duration Until Next User Added: " + FormatUtil.formatDuration(convo.getDurationUntilNextUserAdded()));
                logger.info("Char Length of Text Msg Before New User Added: " + convo.getLengthOfTextMsgBeforeNewUserAdded());
                int i = 1;
                for (MessageAnalysisVO msg : convo.getMonopolizedConversationList()) {
                    logger.info("\t " + i + " Msg: " + msg);
                    i++;
                }
                logger.info("--------------------------------------------------");
            }
        }

        int counter = 0;
        int total = monoConvoList.size();

        for(MonopolizedConvoVO convo : monoConvoList){
            if (counter % (total / 100) == 0) {
                System.out.println("Saving to file... " + counter + " of " + total+ " (" + (counter * 100 / total) + "% complete)");
            }
            counter++;
            //To save time, only capture if the conversations are over a certain size
            if(convo.getMonopolizedConversationList().size() >= 39) {
                output += "--------------------------------------------------" + "\n";
                output += "Group Size: " + convo.getGroupSize() + "\n";
                output += "Group Users: " + convo.getGroupUserList() + "\n";
                output += "Messages Size: " + convo.getMonopolizedConversationList().size() + "\n";
                output += "Duration of Monopolized Convo: " + FormatUtil.formatDuration(convo.getDurationOfMonopolizedConversation()) + "\n";
                output += "Duration Until Next User Added: " + FormatUtil.formatDuration(convo.getDurationUntilNextUserAdded()) + "\n";
                output += "Char Length of Text Msg Before New User Added: " + convo.getLengthOfTextMsgBeforeNewUserAdded() + "\n";
                int i = 1;
                for (MessageAnalysisVO msg : convo.getMonopolizedConversationList()) {
                    output += "\t " + i + " Msg: " + msg + "\n";
                    i++;
                }
                output += "--------------------------------------------------" + "\n";
            }
        }
        return output;
    }

    public void prepareTextFileOutputDirectory(String folderPath) {
        Path path = Paths.get(folderPath);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                System.out.println("Directory created successfully: " + path.toString());
            } else {
                System.out.println("Directory already exists: " + path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String prepareSummaryOutput(AnalysisSummaryVO summaryObject) {
        String output = "";
        output += "Program Type: " + summaryObject.getProgramType() + "\n";
        output += "Output Folder: " + "output/" + summaryObject.getFolderName() + "\n";
        output += "Total Messages: " + summaryObject.getTotalMessages() + "\n";
        output += "Group Size: " + summaryObject.getGroupSize() + "\n";

        return output;
    }
}

package stechschulte.uno.ese.monopolizing_convo.vo;

import java.util.Date;
import java.util.HashMap;

public class AnalysisSummaryVO {

    String programType = "DEFAULT";
    String folderName = "";
    Integer totalMessages;
    Integer groupSize;

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public Integer getTotalMessages() {
        return totalMessages;
    }

    public void setTotalMessages(Integer totalMessages) {
        this.totalMessages = totalMessages;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}

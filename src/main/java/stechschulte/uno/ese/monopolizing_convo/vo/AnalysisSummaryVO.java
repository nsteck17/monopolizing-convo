package stechschulte.uno.ese.monopolizing_convo.vo;

import java.util.Date;
import java.util.HashMap;

public class AnalysisSummaryVO {
    Date analysisStartDate;
    Date analysisEndDate;

    String inputObjectType;
    String inputDomainDetails;
    String fileStartDate;
    String fileEndDate;

    Integer totalMessages;
    Integer totalUsers;
    HashMap<String, Integer> userMessageCountMap;

}

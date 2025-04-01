package stechschulte.uno.ese.monopolizing_convo.file.util;

import stechschulte.uno.ese.monopolizing_convo.vo.AnalysisSummaryVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageConvoLengthResultsVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MonopolizedConvoVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisProcessor {

    public List<MessageConvoLengthResultsVO> processMonopolizedConversations(List<MonopolizedConvoVO> monoConvoList) {
        System.out.println("Starting to process Monopolized Conversations");
        //Scenarios to consider .... should write unit tests!

        // A1->B1 = no processing needed
        // A1->B1->A2 = A1->B1 and A1->B1->A2 (did not know A2 was going to be added, so count it)
            // A1 -> B1 = SAME (we know next is A2 and thus size of msg of 2 counted as 'SAME')
            // A1 -> B1 -> A2 = NEW (we know next after A2 is NEW person)
        // A1->B1->A2->B2 = A1->B1 and A1->B1->A2 and A1->B1->A2->B2

        // A1->A2->B1 = ??
        // (A) do we compress A1 and A2 into A*->B1 (thus 2 msgs)
        // (B) or do we count A1->A2->B1 only? (thus 3 msgs)
        // (C) count A1->A2 alone (#1) and also A1->A2->A3 (#2) (two instances)

        //Are we measuring the # of messages and thus "B" or "C"?
        // or # of back and forth and thus and thus "A"
        // ASSUMING "C" to keep it simple and consistent

        //Object to store the results
        // Map<NumberOfMsgs,NumberOfOccurances>
        Map<Integer,Integer> numberOfMsgToCountOfSameMap = new HashMap<Integer,Integer>();

        // ???? - should maybe use 2 instead of groupSize below because A1->A2 count same as A1->B1

        int groupSize = 0;
        for(MonopolizedConvoVO convo : monoConvoList){
            groupSize = convo.getGroupSize();
            //System.out.println("Processing convo: "+convo);

            //If convo size of 5 then we know to add 4,3,2
            // 5 would be added to "new" count in next loop
            int backAndForthMsgLength = convo.getMonopolizedConversationList().size();
            groupSize = convo.getGroupSize();
            for(int i = backAndForthMsgLength-1; i >= groupSize; i-- ){
                //System.out.println("Processing i: "+i);

                if(numberOfMsgToCountOfSameMap.containsKey(i)){
                    numberOfMsgToCountOfSameMap.put(i,numberOfMsgToCountOfSameMap.get(i)+1);
                }else{
                    numberOfMsgToCountOfSameMap.put(i,1);
                }
                i--;
            }
        }

        Map<Integer,Integer> numberOfMsgToCountOfNewMap = new HashMap<Integer,Integer>();
        //Count different - this is by default the size of each convo object
        for(MonopolizedConvoVO convo : monoConvoList){
            if(numberOfMsgToCountOfNewMap.containsKey(convo.getMonopolizedConversationList().size())){
                numberOfMsgToCountOfNewMap.put(convo.getMonopolizedConversationList().size(),numberOfMsgToCountOfNewMap.get(convo.getMonopolizedConversationList().size())+1);
            }else{
                numberOfMsgToCountOfNewMap.put(convo.getMonopolizedConversationList().size(),1);
            }
        }

        //print out each map size
        List<MessageConvoLengthResultsVO> msgConvoLengthResultsList = new ArrayList<MessageConvoLengthResultsVO>();
        for(Map.Entry<Integer,Integer> entry : numberOfMsgToCountOfSameMap.entrySet()){
            MessageConvoLengthResultsVO msgConvoLengthResults = new MessageConvoLengthResultsVO();
            msgConvoLengthResults.setGroupSize(groupSize);
            msgConvoLengthResults.setNumberOfMessagesBetweenGroup(entry.getKey());
            msgConvoLengthResults.setNumberOfNextUserSame(entry.getValue());
            msgConvoLengthResults.setNumberOfNextUserNew(numberOfMsgToCountOfNewMap.getOrDefault(entry.getKey(),0));
            msgConvoLengthResultsList.add(msgConvoLengthResults);
        }

        return msgConvoLengthResultsList;
    }

}
package stechschulte.uno.ese.monopolizing_convo.file.util;

import stechschulte.uno.ese.monopolizing_convo.vo.MessageAnalysisVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MonopolizedConvoVO;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MonopolizingCalculator {

    /**
     * Analyze the thread for monopolization
     * NOTE: This assumes the list is sorted in order of sentTime already!
     * @param msgList - all the standardized messages to analyze
     * @param monopolizationGroupSize - number of people in a monopolized conversation analysis
     */
    public List<MonopolizedConvoVO> analyzeThreadForMonopolization(List<MessageAnalysisVO> msgList, Integer monopolizationGroupSize) {

        List<MonopolizedConvoVO> monoConvoList = new ArrayList<MonopolizedConvoVO>();
        MonopolizedConvoVO monoConvo = new MonopolizedConvoVO();

        //Loop through the list of messages
        //Start Capturing the messages while X number of people are talking before X + 1 is added
        //Don't i++ because we will control iteration in the loop
        for(int i = 0; i < msgList.size(); ) {

            //This is a new monopolizing conversation - so we can add the message and start things
            if(monoConvo.getGroupSize() == 0){
                monoConvo.addUserToConvo(msgList.get(i).getSender());
                monoConvo.addMessage(msgList.get(i));
                i++;//Next Msg
            }
            //Have we reached the group size? If not, add the message and user
            else if(monoConvo.getGroupSize() < monopolizationGroupSize){
                monoConvo.addUserToConvo(msgList.get(i).getSender());
                monoConvo.addMessage(msgList.get(i));
                i++;//Next Msg
            }
            //We have reached the group size, so we need to check if the next message is from the same user
            else {
                if(monoConvo.getGroupUserList().contains(msgList.get(i).getSender())){
                    monoConvo.addMessage(msgList.get(i));
                    i++;//Next Msg
                }else{
                    //Help verify we're at a new person
                    //System.out.println(monoConvo.getGroupUserList()+" vs "+msgList.get(i).getSender());

                    //Capture the latest time of the latest message vs this new msg convo
                    monoConvo.setDateOfNextUserAdded(msgList.get(i).getSentTime());

                    // Get the duration from the last monopolized convo and the new user msg
                    // NOTE: This assumes added and sorted in order of time already
                    Instant lastMonopolizedConvoMsgTime = monoConvo.getMonopolizedConversationList().get(monoConvo.getMonopolizedConversationList().size()-1).getSentTime().toInstant();
                    monoConvo.setDurationUntilNextUserAdded(
                            Duration.between(
                                    lastMonopolizedConvoMsgTime,
                                    msgList.get(i).getSentTime().toInstant()));

                    // Calculate the duration of the monopolized conovo
                    // NOTE: This assumes added and sorted in order of time already
                    Instant firstMonopolizedConvoMsgTime = monoConvo.getMonopolizedConversationList().get(0).getSentTime().toInstant();
                    monoConvo.setDurationOfMonopolizedConversation(Duration.between(firstMonopolizedConvoMsgTime, lastMonopolizedConvoMsgTime));

                    // Save the length of the text message before the new user was added
                    // NOTE: This assumes added and sorted in order of time already
                    monoConvo.setLengthOfTextMsgBeforeNewUserAdded(monoConvo.getMonopolizedConversationList().get(monoConvo.getMonopolizedConversationList().size()-1).getText().length());

                    //We have a new user, so we need to reset the monopolized conversation
                    monoConvoList.add(monoConvo);
                    monoConvo = new MonopolizedConvoVO();

                    //Don't iterate index 'i' and it'll start new and process correctly
                }
            }
        }
        return monoConvoList;
    }


}

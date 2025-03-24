package stechschulte.uno.ese.monopolizing_convo.vo;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class MonopolizedConvoVO {

    Integer groupSize = 0;
    Set<String> groupUserList = new HashSet<String>();
    List<MessageAnalysisVO> monopolizedConversationList = new ArrayList<MessageAnalysisVO>();
    Date dateOfNextUserAdded;
    Duration durationUntilNextUserAdded;
    Duration durationOfMonopolizedConversation;
    Integer lengthOfTextMsgBeforeNewUserAdded = 0;

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    public Set<String> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(Set<String> groupUserList) {
        this.groupUserList = groupUserList;
    }

    public List<MessageAnalysisVO> getMonopolizedConversationList() {
        return monopolizedConversationList;
    }

    public void setMonopolizedConversationList(List<MessageAnalysisVO> monopolizedConversationList) {
        this.monopolizedConversationList = monopolizedConversationList;
    }

    public Duration getDurationUntilNextUserAdded() {
        return durationUntilNextUserAdded;
    }

    public void setDurationUntilNextUserAdded(Duration durationUntilNextUserAdded) {
        this.durationUntilNextUserAdded = durationUntilNextUserAdded;
    }

    public Duration getDurationOfMonopolizedConversation() {
        return durationOfMonopolizedConversation;
    }

    public void setDurationOfMonopolizedConversation(Duration durationOfMonopolizedConversation) {
        this.durationOfMonopolizedConversation = durationOfMonopolizedConversation;
    }

    public Date getDateOfNextUserAdded() {
        return dateOfNextUserAdded;
    }

    public void setDateOfNextUserAdded(Date dateOfNextUserAdded) {
        this.dateOfNextUserAdded = dateOfNextUserAdded;
    }

    public Integer getLengthOfTextMsgBeforeNewUserAdded() {
        return lengthOfTextMsgBeforeNewUserAdded;
    }

    public void setLengthOfTextMsgBeforeNewUserAdded(Integer lengthOfTextMsgBeforeNewUserAdded) {
        this.lengthOfTextMsgBeforeNewUserAdded = lengthOfTextMsgBeforeNewUserAdded;
    }

    @Override
    public String toString() {
        return "MonopolizedConvoVO{" +
                "groupSize=" + groupSize +
                ", groupUserList=" + groupUserList +
                ", monopolizedConversationList=" + monopolizedConversationList +
                ", dateOfNextUserAdded=" + dateOfNextUserAdded +
                ", durationUntilNextUserAdded=" + durationUntilNextUserAdded +
                ", durationOfMonopolizedConversation=" + durationOfMonopolizedConversation +
                ", lengthOfTextMsgBeforeNewUserAdded=" + lengthOfTextMsgBeforeNewUserAdded +
                '}';
    }

    public void addMessage(MessageAnalysisVO messageAnalysisVO) {
        this.monopolizedConversationList.add(messageAnalysisVO);
    }

    public void addUserToConvo(String sender) {
        this.groupUserList.add(sender);
        this.groupSize =  this.groupUserList.size();
    }
}

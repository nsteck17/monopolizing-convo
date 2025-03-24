package stechschulte.uno.ese.monopolizing_convo.vo;

import java.util.Date;

public class MessageAnalysisVO {
    /**
     * Optional: If disentanglement was performed to split concurrent conversations
     */
    Integer disentangledId;
    /**
     * Required: The text of the message/comment
     */
    String text;
    /**
     * Required: The username who sent the message/comment
     */
    String sender;
    /**
     * Required: The date and time the message/comment was sent
     */
    Date sentTime;

    public Integer getDisentangledId() {
        return disentangledId;
    }

    public void setDisentangledId(Integer disentangledId) {
        this.disentangledId = disentangledId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public String toString() {
        return "MessageAnalysisVO{" +
                "disentangledId=" + disentangledId +
                ", text='" + text + '\'' +
                ", sender='" + sender + '\'' +
                ", sentTime=" + sentTime +
                '}';
    }
}

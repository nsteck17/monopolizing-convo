package stechschulte.uno.ese.monopolizing_convo.vo;

public class MessageConvoLengthResultsVO {
    Integer numberOfMessagesBetweenGroup;
    Integer numberOfNextUserSame;
    Integer numberOfNextUserNew;
    Integer groupSize;

    public Integer getNumberOfMessagesBetweenGroup() {
        return numberOfMessagesBetweenGroup;
    }

    public void setNumberOfMessagesBetweenGroup(Integer numberOfMessagesBetweenGroup) {
        this.numberOfMessagesBetweenGroup = numberOfMessagesBetweenGroup;
    }

    public Integer getNumberOfNextUserSame() {
        return numberOfNextUserSame;
    }

    public void setNumberOfNextUserSame(Integer numberOfNextUserSame) {
        this.numberOfNextUserSame = numberOfNextUserSame;
    }

    public Integer getNumberOfNextUserNew() {
        return numberOfNextUserNew;
    }

    public void setNumberOfNextUserNew(Integer numberOfNextUserNew) {
        this.numberOfNextUserNew = numberOfNextUserNew;
    }

    public Integer getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(Integer groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    public String toString() {
        return "Number of Messages: "+numberOfMessagesBetweenGroup
                +" | Next Msg = SAME: "+numberOfNextUserSame
                +" | Next Msg = NEW: "+numberOfNextUserNew;
    }
}

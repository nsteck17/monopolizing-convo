package stechschulte.uno.ese.monopolizing_convo.file.util;

import stechschulte.uno.ese.monopolizing_convo.bindings.Discord;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageAnalysisVO;

import java.util.ArrayList;
import java.util.List;

public class DataTransformer {

    /**
     * Transforms the Discord object from the DISCO data set to a Standrd Message object
     * @param loadedObject - the DISCO file object that contains a list of messages
     * @return - a list of MessageAnalysisVO objects to analyze
     */
    public List<MessageAnalysisVO> transformDiscordToMonopolizedConvo(Discord loadedObject) {
        List<MessageAnalysisVO> messageAnalysisVOList = new ArrayList<MessageAnalysisVO>();
        for(Discord.Message entry : loadedObject.getMessage()){
            MessageAnalysisVO messageAnalysisVO = new MessageAnalysisVO();
            messageAnalysisVO.setSender(entry.getUser());
            messageAnalysisVO.setSentTime(entry.getTs().toGregorianCalendar().getTime());
            //WARNING: Could fail if not a number - many error handle
            //System.out.println(entry.getConversationId());
            messageAnalysisVO.setDisentangledId(Integer.parseInt(entry.getConversationId()));
            messageAnalysisVO.setText(entry.getText());
            messageAnalysisVOList.add(messageAnalysisVO);
        }
        return messageAnalysisVOList;
    }



}

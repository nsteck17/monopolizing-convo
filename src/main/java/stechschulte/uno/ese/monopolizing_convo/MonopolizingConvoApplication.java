package stechschulte.uno.ese.monopolizing_convo;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import stechschulte.uno.ese.monopolizing_convo.bindings.Discord;
import stechschulte.uno.ese.monopolizing_convo.file.util.DataTransformer;
import stechschulte.uno.ese.monopolizing_convo.file.util.FormatUtil;
import stechschulte.uno.ese.monopolizing_convo.file.util.MonopolizingCalculator;
import stechschulte.uno.ese.monopolizing_convo.file.util.XMLFileImport;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageAnalysisVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MonopolizedConvoVO;

import java.util.List;

@SpringBootApplication
public class MonopolizingConvoApplication {

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			System.out.println("Hello, World!");

			//Would need different import/transform for different inptus (github, slack, etc)

			//Step1a - Import XML file (may have different formats if from diff sources)
			XMLFileImport xmlFileImport = new XMLFileImport();
			Discord loadedObject = xmlFileImport.loadDiscordFromFileName("input/reduced-pythongeneralApr2020.xml.out");

			//Step2a - Transform to Standard Object (incase input changes)
			DataTransformer transformer = new DataTransformer();
			List<MessageAnalysisVO> messageList = transformer.transformDiscordToMonopolizedConvo(loadedObject);

			//Step3 - Break the thread into monopolized conversations
			MonopolizingCalculator calculator = new MonopolizingCalculator();
			List<MonopolizedConvoVO> monoConvoList = calculator.analyzeThreadForMonopolization(messageList, 2);

			//Step4 - Logical Validation

			//StepX - Output

			for(MonopolizedConvoVO convo : monoConvoList){
				System.out.println("--------------------------------------------------");
				System.out.println("Group Size: " + convo.getGroupSize());
				System.out.println("Group Users: " + convo.getGroupUserList());
				System.out.println("Messages Size: " + convo.getMonopolizedConversationList().size());
				System.out.println("Duration of Monopolized Convo: " + FormatUtil.formatDuration(convo.getDurationOfMonopolizedConversation()));
				System.out.println("Duration Until Next User Added: " + FormatUtil.formatDuration(convo.getDurationUntilNextUserAdded()));
				System.out.println("Char Length of Text Msg Before New User Added: " + convo.getLengthOfTextMsgBeforeNewUserAdded());
				for(MessageAnalysisVO msg : convo.getMonopolizedConversationList()){
					System.out.println("\t Msg: " + msg);
				}
				System.out.println("--------------------------------------------------");
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MonopolizingConvoApplication.class, args);
	}

}

package stechschulte.uno.ese.monopolizing_convo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import stechschulte.uno.ese.monopolizing_convo.bindings.Discord;
import stechschulte.uno.ese.monopolizing_convo.file.util.*;
import stechschulte.uno.ese.monopolizing_convo.vo.AnalysisSummaryVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageAnalysisVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MessageConvoLengthResultsVO;
import stechschulte.uno.ese.monopolizing_convo.vo.MonopolizedConvoVO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class MonopolizingConvoApplication {

private static final Logger logger = LoggerFactory.getLogger(MonopolizingConvoApplication.class);

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			logger.info("Hello, World!");
			Date programRunDate = new Date();
			AnalysisSummaryVO summaryObject = new AnalysisSummaryVO();

			//Would need different import/transform for different inptus (github, slack, etc)

			//Step0 - Present the user options
			UserInputUtility inputUtility = new UserInputUtility();
			inputUtility.promptUserOptions();
			int userChoice = inputUtility.getUserChoice();

			//Step1a - Import XML file (may have different formats if from diff sources)
			//switch case to load the correct XML files
			Discord loadedObject = new Discord();
			switch(userChoice){
				case 1:
					loadedObject = inputUtility.loadFullDataSetForClojurians();
					summaryObject.setProgramType("Clojurians");
					break;
				case 2:
					loadedObject = inputUtility.loadFullDataSetForGoLang();
					summaryObject.setProgramType("GoLang");
					break;
				case 3:
					loadedObject = inputUtility.loadFullDataSetForPythonGeneral();
					summaryObject.setProgramType("Python");
					break;
				case 4:
					loadedObject = inputUtility.loadFullDataSetForRacketGeneral();
					summaryObject.setProgramType("Racket");
					break;
				case 5:
					logger.info("You chose to quit");
					break;
				default:
					logger.info("Invalid choice. Please enter a number between 1 and 4.");
			}

			//folder name based on program type
			String currentDateTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(programRunDate);
			String folderName = summaryObject.getProgramType() + "_" + currentDateTime;
			summaryObject.setFolderName(folderName);

			//Step2a - Transform to Standard Object (incase input changes)
			DataTransformer transformer = new DataTransformer();
			List<MessageAnalysisVO> messageList = transformer.transformDiscordToMonopolizedConvo(loadedObject);
			summaryObject.setTotalMessages(messageList.size());

			//Step3 - Break the thread into monopolized conversations
			MonopolizingCalculator calculator = new MonopolizingCalculator();
			int groupSize = 3;
			List<MonopolizedConvoVO> monoConvoList = calculator.analyzeThreadForMonopolization(messageList, groupSize);
			summaryObject.setGroupSize(groupSize);

			//Step4 - Logical Validation (if needed)
			// Check if every monopolized convo has a group size expected, etc - if needed

			//Step5 - Analyze the Monopolized Conversations to break into metrics
			AnalysisProcessor analysisProcessor = new AnalysisProcessor();
			List<MessageConvoLengthResultsVO> msgConvoLengthList = analysisProcessor.processMonopolizedConversations(monoConvoList);

			//Step6 create folder so save will work
			TextFileExport textFileExport = new TextFileExport();
			textFileExport.prepareTextFileOutputDirectory("output/"+folderName+"/");

			//Step6 - Export the analysis summary
			System.out.println("Export the analysis summary");
			textFileExport.writeStringToFile(
					textFileExport.prepareSummaryOutput(summaryObject),
					"OverallSummary",
					"output/"+folderName+"/",
					programRunDate
			);

			//Step6a - Export Conversation Breakdown To Text File
			//NOTE: does not export every message. Function has clauses to only export some
			System.out.println("Export Conversation Breakdown To Text File");
			textFileExport.writeStringToFile(
					textFileExport.prepareMonopolizedConvoOutput(monoConvoList),
					"MonopolizingConvoAnalysis",
					"output/"+folderName+"/",
					programRunDate
			);

			//Step6b - Export to Excel the Final Table Results
			//isAnalysisSummaryOutputEnabled can be enabled if you want to see the results in the console
			System.out.println("Export to Excel the Final Table Results");
			boolean isAnalysisSummaryOutputEnabled = false;
			if (isAnalysisSummaryOutputEnabled){
				for(MessageConvoLengthResultsVO msgLengthAnalysis : msgConvoLengthList){
					logger.info(msgLengthAnalysis.toString());
				}
			}
			ExcelOutput excelOutput = new ExcelOutput();
			excelOutput.createExcelFileConvoResults(
					programRunDate,
					msgConvoLengthList,
					"output/"+folderName+"/");

			//Step6c - Export to Excel the Convo Duration
			System.out.println("Export to Excel the Convo Duration Table Results");
			excelOutput.createExcelFileConvoDurationResults(
					programRunDate,
					monoConvoList,
					"output/"+folderName+"/"
			);


		};
	}

	public static void main(String[] args) {
		logger.info("Application started");
		SpringApplication.run(MonopolizingConvoApplication.class, args);
		logger.info("Application finished");
	}

}

package stechschulte.uno.ese.monopolizing_convo.file.util;

import jakarta.xml.bind.JAXBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stechschulte.uno.ese.monopolizing_convo.bindings.Discord;

import java.io.IOException;
import java.util.Scanner;

public class UserInputUtility {

    private static final Logger logger = LogManager.getLogger(UserInputUtility.class);


    public void promptUserOptions() {
        System.out.println("Please enter the number for what you want to process:");
        System.out.println("1. DISCO - clojurians");
        System.out.println("2. DISCO - golang");
        System.out.println("3. DISCO - pythongeneral");
        System.out.println("4. DISCO - racketgeneral");
        System.out.println("5. quit");
    }

    public int getUserChoice() {
        // Implement logic to get user input
        // For example, using Scanner to read from console
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 1 || choice > 5) {
            System.out.print("Enter your choice (1-5): ");
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }


    public Discord loadFullDataSetForClojurians() throws JAXBException, IOException {
        logger.info("You chose clojurians");
        XMLFileImport xmlFileImport = new XMLFileImport();
        //Discord loadedObject = xmlFileImport.loadDiscordFromFileName("input/reduced-pythongeneralApr2020.xml.out");
        Discord loadedObject1 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/clojurians/Nov2019-Jan2020/clojure_Nov2019-Jan2020.xml.out");
        Discord loadedObject2 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/clojurians/Feb2020-Apr2020/clojure_Feb2020-Apr2020.xml.out");
        Discord loadedObject3 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/clojurians/May2020-July2020/clojure_May2020-Jul2020.xml.out");
        Discord loadedObject4 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/clojurians/Aug2020-Oct2020/clojure_Aug2020-Oct2020.xml.out");

        //merge all the files
        Discord mergedObject = new Discord();
        mergedObject.getMessage().addAll(loadedObject1.getMessage());
        mergedObject.getMessage().addAll(loadedObject2.getMessage());
        mergedObject.getMessage().addAll(loadedObject3.getMessage());
        mergedObject.getMessage().addAll(loadedObject4.getMessage());

        mergedObject.setChannelName(loadedObject1.getChannelName());
        mergedObject.setTeamDomain(loadedObject1.getTeamDomain());

        //note: start/end date for the merged object is not set

        return mergedObject;
    }

    public Discord loadFullDataSetForGoLang() throws JAXBException, IOException {
        logger.info("You chose golang");
        XMLFileImport xmlFileImport = new XMLFileImport();
        //Discord loadedObject = xmlFileImport.loadDiscordFromFileName("input/reduced-pythongeneralApr2020.xml.out");
        Discord loadedObject1 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/golang/Nov2019-Jan2020/golang_Nov2019-Jan2020.xml.out");
        Discord loadedObject2 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/golang/Feb2020-Apr2020/golang_Feb2020-Apr2020.xml.out");
        Discord loadedObject3 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/golang/May2020-July2020/golang_May2020-Jul2020.xml.out");
        Discord loadedObject4 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/golang/Aug2020-Sep2020/golang_Aug2020-Sep2020.xml.out");

        //merge all the files
        Discord mergedObject = new Discord();
        mergedObject.getMessage().addAll(loadedObject1.getMessage());
        mergedObject.getMessage().addAll(loadedObject2.getMessage());
        mergedObject.getMessage().addAll(loadedObject3.getMessage());
        mergedObject.getMessage().addAll(loadedObject4.getMessage());

        mergedObject.setChannelName(loadedObject1.getChannelName());
        mergedObject.setTeamDomain(loadedObject1.getTeamDomain());

        //note: start/end date for the merged object is not set

        return mergedObject;
    }

    public Discord loadFullDataSetForPythonGeneral() throws JAXBException, IOException {
        logger.info("You chose python general");
        XMLFileImport xmlFileImport = new XMLFileImport();
        //Discord loadedObject = xmlFileImport.loadDiscordFromFileName("input/reduced-pythongeneralApr2020.xml.out");
        Discord loadedObject0 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Nov2019/pythongeneralNov2019.xml.out");
        Discord loadedObject1 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Dec2019/pythongeneralDec2019.xml.out");
        Discord loadedObject2 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Jan2020/pythongeneralJan2020.xml.out");
        Discord loadedObject3 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Feb2020/pythongeneralFeb2020.xml.out");
        Discord loadedObject4 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Mar2020/pythongeneralMar2020.xml.out");
        Discord loadedObject5 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Apr2020/pythongeneralApr2020.xml.out");
        Discord loadedObject6 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/May2020/pythongeneralMay2020.xml.out");
        Discord loadedObject7 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Jun2020/pythongeneralJun2020.xml.out");
        Discord loadedObject8 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Jul2020/pythongeneralJul2020.xml.out");
        Discord loadedObject9 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Aug2020/pythongeneralAug2020.xml.out");
        Discord loadedObject10 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Sep2020/pythongeneralSep2020.xml.out");
        Discord loadedObject11 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/pythongeneral/Oct2020/pythongeneralOct2020.xml.out");

        //merge all the files
        Discord mergedObject = new Discord();
        mergedObject.getMessage().addAll(loadedObject0.getMessage());
        mergedObject.getMessage().addAll(loadedObject1.getMessage());
        mergedObject.getMessage().addAll(loadedObject2.getMessage());
        mergedObject.getMessage().addAll(loadedObject3.getMessage());
        mergedObject.getMessage().addAll(loadedObject4.getMessage());
        mergedObject.getMessage().addAll(loadedObject5.getMessage());
        mergedObject.getMessage().addAll(loadedObject6.getMessage());
        mergedObject.getMessage().addAll(loadedObject7.getMessage());
        mergedObject.getMessage().addAll(loadedObject8.getMessage());
        mergedObject.getMessage().addAll(loadedObject9.getMessage());
        mergedObject.getMessage().addAll(loadedObject10.getMessage());
        mergedObject.getMessage().addAll(loadedObject11.getMessage());

        mergedObject.setChannelName(loadedObject1.getChannelName());
        mergedObject.setTeamDomain(loadedObject1.getTeamDomain());

        //note: start/end date for the merged object is not set

        return mergedObject;
    }

    public Discord loadFullDataSetForRacketGeneral() throws JAXBException, IOException {
        logger.info("You chose golang");
        XMLFileImport xmlFileImport = new XMLFileImport();
        //Discord loadedObject = xmlFileImport.loadDiscordFromFileName("input/reduced-pythongeneralApr2020.xml.out");
        Discord loadedObject1 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/racketgeneral/Nov2019-Jan2020/racketgeneral_Nov2019-Jan2020.xml.out");
        Discord loadedObject2 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/racketgeneral/Feb2020-Apr2020/Racket_general_Feb2020-Apr2020.xml.out");
        Discord loadedObject3 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/racketgeneral/May2020-July2020/Racket_general_May2020-Jul2020.xml.out");
        Discord loadedObject4 = xmlFileImport.loadDiscordFromFileName("input/DISCO-A Dataset of Discord Chat Conversations for Software Engineering Research/data/racketgeneral/Aug2020-Oct2020/Racket_general_Aug2020-Oct2020.xml.out");

        //merge all the files
        Discord mergedObject = new Discord();
        mergedObject.getMessage().addAll(loadedObject1.getMessage());
        mergedObject.getMessage().addAll(loadedObject2.getMessage());
        mergedObject.getMessage().addAll(loadedObject3.getMessage());
        mergedObject.getMessage().addAll(loadedObject4.getMessage());

        mergedObject.setChannelName(loadedObject1.getChannelName());
        mergedObject.setTeamDomain(loadedObject1.getTeamDomain());

        //note: start/end date for the merged object is not set

        return mergedObject;
    }
}

package stechschulte.uno.ese.monopolizing_convo.file.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.ClassPathResource;
import stechschulte.uno.ese.monopolizing_convo.bindings.Discord;

public class XMLFileImport {

    public static Discord loadDiscordFromFilePath(String filePath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Discord.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Discord loadedObject =  (Discord) unmarshaller.unmarshal(new File(filePath));
        System.out.println(loadedObject);
        return loadedObject;
    }

    public static Discord loadDiscordFromFileName(String fileName) throws JAXBException, IOException {
        ClassPathResource resource = new ClassPathResource(fileName);

        JAXBContext context = JAXBContext.newInstance(Discord.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Discord loadedObject =  (Discord) unmarshaller.unmarshal(resource.getFile());
        System.out.println(loadedObject);
        return loadedObject;
    }

}

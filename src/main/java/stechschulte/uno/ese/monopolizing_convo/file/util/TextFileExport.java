package stechschulte.uno.ese.monopolizing_convo.file.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextFileExport {
    public static void writeStringToFile(String content,
                                         String filePath,
                                         Date dateOf,
                                         int version
    ) throws IOException {
        try (FileWriter fileWriter = new FileWriter(
                filePath+"_"
                        +new SimpleDateFormat("yyyyMMdd_HHmmss").format(dateOf)
                        +"_v"+version
                        +".txt")) {
            fileWriter.write(content);
        }
    }
}

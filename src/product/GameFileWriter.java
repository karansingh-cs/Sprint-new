package product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameFileWriter {
		
    public static void writeIntoFile(String text) throws IOException
    {
        // Define the file name of the file
        Path fileName = Path.of("/Users/mayanksolanki/Desktop/demo.docx");
 
        // Write into the file
        Files.writeString(fileName, text);
 
        // Read the content of the file
        String file_content = Files.readString(fileName);
 
        // Print the content inside the file
        System.out.println(file_content);
    }

}
package product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class GameFileWriter {
		
    public static void writeIntoFile(String text) throws IOException
    {
      Files.write(Paths.get("C:\\Users\\Karan\\Documents\\College\\Classes\\Senior\\CS 449\\Sprint4\\output.txt"), text.getBytes(), StandardOpenOption.APPEND);
    }

}
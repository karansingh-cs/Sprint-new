package product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class fileWriter {

	public static void main(String[] args) throws FileNotFoundException {
		
		try {
            BufferedWriter bw = new BufferedWriter(
            	new FileWriter("C:\\\\Users\\\\Karan\\\\Documents\\\\College\\\\Classes\\\\Senior\\\\CS 449\\\\Sprint4\\\\output.txt"));
            bw.write("Blue Move:\n" );
            bw.write("Red Score:\n" );
            bw.close();
             
            }catch(Exception ex) {
            	return;
            }
		
		
		
//		File file = new File("/Users/Karan/Desktop/captmidn.txt");
//		Scanner scan = new Scanner(file);
//		
//		while(scan.hasNextLine()) {
//		System.out.println(scan.nextLine());
//		}

	}

}
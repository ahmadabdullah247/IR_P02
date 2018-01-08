package irp02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Filer {

	void createFile(String path, HashSet<String> contents) {
		try {
			File file = new File(path);

			boolean isCreated = file.createNewFile();
			if (isCreated) {
				System.out.println("File created");
				System.out.println("Writing file");
				FileWriter writer = new FileWriter(path, true);
				for (String url : contents) {
					writer.write(url);
				}
				writer.close();
			} else {
				System.out.println("File already present");
				System.out.println("Appending file");
				FileWriter writer = new FileWriter(path,true);	
				for (String url : contents) {
					writer.write(url);
				}
				writer.close();
			}
		} catch (IOException e) {
			System.err.println("Error :: " + e.getMessage());
		}
	}
}

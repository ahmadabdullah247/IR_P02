package irp02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class Filer {
	// Creates File parse.txt and store list of URLs and their depth
	void createFile(String path, HashSet<String> contents) {
		try {
			path += "index/";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			path += "pages.txt";
			file = new File(path);
			if (file.createNewFile()) {
				System.out.println("File created");
				System.out.println("Writing file");
				FileWriter writer = new FileWriter(path, true);
				for (String url : contents) {
					writer.write(url);
				}
				writer.close();
			} else {
				System.out.println("File already present");
				System.out.println("Deleting the old file");
				System.out.println("Creating a the new file");
				FileWriter writer = new FileWriter(path, false);
				for (String url : contents) {
					writer.write(url);
				}
				writer.close();
			}
		} catch (IOException e) {
			System.err.println("Error :: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Check if index folder is present
	public Boolean findPath() {
		boolean found = false;
		File directory = new File("./index/");
		File[] subDirs = directory.listFiles();
		for (File dir : subDirs) {
			if (dir.getName().equals("pages.txt")) {
				System.out.println("true");
				found = true;
				break;
			}
		}
		return found;
	}
}

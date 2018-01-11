package irp02;

import java.io.File;

public class Main {
	String indexDirectoryPath;
	
	public Main(String path){
		indexDirectoryPath = path;
	}
	
	public void deleteIndex(File dir) {
		try {
			for (File file : dir.listFiles()) {
				if (file.isDirectory())
					deleteIndex(file);
				file.delete();
			}
		} catch (NullPointerException e) {
			System.out.println("Given path does not has any files.");
		}
	}

	public static void main(String[] args){
		
		Main mainApp = new Main("F:/Books/Third Semester/Informtation Retrieval/IR_P02/index/");
		Crawler crawler = new Crawler(0);
		Filer filer = new Filer();
		System.out.println("Crawling started");
		crawler.getAllURL("http://www.mkyong.com/",0);
		filer.createFile(mainApp.indexDirectoryPath+"pages.txt",
				crawler.urls);
		System.out.println("Crawling Ended");
		System.out.println("Indexing documents");
		Indexer indexer = new Indexer(mainApp.indexDirectoryPath);
		mainApp.deleteIndex(new File(mainApp.indexDirectoryPath));
		indexer.indexURLData(crawler.urls);
	}
}

package irp02;

import java.io.IOException;

public class Main {

	// INPUT : Null
	// OUTPUT : Null
	// DESCRIPTION : Print line on console
	public static void hashLine() {
		System.out.println(
				"#########################################################################################################");
	}

	public static void main(String[] args) {
		Crawler crawler = new Crawler(0);
		Filer filer = new Filer();
//		Indexer indexer = new Indexer("/Users/ollostudio/Desktop/IR/IR_P02/index/");
		
		System.out.println("Crawling started");
		System.out.print("[");
		crawler.getAllURL("http://www.mkyong.com/",0);
		System.out.println("]");
		filer.createFile("/Users/ollostudio/Desktop/IR/IR_P02/index/pages.txt",crawler.urls);	
		System.out.println("Indexing documents");
//		indexer.indexDocuments(crawler.urls);	
		hashLine();
	}
}

package irp02;

import java.io.File;

public class Main {
	String indexDirectoryPath;

	public Main(String path) {
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

	public static void main(String[] args) {
		int argsLength = args.length;
		int crawlDepth;
		String seedURL = new String();
		String indexFolderPath = new String();
		String searchQuery = new String();

		crawlDepth = 1;
		seedURL = "http://www.mkyong.com/";
		indexFolderPath = "/Users/ollostudio/Desktop/IR/IR_P02/index/";
		searchQuery= "php";

		// switch (argsLength) {
		// case 4:
		// seedURL = args[0];
		// crawlDepth = Integer.parseInt(args[1]);
		// indexFolderPath = args[2];
		// Query = args[3];
		// break;
		// case 3:
		// seedURL = args[0];
		// crawlDepth = Integer.parseInt(args[1]);
		// Query = args[2];
		// break;
		// default:
		// System.err.println("Argument missing");
		// System.err.println(
		// "Follow Format :: java -jar IR P02.jar [seed URL] [crawl depth] [path to
		// index folder] [query]");
		// }

		// Crawl to N depth
		System.out.println("Crawling started");
		Crawler crawler = new Crawler(crawlDepth);
		crawler.getAllURL(seedURL, 0);
		System.out.println(":");

		// Create page.txt file
		Filer filer = new Filer();
		filer.createFile(indexFolderPath + "pages.txt", crawler.urls);
		System.out.println("Crawling Ended");

		// Index URLS
		System.out.println("Indexing documents");
		Indexer indexer = new Indexer(indexFolderPath);
		indexer.indexURLData(crawler.urls);
		System.out.println("Indexing complete");
		// Search query
		System.out.println("Searching documents");
		Searcher searcher = new Searcher(indexFolderPath);
		searcher.searchIndexedFiles(searchQuery);
		System.out.println("Searching complete");
		// Main mainApp = new Main("F:/Books/Third Semester/Informtation
		// Retrieval/IR_P02/index/");

		// filer.createFile(mainApp.indexDirectoryPath + "pages.txt", crawler.urls);

		// Indexer indexer = new Indexer(mainApp.indexDirectoryPath);
		// mainApp.deleteIndex(new File(mainApp.indexDirectoryPath));
		// indexer.indexURLData(crawler.urls);

		// mainApp.deleteIndex(new File("/Users/ollostudio/Desktop/IR/IR_P02/index/"));
	}
}

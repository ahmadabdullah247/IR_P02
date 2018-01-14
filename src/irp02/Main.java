package irp02;

public class Main {

	public static void main(String[] args) {
		int argsLength = args.length; // Get arguments length
		int crawlDepth = 0; // Depth of crawling
		String seedURL = new String(); // URL to crawl
		String indexFolderPath = new String(); // Index folder path
		String searchQuery = new String(); // Search query
		String consoleCase = new String(); // Console query case (4 or 3 arguments)

		///////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////// For IDE
		///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////
		
		// Test query
		// java -jar IR02.jar http://www.ovgu.de/ 1 /Users/ollostudio/Desktop/IR/IR_P02/index/ Für
		
		
//		 crawlDepth = 1;
//		 seedURL = "http://www.ovgu.de/";
//		 indexFolderPath = "/Users/ollostudio/Desktop/IR/IR_P02/index/";
//		 searchQuery = "Für";
//		
//		 // Crawl to N depth
//		 System.out.println("Crawling started");
//		 Crawler crawler = new Crawler(crawlDepth);
//		 crawler.getAllURL(seedURL, 0);
//		 System.out.println(":");
//		
//		 // Create page.txt file
//		 Filer filer = new Filer();
//		 filer.createFile(indexFolderPath, crawler.urls);
//		 System.out.println("Crawling Ended");
//		
//		 // Index URLS if not indexed or don't find a index folder
//		 if (filer.findPath()) {
//		 System.out.println("Indexing documents");
//		 Indexer indexer = new Indexer(indexFolderPath);
//		 indexer.indexURLData(crawler.urls);
//		 System.out.println("Indexing complete");
//		 }
//		 // Search query and rank top 10 results
//		 System.out.println("Searching documents for : " + searchQuery);
//		 Searcher searcher = new Searcher(indexFolderPath);
//		 searcher.searchIndexedFiles(searchQuery);
//		 System.out.println("Searching complete");

		///////////////////////////////////////////////////////////////////////////////////
		////////////////////////// For Console app
		///////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////

		switch (argsLength) {
		case 4:
			seedURL = args[0];
			crawlDepth = Integer.parseInt(args[1]);
			indexFolderPath = args[2];
			searchQuery = args[3];
			consoleCase = "NEW";
			break;
		case 3:
			seedURL = args[0];
			crawlDepth = Integer.parseInt(args[1]);
			searchQuery = args[2];
			consoleCase = "INDEXED";
			break;
		default:
			System.err.println("Invalid arguments");
			System.err.print("Follow Format :: ");
			System.err.print("java -jar IR P02.jar [seed URL] [crawl depth] [path toindexfolder] [query]");
			consoleCase = "NULL";
		}

		if (consoleCase != "NULL") {
			// Crawl to N depth
			hashLine();
			System.out.println("Crawling started");
			Crawler crawler = new Crawler(crawlDepth);
			crawler.getAllURL(seedURL, 0);
			System.out.println(":");

			// Create page.txt file
			Filer filer = new Filer();
			filer.createFile(indexFolderPath, crawler.urls);
			System.out.println("Crawling Ended");

			// Index URLS if not indexed or don't find a index folder
			if (consoleCase != "INDEXED" || filer.findPath()) {
				hashLine();
				System.out.println("Indexing documents");
				Indexer indexer = new Indexer(indexFolderPath);
				indexer.indexURLData(crawler.urls);
				System.out.println("Indexing complete");
			}
			// Search query and rank top 10 results
			hashLine();
			System.out.println("Searching documents for : " + searchQuery);
			Searcher searcher = new Searcher(indexFolderPath);
			searcher.searchIndexedFiles(searchQuery);
			System.out.println("Searching complete");
		}
	}
	public static void hashLine() {
		System.out.println("###############################################################################################################");
	}
}

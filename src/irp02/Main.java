package irp02;


public class Main {

	// INPUT : Null
	// OUTPUT : Null
	// DESCRIPTION : Print line on console
	public static void hashLine() {
		System.out.println(
				"#########################################################################################################");
	}

	public static void main(String[] args) {
		Crawler crawler = new Crawler(1);
		Filer filer = new Filer();
//		
		crawler.getAllURL("http://www.mkyong.com/",0);
		filer.createFile("/Users/ollostudio/Desktop/IR/IR_P02/index/pages.txt",crawler.urls);	
		hashLine();
	}
}

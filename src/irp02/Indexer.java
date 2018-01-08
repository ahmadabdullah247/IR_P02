package irp02;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class Indexer {

	private IndexWriter indexWriter;

	public List<String> text;
	public Crawler crawler;
	public String indexPath;

	public Indexer(String path) throws IOException {
		text = new ArrayList<String>();
		crawler = new Crawler(0);
		indexPath = path;

		FSDirectory directory = FSDirectory.open(Paths.get(indexPath));
		// create the indexer
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		indexWriter = new IndexWriter(directory, config);
	}

	public void indexDocuments(HashSet<String> urls) {
		for (String url : urls) {
			String[] x = url.split("\t");
			System.out.println(crawler.getText(x[0]));
		}
	}

}

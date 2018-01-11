package irp02;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.tartarus.snowball.ext.PorterStemmer;
import org.apache.lucene.document.Document;

public class Indexer {

	private IndexWriter indexwriter;

	public List<String> text;
	public Crawler crawler;
	public String indexPath;
	public Document URLData;
	public FieldType contentField;
	public Field contentValue;
	public FieldType urlField;
	public Field urlValue;

	public Indexer(String path){
		text = new ArrayList<String>();
		crawler = new Crawler(0);
		indexPath = path;
		try {
			FSDirectory directory = FSDirectory.open(Paths.get(indexPath));
			// create the indexer
			IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
			indexwriter = new IndexWriter(directory, config);
		}catch (IOException e) {
			System.err.println("Error :: " + e.getMessage());
		}
	}
	
	public String porterstemmer(String text) {
		List<String> stemmedLines = new ArrayList<String>();
		PorterStemmer stemmer = new PorterStemmer();
		StringBuffer buffer = new StringBuffer();
		String[] words = text.split(" ");
		for (int i = 0; i < words.length; i++) {
			stemmer.setCurrent(words[i]);
			stemmer.stem();
			buffer.append(stemmer.getCurrent() + " ");
		}
		stemmedLines.add(buffer.toString());
		return stemmedLines.toString();
	}
	
	public Document initilizeDocumentwithFields(String url) {
		URLData = new Document();
		
		urlField = new FieldType();
		urlField.setIndexOptions(IndexOptions.DOCS);
		urlField.setStored(true);
		urlField.setTokenized(true);
		urlValue = new Field(LuceneConstants.URL, url, urlField);
		
		contentField = new FieldType();
		contentField.setIndexOptions(IndexOptions.DOCS);
		contentField.setStored(true);
		contentField.setTokenized(true);
		
		contentValue = new Field(LuceneConstants.CONTENTS, 
				porterstemmer(crawler.getText(url)).toLowerCase(), contentField);
		
		URLData.add(urlValue);
		URLData.add(contentValue);
		return URLData;
	}
	
	public void indexURLData(HashSet<String> urls) {
		for (String url : urls) {
			String[] x = url.split("\t");
			Document document = initilizeDocumentwithFields(x[0]);
			try {
				indexwriter.addDocument(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			indexwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

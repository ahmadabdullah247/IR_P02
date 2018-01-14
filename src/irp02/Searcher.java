package irp02;

import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.similarities.BM25Similarity;

public class Searcher {
	IndexSearcher indexSearcher;
	QueryParser queryParser;
	Query query;

	// takes the directory path as input where to indexes are present
	// and initialize that
	public Searcher(String indexDirectoryPath) {
		try {
			Directory dir = FSDirectory.open(Paths.get(indexDirectoryPath));
			IndexReader reader = DirectoryReader.open(dir);
			this.indexSearcher = new IndexSearcher(reader);
		} catch (IOException e) {
			System.err.println("Error :: " + e.getMessage());
		}
	}

	// parse the inputed query and rank with either Vector Space Model or Okapi BM25
	public void searchIndexedFiles(String searchQuery) {
		try {
			this.indexSearcher.setSimilarity(new BM25Similarity());

			queryParser = new QueryParser(LuceneConstants.CONTENTS, new StandardAnalyzer());
			query = queryParser.parse(searchQuery);
			// queryParser.parse(searchQuery);
			ScoreDoc[] hits = this.indexSearcher.search(query, 10).scoreDocs;
			int hitCount = hits.length;
			int rank = 0;
			System.out.println(hitCount + " documents found.");
			for (ScoreDoc scoreDoc : hits) {
				Document doc = this.indexSearcher.doc(scoreDoc.doc);
				System.out.println(rank + " URL : " + doc.get(LuceneConstants.URL));
				rank++;
			}
		} catch (IOException e) {
			System.err.println("Error :: " + e.getMessage());
		} catch (ParseException e) {
			System.err.println("Error :: " + e.getMessage());
		}
	}
}
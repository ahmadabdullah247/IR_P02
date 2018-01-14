package irp02;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private int maxDepth; // Maximum depth to crawl
	public HashSet<String> urls;	// Save unique URLs only 

	// Constructor
	public Crawler(int depth) {
		maxDepth = depth;
		urls = new HashSet<>();
	}

	// Get all URLs and save them in urls variable
	public void getAllURL(String URL, int depth) {
		if ((!urls.contains(URL) && (depth <= maxDepth))) {
			try {
				urls.add(normalize(URL) + "\t" + depth + "\n");
				Document document = Jsoup.connect(URL).get();
				if (document != null) {
					Elements elements = document.select("a[href]");
					 System.out.print(":");
					depth++;
					for (Element anchorTag : elements) {
						getAllURL(anchorTag.attr("abs:href"), depth);
					}
				} else {
					System.out.println("Time out :: " + URL);
				}
			} catch (IOException e) {
				System.err.println("Error :: " + e.getMessage());
				System.err.println("URL :: " + URL);
			}
		}
	}

	// Get text from title and body of HTML pages (for indexer)
	public String getText(String URL) {
		try {
			Document document = Jsoup.connect(URL).get();
			String titleText = document.title().toString();
			String bodyText = document.body().text();
			return titleText + " " + bodyText;
		} catch (IOException e) {
			System.err.println("Error :: " + e.getMessage());
			return null;
		}
	}

	// Remove '/' from end of URL
	private String normalize(String URL) {
		if (URL.length() > 0 && URL.substring(URL.length() - 1).equals("/")) {
			URL = URL.substring(0, URL.length() - 1);
		}
		URL = URL.toLowerCase();
		return URL;
	}
}

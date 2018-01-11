package irp02;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private int maxDepth;
	public HashSet<String> urls;

	public Crawler(int depth) {
		urls = new HashSet<>();
		maxDepth = depth;
	}

	public void getAllURL(String URL, int depth) {
		if ((!urls.contains(URL) && (depth <= maxDepth))) {
			try {
				urls.add(normalize(URL) + "\t" + depth + "\n");

				Document document = Jsoup.connect(URL).get();
				Elements elements = document.select("a[href]");

				depth++;
				for (Element anchorTag : elements) {
					getAllURL(anchorTag.attr("abs:href"), depth);
				}
			} catch (IOException e) {
				System.err.println("Error :: " + e.getMessage());
			}
		}
	}

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

	private String normalize(String URL) {
		if (URL.substring(URL.length() - 1).equals("/")) {
			URL = URL.substring(0, URL.length() - 1);
		}
		URL = URL.toLowerCase();
		return URL;
	}
}

package com.example.demo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrape {

	public static String getScrapingData(String str) throws IOException {
	    Document document = Jsoup.connect("url").get();
	    Elements elements = document.select("elements");
	    String name = "";

	    for (Element element : elements) {
	        name = element.text();
	        System.out.println(name);
	    }

		return name;
	}

}

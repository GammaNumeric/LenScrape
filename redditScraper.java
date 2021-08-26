package com.mcnz.screen.scraper;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class scrapedObject{
    private HtmlPage page;
    private String url; 
    HtmlPage getPage(){return page;}
    String getUrl(){return url;}
}

/**
 * returns scrapedObject  NEWEST entry on subreddit.
 * easy identification - all subreddits are denoted reddit.com/r/SUBREDDITNAME - users usually know this name.
 */
public class HtmlUnitScraper {
  public scrapedObject scrape(String subreddit) throws Exception {
	WebClient webClient = new WebClient();
    webClient.getOptions().setUseInsecureSSL(true);
    webClient.getOptions().setCssEnabled(false);
    webClient.getOptions().setJavaScriptEnabled(false);
    HtmlPage htmlPage = webClient.getPage("reddit.com/r/"+subreddit+"/new"); 
    String url = htmlPage.getByXPath("//a[contains(text(),r/"+subreddit+"/comments)]");
    htmlPage = webClient.getPage(url);
    return new scrapedObject(htmlPage, url);
  }
}
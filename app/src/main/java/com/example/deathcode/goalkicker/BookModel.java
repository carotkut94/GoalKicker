package com.example.deathcode.goalkicker;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.Serializable;

/**
 * Created by deathcode on 15/01/18.
 */

public class BookModel implements Serializable {

    String imageLink;
    String detailsUrl;
    String bookTitle;
    public BookModel(Element book) {
        setImageLink(book.getElementsByTag("img").get(0).absUrl("src"));
        setDetailsUrl(book.getElementsByTag("a").get(0).absUrl("href"));
        setBookTitle(book.text());
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
}

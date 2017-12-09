package com.example.root.digital;

/**
 * Created by root on 24/8/17.
 */

public class Model  {
    String name;
    String author;
    String link;
    String download;

    public String getDownload() {
        return download;
    }
    public void setDownload(String download) {
        this.download = download;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Model(String name, String author, String link,String download)
    {
        this.name=name;
        this.author=author;
        this.link=link;
        this.download=download;
    }
}

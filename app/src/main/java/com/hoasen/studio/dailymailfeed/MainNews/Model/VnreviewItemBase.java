package com.hoasen.studio.dailymailfeed.MainNews.Model;

import org.simpleframework.xml.Element;

/**
 * Created by Harry Nguyen on 02-Mar-16.
 */
public class VnreviewItemBase {
    @Element(name = "title")
    public String title;
    @Element(name = "link")
    public String link;
    @Element(name = "description")
    public String description;
    @Element(name = "pubDate")
    public String pubDate;
    @Element(name = "date")
    public String dcdate;

}

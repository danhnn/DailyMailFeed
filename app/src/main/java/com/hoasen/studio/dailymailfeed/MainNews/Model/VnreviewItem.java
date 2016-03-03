package com.hoasen.studio.dailymailfeed.MainNews.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Harry Nguyen on 02-Mar-16.
 */
@Root(name = "item")
public class VnreviewItem extends VnreviewItemBase {
    @Element(name = "guid")
    public String guid;
}

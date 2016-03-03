package com.hoasen.studio.dailymailfeed.MainNews.Model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Harry Nguyen on 02-Mar-16.
 */
public class VnreviewChannel extends VnreviewItemBase {

    @ElementList(inline = true)
    public List<VnreviewItem> listItem;
}

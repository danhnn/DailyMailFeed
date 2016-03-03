package com.hoasen.studio.dailymailfeed.MainNews.Model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Harry Nguyen on 02-Mar-16.
 */

@Root(name = "root")
public class VnreviewModel {
    @Attribute(name = "version")
    String version;
    @Element(name = "channel")
    public VnreviewChannel channel;

}

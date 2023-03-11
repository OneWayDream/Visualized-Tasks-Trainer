package ru.itis.graduationwork.application.managers.content;

import ru.itis.graduationwork.exceptions.url.UrlParsingException;
import ru.itis.graduationwork.application.settings.Link;
import ru.itis.graduationwork.utils.PropertiesUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class LinksManager {

    private static final Properties properties;

    static {
        properties = PropertiesUtils.getInstance().getProperties();
    }

    public static URI getLinkValue(Link link){
        try {
            return new URI(properties.getProperty(link.getKey()));
        } catch (URISyntaxException ex) {
            throw new UrlParsingException(ex);
        }
    }

}

package ru.itis.visualtasks.desktopapp.application.managers.content;

import ru.itis.visualtasks.desktopapp.application.settings.Link;
import ru.itis.visualtasks.desktopapp.exceptions.url.UrlParsingException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

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

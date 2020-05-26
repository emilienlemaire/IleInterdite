package com.egp.constants;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class utils {
    public static String getPath(String path) {
        return new File(path).toURI().toString();
    }
    public static URL getURL(String path) throws MalformedURLException {
        return new File(path).toURI().toURL();
    }
}

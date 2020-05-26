package com.egp.constants;

import java.io.File;

public class utils {
    public static String getPath(String path) {
        return new File(path).toURI().toString();
    }
}

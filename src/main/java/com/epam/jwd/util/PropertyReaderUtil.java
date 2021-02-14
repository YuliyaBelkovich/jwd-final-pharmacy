package com.epam.jwd.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Util class responsible for loading application properties from the given file
 */
public class PropertyReaderUtil {

    private final Properties properties = new Properties();

    public PropertyReaderUtil() { }

    public Properties loadProperties(final String propertiesFileName) {

        try (InputStream propertiesStream = new FileInputStream(propertiesFileName)) {
            properties.load(propertiesStream);
        } catch (IOException e) {
        }
        return properties;
    }

}

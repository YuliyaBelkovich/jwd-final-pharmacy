package com.epam.jwd.domain;

import com.epam.jwd.util.PropertyReaderUtil;

import java.util.Properties;

public class ApplicationProperties {

    private static ApplicationProperties applicationProperties;

    private final String url;
    private final String dbName;
    private final String user;
    private final String password;
    private final int initConnections;
    private final int maxConnections;
    private static String propertiesFileName;

    public static void setPropertiesFileName(String propertiesFileName) {
        ApplicationProperties.propertiesFileName = propertiesFileName;
    }

    private ApplicationProperties(String url, String dbName, String user, String password, int initConnections, int maxConnections) {
        this.url = url;
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.initConnections = initConnections;
        this.maxConnections = maxConnections;
    }

    public static ApplicationProperties getInstance(){
        Properties properties = new PropertyReaderUtil().loadProperties(propertiesFileName);
        applicationProperties = new ApplicationProperties(properties.getProperty("url"),
                properties.getProperty("dbName"),
                properties.getProperty("user"),
                properties.getProperty("password"),
                Integer.parseInt(properties.getProperty("initConnections")),
                Integer.parseInt(properties.getProperty("maxConnections")));
        return applicationProperties;
    }

    public static void setApplicationProperties(ApplicationProperties applicationProperties) {
        ApplicationProperties.applicationProperties = applicationProperties;
    }

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getInitConnections() {
        return initConnections;
    }

    public int getMaxConnections() {
        return maxConnections;
    }
}

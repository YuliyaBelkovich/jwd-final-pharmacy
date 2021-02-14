package com.epam.jwd.pool;

import com.epam.jwd.domain.ApplicationProperties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.*;

public class ConnectionPoolTest {

    String propertiesFileName = "src/test/resources/application_test.properties";

    @BeforeTest
    public void init() {
        ApplicationProperties.setPropertiesFileName(propertiesFileName);
        ApplicationProperties.getInstance();
    }

    @Test
    public void testGetInstance() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        assertNotNull(connectionPool);
    }


    @Test
    public void testRetrieveConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.retrieveConnection();
        assertNotNull(connection);
    }

    @Test
    public void testRetrieveNotSameConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection1 = connectionPool.retrieveConnection();
        Connection connection2 = connectionPool.retrieveConnection();

        assertNotEquals(connection1, connection2);
    }
}
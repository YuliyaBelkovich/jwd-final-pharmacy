package com.epam.jwd.pool;

import com.epam.jwd.domain.ApplicationProperties;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {

    private static ConnectionPool connectionPool;
    private static ArrayBlockingQueue<ConnectionProxy> availableConnections;
    private static ApplicationProperties properties = ApplicationProperties.getInstance();
    // private  ArrayBlockingQueue<ConnectionProxy> usedConnections;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (availableConnections == null) {
            connectionPool = new ConnectionPool();
            createConnections(properties.getUrl(),
                    properties.getUser(),
                    properties.getPassword(),
                    properties.getInitConnections());
        } return connectionPool;
    }

    private static void createConnections(String url, String user, String password, final int maxConnectionNumber) {
        availableConnections = new ArrayBlockingQueue<>(maxConnectionNumber);
        // usedConnections = new ArrayBlockingQueue<>(maxConnectionNumber);

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

         for (int i = 0; i < maxConnectionNumber; i++) {
            try {
                ConnectionProxy connection = new ConnectionProxy(DriverManager.getConnection(url, user, password));
                availableConnections.offer(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public ConnectionProxy retrieveConnection() {
        ConnectionProxy connection = null;
        try {
            connection = availableConnections.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void returnConnection(ConnectionProxy connection) {
        availableConnections.offer(connection);
    }


}

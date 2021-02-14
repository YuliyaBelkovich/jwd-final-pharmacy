package com.epam.jwd.pool;

import com.epam.jwd.domain.ApplicationProperties;
import com.epam.jwd.exception.DAOException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Class responsible for storing and offering connections to the database
 * Stores {@link ConnectionProxy}
 * Creates when the server is started
 */
public class ConnectionPool {

    private static ConnectionPool connectionPool;
    private static ArrayBlockingQueue<ConnectionProxy> availableConnections;
    private static ApplicationProperties properties = ApplicationProperties.getInstance();

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

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

         for (int i = 0; i < maxConnectionNumber; i++) {
            try {
                ConnectionProxy connection = new ConnectionProxy(DriverManager.getConnection(url, user, password));
                availableConnections.offer(connection);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public void closePool(){
        for(ConnectionProxy connectionProxy : availableConnections){
            try {
                connectionProxy.realClose();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        availableConnections.clear();
    }

    public ConnectionProxy retrieveConnection() throws DAOException {
        ConnectionProxy connection = null;
        try {
            connection = availableConnections.take();
        } catch (InterruptedException e) {
            if(availableConnections.size() < properties.getMaxConnections()){
                try {
                   connection  = new ConnectionProxy(DriverManager.getConnection(properties.getUrl(), properties.getUser(), properties.getPassword()));
                } catch (SQLException exception) {
                    throw new DAOException("No connections available.");
                }
            }
        }
        return connection;
    }

    public void returnConnection(ConnectionProxy connection) {
        availableConnections.offer(connection);
    }


}

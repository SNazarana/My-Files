
package com.springboot.bike.stepdefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;

public class Hooks {
    public static String basicURI = "http://localhost:8080";
    public static Connection connection;
    // public static Statement statement;
    private static final Logger logger = LogManager.getLogger(Hooks.class);
    public static Statement statement;

    @Before
    public void setUpConnection() {
        setUpDatabase();
    }

    @After
    public void closeConnection() {
        // clerTable("bank");
        closeDatabase();
    }
    public static void setUpDatabase() {
        //Establishing database connection
        String url = "jdbc:postgresql://localhost:5432/bikes";
        String username = "postgres";
        String password = "Sheik98";

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection established");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error establishing database connection: " + e.getMessage());
        }}
        public static void closeDatabase() {
            //Disconnecting database connection
            if (connection != null) {
                try {
                    // closeDatabase();
                    connection.close();
                    System.out.println("connection closed");
                } catch (SQLException e) {
                    logger.error("An error occurred", e);
                }
            }
        }

    }
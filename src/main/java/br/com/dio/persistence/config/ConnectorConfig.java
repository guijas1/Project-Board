package br.com.dio.persistence.config;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class ConnectorConfig {
    

    public static Connection getConnection() throws SQLException {

    var url = "jdbc:mysql://localhost/board";
    var user = "root";
    var password = "1234";
    var connection = DriverManager.getConnection(url, user, password);
    connection.setAutoCommit(false);
    return connection;

    }
     
}
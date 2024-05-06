package com.example.project;

import java.sql.*;
import java.util.*;

public class DBConnection {
    private static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/company";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "0984029574Phat!";

    private String driver;
    private String url;
    private String username;
    private String password;

    public DBConnection() {
        this(DEFAULT_DRIVER, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
    }

    public DBConnection(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        if (username == null || password == null || username.trim().length() == 0 || password.trim().length() == 0) {
            return DriverManager.getConnection(url);
        } else {
            return DriverManager.getConnection(url, username, password);
        }
    }

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> query(String sql, List<Object> parameters) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = createConnection();
            ps = connection.prepareStatement(sql);
            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            rs = ps.executeQuery();
            results = map(rs);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(ps);
            close(connection);
        }
        return results;
    }

    public int update(String sql, List<Object> parameters) throws SQLException {
        int numRowsUpdated = 0;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = createConnection();
            ps = connection.prepareStatement(sql);
            int i = 0;
            for (Object parameter : parameters) {
                ps.setObject(++i, parameter);
            }
            numRowsUpdated = ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(ps);
            close(connection);
        }
        return numRowsUpdated;
    }

    private List<Map<String, Object>> map(ResultSet rs) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        int numColumns = meta.getColumnCount();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= numColumns; ++i) {
                String name = meta.getColumnName(i);
                Object value = rs.getObject(i);
                row.put(name, value);
            }
            results.add(row);
        }
        return results;
    }


}


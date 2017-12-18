package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//
//  ::::::::  :::    ::: :::::::::   ::::::::  :::    :::
// :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:    :+:
// +:+         +:+  +:+  +:+    +:+ +:+    +:+ +:+    +:+
// +#++:++#++   +#++:+   +#++:++#+   +#++:++#  +#++:++#++
//        +#+  +#+  +#+  +#+        +#+    +#+ +#+    +#+
// #+#    #+# #+#    #+# #+#        #+#    #+# #+#    #+#
//  ########  ###    ### ###         ########  ###    ###
//
//          --------Created by SXP8H--------

public class ConnectionOracle {
    private Connection c;
    private boolean paramsDefined = false;
    private String ip;
    private int port;
    private String username;
    private String password;
    private String database;
    private static final String CONNECTION_STRING = "jdbc:oracle:thin:@<ip>:<port>:<database>"; // CHANGE IP:PORT:DATABASE 
    private static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";

    public ConnectionOracle() {
        setConnectionParams();
    }
    
    
    private void setConnectionParams(String ip, int port, String database, String username, String password) {
        paramsDefined = true;
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    private void setConnectionParams() {
        paramsDefined = true;
        // IP CASA
        //this.ip = "192.168.1.7";
        // IP CLASE
        this.ip = "192.168.20.202";
        this.port = 1521;
        this.database = "XE";
        this.username = "SYSTEM";
        this.password = "system";
    }

    public Connection connect() throws Exception {
        if (!paramsDefined) {
            throw new ConnectionParamsNotDefinedException();
        }
        try {
            Class.forName(DRIVER_CLASS);
        } catch (Exception ex) {
            throw new Exception("Class");
        }
        c = DriverManager.getConnection(
            CONNECTION_STRING.replace("<ip>", ip).replace("<port>", "" + port).replace("<database>", database),
            username, password);
        return c;
    }
    public void disconnect() {
        if (c != null) {
        try {
                c.close();
        } catch (SQLException e) {}
            c = null;
        }
    }

    public Connection getConnection() {
        return c;
    }
}

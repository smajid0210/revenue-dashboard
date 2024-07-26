//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package login.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;

public class ConnectionProvider {
    static Connection con = null;

    public ConnectionProvider() {
    }

    public static Connection getCon() {
        return con;
    }

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con =DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=dashboardDB;user=user22;password=321;encrypt=true;trustServerCertificate=true");
            System.out.println("ConnectionProvider: ok"+con);
        } catch (Exception var1) {
        }

    }
}

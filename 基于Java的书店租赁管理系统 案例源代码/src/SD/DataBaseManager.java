package SD;

import java.sql.*;

public class DataBaseManager {
    Connection con = null;
    ResultSet rs = null;
    Statement stmt;

    public DataBaseManager() {
        try {
            String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=SDZL";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String user="AYQ";
            String password="123456";
            con= DriverManager.getConnection(url,user,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
        }
        catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
        catch (ClassNotFoundException cnfex) {
            cnfex.printStackTrace();
        }
    }

    public ResultSet getResult(String strSQL) {
        try {
            rs = stmt.executeQuery(strSQL);
            return rs;
        }
        catch (SQLException sqle) {
            System.out.println(sqle.toString());
            return null;
        }

    }

    public int updateSql(String strSQL) {
        try {
            int i = stmt.executeUpdate(strSQL);
            con.commit();
            return i;
        }
        catch (SQLException sqle) {
            System.out.println(sqle.toString());
            return -1;
        }
    }

    public void closeConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
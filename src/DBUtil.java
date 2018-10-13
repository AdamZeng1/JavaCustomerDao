import java.sql.*;

public class DBUtil {
    private static DBUtil db;

    private Connection conn;

    private PreparedStatement ps;

    private ResultSet rs;

    public DBUtil() {

    }

    public static DBUtil getDBUtil() {
        if (db == null) {
            return db = new DBUtil();
        } else {
            return db;
        }
    }

    public int executeUpdate(String sql) {
        int result = -1;

        if (getConn() == null) {
            return result;
        }

        try {
            ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public int executeUpdate(String sql, Object[] obj) {
        int result = -1;

        if (getConn() == null) {
            return result;
        }

        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public ResultSet executeQuery(String sql) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public ResultSet executeQuery(String sql, Object[] obj) {
        if (getConn() == null) {
            return null;
        }
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rs;
    }


    private Connection getConn() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection
                        ("jdbc:mysql://rerun.it.uts.edu.au/sd01", "sd01", "dwy2mDmW");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("jdbc driver is not found");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}

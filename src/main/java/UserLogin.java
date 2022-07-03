import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.*;
import static java.sql.DriverManager.getConnection;

public class UserLogin {
    public UserLogin() throws SQLException {
        Connection conn;
        boolean connect = validateConn();
    }

    public static boolean validateConn() throws SQLException {
        Connection conn = getConnection(Main.DB_URL, Main.USER, Main.PASS);
        System.out.println("Successful Connection");
        return true;
    }

    public static boolean validateUser(String userName, String email)
            throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        PreparedStatement pstmt = conn.prepareStatement(
                "select * from user_tbl where user_name = ? or " +
                        "user_email = ?");

        pstmt.setString(1, userName);
        pstmt.setString(2, email);

        ResultSet rs = pstmt.executeQuery();
        boolean status = rs.next();
        if(status){
            System.out.println("Username or Email Exist!");
        }
        return status;
    }


    public static List<String> LoginUser() throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        List <String> r = new ArrayList<String>();

        System.out.println("[Username]: ");
        Scanner myObj = new Scanner(System.in);
        String userName = myObj.nextLine();
        System.out.println("[Password]: ");
        String password = myObj.nextLine();

        PreparedStatement pstmt = conn.prepareStatement(
                "select * from user_tbl where user_name = ? and user_password = ? ");

        pstmt.setString(1, userName);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();
        boolean status = rs.next();
        if(status){
            System.out.println("Login Successful!");
            r.add(userName);
            r.add(password);
            r.add(null);
            r.add(null); // May be added later
            loginUpdate(conn,userName,password);
            return r;
        }
        else {
            System.out.println("User not found or Password not correct");
            return r;
        }


    }

    public static List<String> LoginUser(String userName, String password)
            throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);

        List <String> r = new ArrayList<String>();
        PreparedStatement pstmt = conn.prepareStatement(
                "select * from user_tbl where user_name = ? and user_password = ? ");

        pstmt.setString(1, userName);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();
        boolean status = rs.next();
        if(status){
            System.out.println("Login Successful!");
            r.add(userName);
            r.add(password);
            r.add(null);
            r.add(null); // May be added later
            return r;
        }
        else {
            System.out.println("User not found or Password not correct");
            return r;
        }
    }
    public static void loginUpdate(Connection conn, String userName,
                                   String password) throws SQLException {
        System.out.println("Update Latest Login Time ");
        String sqlhit = "SELECT user_id\n" +
                "  FROM user_tbl " +
                "  WHERE user_name =? and user_password = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        int uid = rs.getInt(1);
        String sqladd = "UPDATE user_tbl " +
                "SET user_last_login = now() WHERE user_id=?;";
        pstmt = conn.prepareStatement(sqladd);
        pstmt.setInt(1, uid);
        pstmt.executeUpdate();
    }
}
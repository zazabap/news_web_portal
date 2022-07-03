import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class UserRegister {

    public static void main(String[] args) throws SQLException {}

    public UserRegister() throws SQLException {
        boolean connect = validateConn();
    }

    private boolean validateConn() throws SQLException {
        Connection conn = getConnection(Main.DB_URL, Main.USER, Main.PASS);
        System.out.println("Successful Connection");
        return true;
    }

    public static List<String> registerUser() throws SQLException {
        String userName,password, email,phone;
        boolean usercheck;
        do{
            System.out.println("[New Username]: ");
            Scanner myObj = new Scanner(System.in);
            userName = myObj.nextLine();
            System.out.println("[New Password]: ");
            password = myObj.nextLine();
            System.out.println("[New Email]: ");
            email = myObj.nextLine();
            System.out.println("[New Phone]: ");
            phone = myObj.nextLine();
            usercheck = UserLogin.validateUser(userName, email);
            if (usercheck) System.out.println("User Name Registered!");
        }while (usercheck);
        System.out.println("User Name Available! ");

        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT into user_tbl(user_id,user_name,user_password,user_email,user_phone) " +
                        "values (?,?,?,?,?);");

        pstmt.setInt(1, 0);
        pstmt.setString(2, userName);
        pstmt.setString(3, password);
        pstmt.setString(4, email);
        pstmt.setString(5, phone);
        pstmt.executeUpdate();
        System.out.println("Register Successful!");

        List<String> r = new ArrayList<>();
        r.add(userName);
        r.add(password);
        r.add(email);
        r.add(phone);
        return r;
    }
}

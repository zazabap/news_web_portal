import java.sql.*;
import java.util.Scanner;

public class Main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "user";
    static final String PASS = "P@ssW0rd";

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        try{
            //STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection Success!");
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT user_id, user_phone, user_name, user_password FROM user_tbl";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Welcome to news website!");
            System.out.println("What would you like to do?");
            System.out.println("""
                    Without Login:
                    0 See Top News
                    User:
                    1 Create New User Account and Login
                    2 Login and Edit Name/Avatar
                    3 Login and Search Top News
                    ============News======================
                    4 Login and Browse News base on Field
                    5 Login and Browse News base on Topics
                    6 Login and Browse News Comment
                    7 Login and upvote downvote news
                    8 Login and favorite share news
                    ============News Comment=============
                    9 Login and comments on news 
                    10 Login and upvote downvote comments
                    11 Login and delete comments
                    12 Login and view comment history
                    13 Login and view browse history
                    
                    Admin:
                    14 Login and set new Advertisement
                    15 Login and get Advertisement based on type
                    16 Login and add/edit/delete news
                    """);

            System.out.println("Your Choice: ");
            Scanner myObj = new Scanner(System.in);
            int u = Integer.parseInt(myObj.nextLine());

            UserAccount usr = new UserAccount();
            if (u == 1){
                usr.register();
                usr.login();
            }
            if (u > 1 & u<=13) {
                usr.login();
                if(u == 2){
                    usr.setUserAccount();
                }
                if(u>=3 & u<=8){
                    usr.searchNews();
                }
                if(u>=9 & u<=11){
                    usr.searchComments();
                }
                if(u>11){
                    usr.searchHistory();
                }
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}

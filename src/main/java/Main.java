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
                    User:
                    ============Edit==========================
                    1 Create New User Account and Login
                    2 Login and Edit Name/Avatar
                    ============News==========================
                    3 Login and Browse Latest News
                    4 Login and Browse Top Hit News 
                    5 Login and Browse News base on Author
                    6 Login and favorite share news
                    ============Comment=======================
                    7 Login and comments on news 
                    8 Login and upvote downvote comments
                    9 Login and delete comments
                    ============History=======================
                    10 Login and view comment history
                    11 Login and view browse history
                   
                    Admin:
                    =========Advertisement====================
                    12 Login and set new Advertisement
                    13 Login and get Advertisement based on type
                    ============News==========================
                    14 Login and add news
                    15 Login and delete news
                    16 Login and edit news
                    """);

            System.out.println("Your Choice: ");
            Scanner myObj = new Scanner(System.in);
            int u = Integer.parseInt(myObj.nextLine());
            UserAccount usr = new UserAccount();
            AdminAccount adm = new AdminAccount();

            // Done
            if (u == 1){
                usr.register();
                usr.login();
            }

            // Done
            if (u == 2){
                usr.login();
                usr.editAccount();
            }
            // Done
            if( u == 3){
                usr.login();
                usr.browseLatestNews();
            }

            // Done
            if( u == 4){
                usr.login();
                usr.browseTopNews();
            }

            // Done
            if( u == 5){
                usr.login();
                usr.browseNewsAuthor();
            }

            // Done
            if( u ==6 ){
                usr.login();
                usr.favoriteNews();
            }

            // Done
            if(u ==7 ){
                usr.login();
                usr.browseLatestNews();
                System.out.println("Which News for Comments: ");
                int news_id = Integer.parseInt(myObj.nextLine());
                usr.comment(news_id);
            }

            // Done
            if(u ==8){
                usr.login();
                usr.upvoteComment();
            }

            // Done
            if(u ==9 ){
                usr.login();
                usr.deleteComment();
            }


            if(u == 10) {
                usr.login();
                usr.browseHistory();
            }

            if(u == 11) {
                usr.login();
                usr.browseComment();
            }

            if(u == 12){
                adm.login();
                adm.addAds();
            }

            if(u ==13){
                adm.login();
                adm.browseAds();
            }

            if(u == 14){
                adm.login();
                adm.addNews();
            }

            if(u == 15){
                adm.login();
                adm.deleteNews();
            }

            if(u == 16){
                adm.editNews();
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

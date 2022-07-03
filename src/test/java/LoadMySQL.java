// Author: Shiwen An
// Date: 2022.06.30
// Purpose: Trying to the
//  load the data to MySQL


import java.sql.*;

public class LoadMySQL {    // JDBC driver name and database URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    //  Database credentials -- 数据库名和密码自己修改
    static final String USER = "user";
    static final String PASS = "P@ssW0rd";

    Connection conn = null;
    Statement stmt = null;

    // Mainly for build connection
    public LoadMySQL(){
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


            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int user_id  = rs.getInt("user_id");
                String user_name = rs.getString("user_name");
                String user_phone = rs.getString("user_phone");
                String user_password = rs.getString("user_password");

                //Display values
                System.out.print("user_id: " + user_id + " ");
                System.out.print("user_name: " + user_name+ " ");
                System.out.println("user_phone:" + user_phone+ " ");
                System.out.println("user_password:" + user_password+ " ");

            }
            //STEP 6: Clean-up environment
            rs.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        System.out.println("Goodbye!");
    }//end main

    public static void main(String[] args) {
        LoadMySQL upload = new LoadMySQL();
        System.out.println("Just testing the class object");
    }


}

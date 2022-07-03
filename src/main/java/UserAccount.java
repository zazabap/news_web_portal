import java.sql.*;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class UserAccount {

    private String userName;
    private String password;
    private String email;
    private String phone;

    public UserAccount() {

    }
    public void setUserAccount() {}
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email){this.email = email;}
    public void setPhone(String phone){this.phone = phone;}
    public void setAll(List<String> a){
        userName = a.get(0);
        password = a.get(1);
        email = a.get(2);
        phone = a.get(3);
    }
    public String getUserName() {
        return userName;
    }

    public static String getUserName(Connection conn, int user_id) throws SQLException {
        String uname = "";
        String sqlstmt = "SELECT user_name" +
                "  FROM user_tbl " +
                "  WHERE user_id = ?;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setInt(1, user_id);
        int uid = 0;
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  \n");
                uname = rs.getString(i);
            }
        }
        return uname;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public int getUID() throws SQLException {
        String sqlstmt = "SELECT user_id\n" +
                "  FROM user_tbl " +
                "  WHERE user_name = ? and user_password = ?;";
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        
        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        int uid = 0;
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  \n");
                uid = rs.getInt(i);
            }
        }
        return uid;
    }

    //===========Register Login Edit Related ======================
    public void register() throws SQLException {
        List<String> a = UserRegister.registerUser();
        this.setAll(a);
    }

    public void login() throws SQLException {
        List<String> a = UserLogin.LoginUser();
        this.setAll(a);
    }

    public void login(String userName, String password)
            throws SQLException {
        List<String> a = UserLogin.LoginUser(userName, password);
        this.setAll(a);
    }


    public void editAccount() throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        UserLogin.UpdateUser(conn, userName,password);
    }

    //=========================News Related ======================
    public void browseTopNews() throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        News.NewsTop(conn);
        int nid = News.visitNews(conn);
        UserHistory.addBrowseHistory(conn,nid,userName);
    }

    public void browseLatestNews() throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        News.NewsLatest(conn);
        int nid = News.visitNews(conn);
        UserHistory.addBrowseHistory(conn,nid,userName);
    }

    public void browseNewsAuthor() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        News.NewsAuthor(conn);
        int nid = News.visitNews(conn);
        UserHistory.addBrowseHistory(conn,nid,userName);
    }

    public void favoriteNews() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        News.NewsLatest(conn);
        int nid = News.visitNews(conn);
        UserHistory.addBrowseHistory(conn,nid,userName);
        UserFavorite.addFavorite(conn,nid, userName);
    }

    //=========================Comment Related ======================
    public void comment(int news_id) throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Comment.addComment(conn, userName,
                news_id, getUID());
    }

    public void upvoteComment() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Comment.showAllComment(conn);
        System.out.println("Which review id for Upvote: ");
        Scanner myObj = new Scanner(System.in);
        int rid = Integer.parseInt(myObj.nextLine());
        Comment.VoteComment(conn,rid);
    }

    public void deleteComment() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Comment.showAllComment(conn);
        System.out.println("Which review id for delete: ");
        Scanner myObj = new Scanner(System.in);
        int rid = Integer.parseInt(myObj.nextLine());

        Comment.deleteComment(conn,getUID(),rid);
    }

    public void browseHistory() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        UserHistory.showBrowseHistory(conn, userName);
    }

    public void browseComment() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        UserHistory.showCommentHistory(conn,userName);
    }

}

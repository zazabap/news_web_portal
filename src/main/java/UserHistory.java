import java.sql.*;

public class UserHistory {

    public static void addBrowseHistory(Connection conn,
                                        int news_id,
        String userName) throws SQLException {

        String sqlhit = "INSERT into user_tbl (" +
                "user_name, user_history_browse, user_last_login) "
                + "values (?, ?, now()) ";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setInt(2, news_id);
        pstmt.executeUpdate();

        System.out.println("Add Browse History");
    }

    public static void addCommentHistory(Connection conn,
                                        String userName, int rid)
            throws SQLException {

        String sqlhit = "INSERT into user_tbl (" +
                "user_name, user_history_review, user_last_login)"
                + "values (?, ?, now()) ";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setInt(2, rid);
        pstmt.executeUpdate();
        System.out.println("Add Comment History");
    }

    public static void showBrowseHistory(){}

    public static void showCommentHistory(){}


}

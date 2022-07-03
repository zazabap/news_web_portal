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

        System.out.println("=====Add Browse History=====");
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
        System.out.println("=====Add Comment History=====");
    }

    public static void showBrowseHistory(Connection conn, String userName)
            throws SQLException {

        String sqlstmt = "SELECT DISTINCT user_history_browse\n" +
                "FROM user_tbl WHERE user_name = ? AND user_history_browse IS NOT NULL;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setString(1, userName);

        ResultSet rs = pstmt.executeQuery();
        // Try to debug first.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        int j=0 , l =0;
        l = 10;
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );

            }
            System.out.println("");
            j++;
            if (j >l) break;
        }
        News.visitNews(conn); // if you would like to
    }

    public static void showCommentHistory(Connection conn, String userName)
    throws SQLException {

        String sqlstmt = "SELECT DISTINCT user_history_review\n" +
                "FROM user_tbl WHERE user_name = ? AND user_history_review IS NOT NULL;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setString(1, userName);

        ResultSet rs = pstmt.executeQuery();
        // Try to debug first.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        int j=0 , l =0;
        l = 10;
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
                Comment.showComment(conn, Integer.parseInt(columnValue));
            }
            System.out.println("");
            j++;
            if (j >l) break;
        }

    }


}

import java.sql.*;

public class Comment {

    public Comment(){
    }

    public static void showComment(Connection conn,
                                       int news_id ) throws SQLException {
        String sqlstmt = "SELECT review_content, review_upvote_vol, " +
                "review_downvote_vol, user_id" +
                "  FROM review_tbl " +
                "  WHERE news_id = ?;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setInt(1,news_id);

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        int count =1;
        while (rs.next()) {
            System.out.print("Review :" + count);
            for (int i = 1; i <= columnsNumber-1; i++) {
                if (i > 1) System.out.print(",");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            //Add Commenter's name
            String userName = UserAccount.getUserName(conn, rs.getInt(columnsNumber));
            System.out.print( " " + userName );
            System.out.println("");
            count++;
        }
    }

    public static void addComment(Connection conn,
                                  String userName, int news_id,
                                  int user_id)
    throws SQLException {

        String sqlhit = "INSERT into review_tbl ( user_id, news_id, review_content," +
                "review_datetime, review_upvote_vol, review_downvote_vol) " +
                "values (?, ?, ?, now(),0,0)" ;
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(3, userName+"Comment");
        pstmt.setInt(2, news_id);
        pstmt.setInt(1, user_id);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int rid = rs.getInt(1);

        System.out.println("Add Comment: " + rid);
        UserHistory.addCommentHistory(conn, userName, rid);
    }
    public void VoteComment(){}
    public void deleteComment(){

    }
}

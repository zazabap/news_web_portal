import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Comment {

    public Comment(){
    }

    public static void addComment(Connection conn,
                                  String userName, int news_id,
                                  int user_id)
    throws SQLException {

        String sqlhit = "INSERT into review_tbl (" +
                "user_id, news_id, review_content, " +
                "review_datetime, review_upvote_vol, " +
                "review_downvote_vol,) "
                + "values (?, ?, ?, now(),0,0) ";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setInt(2, news_id);
        pstmt.setInt(3, user_id);

        pstmt.executeUpdate();

        System.out.println("Add Comment");

        sqlhit = "INSERT into user_tbl (" +
                "user_name, user_history_review, user_last_login) "
                + "values (?, ?, now()) ";
        pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setInt(2, news_id);
        pstmt.executeUpdate();

        System.out.println("Add Browse History");

    }
    public void VoteComment(){}
    public void deleteComment(){}
}

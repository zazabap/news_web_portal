import java.sql.*;
import java.util.Scanner;

public class Comment {

    public Comment(){
    }
    public static void showNewsComment(Connection conn,
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

    public static void showAllComment(Connection conn) throws SQLException {
        String sqlstmt = "SELECT review_id review_content, review_upvote_vol, " +
                "review_downvote_vol, user_id" +
                "  FROM review_tbl ;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber-1; i++) {
                if (i > 1) System.out.print(",");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            //Add Commenter's name
            String userName = UserAccount.getUserName(conn, rs.getInt(columnsNumber));
            System.out.print( " " + userName );
            System.out.println("");
        }
    }

    public static void addComment(Connection conn,
                                  String userName, int news_id,
                                  int user_id)
    throws SQLException {

        System.out.println("Please Write your Comment: ");
        Scanner myObj = new Scanner(System.in);
        String cmt = myObj.nextLine();

        String sqlhit = "INSERT into review_tbl ( user_id, news_id, review_content," +
                "review_datetime, review_upvote_vol, review_downvote_vol) " +
                "values (?, ?, ?, now(),0,0)" ;
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(3, cmt);
        pstmt.setInt(2, news_id);
        pstmt.setInt(1, user_id);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int rid = rs.getInt(1);

        System.out.println("=======Add Comment: " + rid+ "========");
        UserHistory.addCommentHistory(conn, userName, rid);
    }

    public static void showComment(Connection conn, int review_id) throws SQLException {
        String sqlhit = "SELECT review_content," +
                " user_id" +
                "  FROM review_tbl " +
                "  WHERE review_id = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setInt(1, review_id);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(", ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
    }
    public static void VoteComment(Connection conn, int review_id) throws SQLException{
        String sqlhit = "SELECT review_upvote_vol \n" +
                "  FROM review_tbl " +
                "  WHERE review_id = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setInt(1, review_id);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        int upvote = rs.getInt(1);
        upvote++;
        System.out.println("======Upvote for the comment"+upvote+"========" );

        String sqladd = "UPDATE review_tbl " +
                "SET review_upvote_vol=? WHERE review_id=?;";
        pstmt = conn.prepareStatement(sqladd);
        pstmt.setInt(1,
                upvote);
        pstmt.setInt(2, review_id);
        pstmt.executeUpdate();
    }
    public static void deleteComment(Connection conn, int uid, int rid)
            throws SQLException{
        String sqlhit = "SELECT user_id, review_id \n" +
                "  FROM review_tbl " +
                "  WHERE review_id = ?;";

        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setInt(1, rid);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        if(uid !=  rs.getInt(1)) {
            System.out.println("=======Comment by other=======");
            return;
        }
        String del = "DELETE FROM review_tbl\n" +
                "    WHERE review_id = ?";
        pstmt = conn.prepareStatement(del);
        pstmt.setInt(1,rid);
        pstmt.executeUpdate();

        del = "DELETE FROM user_tbl\n" +
                "    WHERE user_history_review = ?";
        pstmt = conn.prepareStatement(del);
        pstmt.setInt(1,rid);
        pstmt.executeUpdate();

        System.out.println("=======Comment Deleted=======");

    }
}

import java.sql.*;

public class Advertisement {
    public static void addAds(Connection conn, String title,
                               String author, String content, double weight) throws SQLException {
        PreparedStatement prepStmt = null;

        prepStmt = conn
                .prepareStatement("INSERT into ad_tbl (" +
                        "ad_id, ad_title, ad_datetime, " +
                        "ad_investor, ad_content, ad_weight) "
                        + "values (0, ?, now(), ? ,?, ?) ");
        prepStmt.setString(1, title);
        prepStmt.setString(2, author);
        prepStmt.setString(3, content);
        prepStmt.setDouble(4, weight);
        System.out.println("=======Add News==========");
        System.out.println(prepStmt);
        prepStmt.executeUpdate();

    }

    public static void showAllAds(Connection conn) throws SQLException {
        String sqlstmt = "SELECT ad_id, ad_title, ad_content " +
                "  FROM ad_tbl ;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
    }


    public static void deleteAds(Connection conn, int nid)
            throws SQLException{

        String del = "DELETE FROM news_tbl\n" +
                "    WHERE news_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(del);
        pstmt.setInt(1,nid);
        pstmt.executeUpdate();

        del = "DELETE FROM user_tbl\n" +
                "    WHERE user_history_review = ?";
        pstmt = conn.prepareStatement(del);
        pstmt.setInt(1,nid);
        pstmt.executeUpdate();

    }



}

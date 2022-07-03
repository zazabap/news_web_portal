import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class News {
    Connection conn;
    private String userName;
    public News(){}
    public News(UserAccount usr){
        this.userName = usr.getUserName();
    }

    public static void deleteNews(){

    }
    public static void addNews(){

    }

    public static void NewsLatest(Connection conn) throws SQLException {
        String sqlstmt = "SELECT news_id, news_title, news_datetime\n" +
                "  FROM news_tbl " +
                "  ORDER BY news_datetime DESC;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);

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
    }

    public static void NewsTop(Connection conn) throws SQLException {

        String sqlstmt = "SELECT news_id, news_title, news_hit_vol\n" +
                "  FROM news_tbl " +
                "  ORDER BY news_hit_vol DESC;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);

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
    }

    public static void NewsAuthor(Connection conn) throws SQLException {

        String sqlstmt = "SELECT news_author, count(*) FROM " +
                "news_tbl GROUP BY " +
                "news_author ORDER BY count(*) DESC LIMIT 10;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);

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

        System.out.println("Which Author You want to see?" +
                "[Exact Name: ] ");
        Scanner myObj = new Scanner(System.in);
        String s = myObj.nextLine();

        sqlstmt = "SELECT news_id, news_title, news_author" +
                "  FROM news_tbl " +
                "  WHERE news_author = ?;";
        pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setString(1,s);
        rs = pstmt.executeQuery();
        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
    }

    public static int visitNews(Connection conn) throws SQLException {
        System.out.println("Which News You would like to see [news_id]: ");
        Scanner myObj = new Scanner(System.in);
        int s = Integer.parseInt(myObj.nextLine());
        String sqlstmt = "SELECT *\n" +
                "  FROM news_tbl " +
                "  WHERE news_id = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setInt(1,s);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  \n");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
        hitNews(conn, s);
        Comment.showNewsComment(conn, s);
        return s;
    }

    public static void hitNews(Connection conn, int news_id) throws SQLException {
        String sqlhit = "SELECT news_hit_vol\n" +
                "  FROM news_tbl " +
                "  WHERE news_id = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setInt(1, news_id);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        int addHit = Integer.parseInt(rs.getString(1));
        addHit++;
        System.out.println("======Update Hit Number to"+addHit+"========" );

        String sqladd = "UPDATE news_tbl " +
                "SET news_hit_vol=? WHERE news_id=?;";
        pstmt = conn.prepareStatement(sqladd);
        pstmt.setInt(1,
                addHit);
        pstmt.setInt(2, news_id);
        pstmt.executeUpdate();
    }

    public void VoteNews(){

    }




}

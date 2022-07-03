import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserFavorite {
    public static void addFavorite(Connection conn, int nid, String userName)
    throws SQLException{
        System.out.println("Do you want to add this news to favorite? \n0:No \n1:Yes");
        Scanner myObj = new Scanner(System.in);
        int y = Integer.parseInt(myObj.nextLine());
        if( y!=1) {
            return;
        }
        String sqlhit = "INSERT into user_tbl (" +
                    "user_name, user_favorite, user_last_login) "
                    + "values (?, ?, now()) ";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setInt(2, nid);
        pstmt.executeUpdate();
        System.out.println("=====Add Favorite=====");
    }
}

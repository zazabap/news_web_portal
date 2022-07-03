import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

public class AdminAccount {
    private final String adminName = "admin";
    private final String adminPassword = "root";
    public void login() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("[Admin Name:] ");
        String ad = myObj.nextLine();
        System.out.println("[Admin Password:] ");
        String pw = myObj.nextLine();

        if (pw.equals(adminPassword) & ad.equals(adminName)){
            System.out.println("Admin Access Granted");
        }
        else {
            System.out.println("No Access to ADMIN");
            System.exit(1);
        }

    }
    public void browseAds() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Advertisement.showAllAds(conn);

    }

    public void addAds() throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Scanner myObj = new Scanner(System.in);
        System.out.println("[Ad title: ] ");
        String title = myObj.nextLine();
        System.out.println("[Ad investor: ] ");
        String author = myObj.nextLine();
        System.out.println("[Ad content: ] ");
        String content = myObj.nextLine();
        System.out.println("[Ad weight: ] ");
        double weight = Double.parseDouble(myObj.nextLine());
        Advertisement.addAds(conn, title, author, content, weight);
    }

    public void addNews() throws SQLException {
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Scanner myObj = new Scanner(System.in);
        System.out.println("[news title: ] ");
        String title = myObj.nextLine();
        System.out.println("[news author: ] ");
        String author = myObj.nextLine();
        System.out.println("[news content: ] ");
        String content = myObj.nextLine();
        News.addNews(conn, title, author, content );
    }

    public void deleteNews() throws SQLException{
        Connection conn =
                getConnection(Main.DB_URL, Main.USER,Main.PASS);
        Scanner myObj = new Scanner(System.in);
        System.out.println("[news title: ] ");
        String title = myObj.nextLine();
        News.searchNewsTitle(conn,  title);

        System.out.println("[news id to be deleted: ] ");
        int nid = Integer.parseInt(myObj.nextLine());
        News.deleteNews(conn, nid);
    }

}

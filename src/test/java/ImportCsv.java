
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ImportCsv
{
    static final String testcsv =
            "/Users/shiwenan/IdeaProjects/news_chart/data/myFile0.csv";

    static final String newscsv =
            "/Users/shiwenan/IdeaProjects/news_chart/data/news.csv";

    static final String newstitle=
            "/Users/shiwenan/IdeaProjects/news_chart/data/abcnews-date-text.csv";

    public static void main(String[] args) throws IOException {
        LoadMySQL upload = new LoadMySQL();
//        readCsv();
        loadCsv(upload.conn);
        //readCsvUsingLoad(upload.conn);
    }

    private static void loadCsv( Connection conn)throws FileNotFoundException{

        FileReader fi = new FileReader(testcsv);
        BufferedReader br = new BufferedReader(fi);

        FileReader fi1 = new FileReader(newscsv);
        BufferedReader br1 = new BufferedReader(fi1);

        FileReader fi2 = new FileReader(newstitle);
        BufferedReader br2 = new BufferedReader(fi2);

        java.sql.Timestamp ts;
        PreparedStatement prepStmt = null;

        try
        {
            String rowData, rowData1, rowData2;
            String splitBy = ",";
            String[] rows;
            String[] rowss;
            int i = 0;
            rowData = br.readLine();
            rowData1 = br1.readLine();
            rowData2 = br2.readLine();
            System.out.println(rowData);
            while( (rowData = br.readLine()) != null)
            {
                rowData1 = br1.readLine(); // content
                rowData2 = br2.readLine(); // title
                rowss = rowData2.split(splitBy);

                rows = rowData.split(splitBy);    // use comma as separator
                System.out.println(rowData);
                //System.out.println(rowData);
                System.out.println("Uid: "+ rows[0]);
                System.out.println("Datetime: "+ rows[1]);
                System.out.println("News title: "+ rowss[1]);
                System.out.println("News: "+ rowData1);
                SimpleDateFormat dF = new SimpleDateFormat("MMM/dd/yyyy hh:mm:ss");
                java.util.Date parseDate = dF.parse(rows[1]);
                java.sql.Timestamp timestamp1 = new java.sql.Timestamp(parseDate.getTime());

//                String tmp = "INSERT INTO news_tbl(" +
//                        "news_id, news_title, news_datetime, " +
//                        "news_author, news_content, news_hit_vol) "
//                        + "values (0, \"abc\", now(), \"Shiwen\", \"backend\", 10) ";
//
//                Statement stmt = conn.createStatement();
//                stmt.executeUpdate(tmp);


                prepStmt = conn
                        .prepareStatement("INSERT into news_tbl (" +
                                "news_id, news_title, news_datetime, " +
                                "news_author, news_content, news_hit_vol) "
                                + "values (?, ?, ?, ? ,?, ?) ");
                prepStmt.setInt(1, 0);
                prepStmt.setString(2, rowss[1]);
                prepStmt.setTimestamp(3, timestamp1);
                prepStmt.setString(4, rows[2]);
                prepStmt.setString(5, rowData1);
                prepStmt.setInt(6, Integer.parseInt(rows[3]));
                prepStmt.executeUpdate();

                i++;
                if(i>1000) break;
            }
            System.out.println("csv Successfully read");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private static void readCsv() throws IOException {
        FileReader fi = new FileReader(testcsv);
        BufferedReader br = new BufferedReader(fi);

        FileReader fi1 = new FileReader(newscsv);
        BufferedReader br1 = new BufferedReader(fi1);
        try
        {
            String rowData, rowData1;
            String splitBy = ",";
            String[] rows;
            int i = 0;
            rowData = br.readLine();
            rowData1 = br1.readLine();
            System.out.println(rowData);
            while( (rowData = br.readLine()) != null)
            {
                rows = rowData.split(splitBy);    // use comma as separator
                System.out.println(rowData);
                //System.out.println(rowData);
                System.out.println("Uid: "+ rows[0]);
                System.out.println("Datetime: "+ rows[1]);
                SimpleDateFormat dF = new SimpleDateFormat("MMM/dd/yyyy hh:mm:ss");
                java.util.Date parseDate = dF.parse(rows[1]);
                java.sql.Timestamp timestamp1 = new java.sql.Timestamp(parseDate.getTime());

                rowData1 = br1.readLine();
                System.out.println("News: "+ rowData1);

                i++;
                if(i>10) break;
            }
            System.out.println("csv Successfully read");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //The buffer does not work well


//        try (CSVReader csvReader = new CSVReader(fi);) {
//            String[] rows = null;
//            while ((rows = csvReader.readNext()) != null) {
//                System.out.println("Datetime: "+ rows[2]);
//                System.out.println("Headline: "+ rows[4]);
//                System.out.println("Story Body: " + rows[5]);
//                System.out.println("Products: " + rows[6]);
//                System.out.println("Topics: " + rows[7]);
//            }
//        } catch (CsvValidationException e) {
//            throw new RuntimeException(e);
//        }

        // Simple date formatter
        //https://www.javatpoint.com/java-string-to-date
    }

    public static Instant getDateFromString(String string)
    {
        // Creating an instant object
        Instant timestamp = null;

        // Parsing the string to Date
        timestamp = Instant.parse(string);

        // Returning the converted timestamp
        return timestamp;
    }



    private static void readCsvUsingLoad(Connection connection)
    {
        try
        {

            String loadQuery = "LOAD DATA LOCAL INFILE '" + "C:\\upload.csv" + "' INTO TABLE txn_tbl FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (txn_amount, card_number, terminal_id) ";
            System.out.println(loadQuery);
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
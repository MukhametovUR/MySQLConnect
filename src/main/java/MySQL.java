import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySQL{
    //JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/A";
    private static final String user = "root";
    private static final String pwd = "Abc#123as!";

    //JDBC
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) {
        //String query = "select count(*) from client";
        String query = "SELECT cid,fname,lname,mname,age FROM client;";
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, pwd);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int bdcid = rs.getInt(1);
                String bdfname = rs.getString(2);
                String bdlname = rs.getString(3);
                String bdmname = rs.getString(4);
                int bdage = rs.getInt(5);
                System.out.printf("№:%d   Имя: %s   Фамилия: %s   Отчество: %s    лет: %s  %n",
                        bdcid, bdfname, bdlname, bdmname, bdage);
                /*
                int count = rs.getInt(1);
                System.out.println("Total number of books in the client : " + count);
                */
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
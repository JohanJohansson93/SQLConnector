package Database;

import java.sql.*;
/**
 * This class acts as a connector to the database.
 */
public class sqlConnector {
   /*
        Creates and declares instance variables.
    */
    private Statement statement = null;
    private sqlGUI gui = new sqlGUI(this);
    private String db_name = "";
    private String username = "";
    private String password = "";
    private String computer = "";
    private String url = "jdbc:mysql://" + computer + "/" + db_name + "?user=" + username + "&password=" + password;
    /*
        The constructor calls connectTodb, which tries to connect to the database.
        And then the comments method is called which recieves all comments in the database.
    */
    private sqlConnector(){
        connectTodb();
        comments();
    }
    /*
        This method tries to make a connection to the database.
        If it succeeds the method setTitle is called and a title is presented on the gui.
    */
    private void connectTodb(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            gui.setTitle("Connected to MySQL on: " + computer);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
    }
    /*
        This method fetches all the comments from the database.
        The comments are then presented on the gui.
    */
    public void comments(){
        try {
            String query = "SELECT * FROM guestbook ORDER BY id";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String homepage = resultSet.getString("homepage");
                String comment = resultSet.getString("comment");
                gui.setMessage("NAME: " + name + " EMAIL: " + email + " HOMEPAGE: " + homepage + "\n" + "COMMENT: " + comment + "\n\n");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    /*
        This method adds a post to the database. It takes four strings as arguments.
    */
    public void addComment(String Name, String Email, String Homepage, String Comment) {

        try {
            String postComment = "INSERT INTO guestbook (Name, Email, Homepage, Comment) VALUES ('" + Name + "', '" + Email + "', '" + Homepage + "', '" + Comment + "')";
            statement.executeUpdate(postComment);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*
        The main method which creates an object of this class.
    */
    public static void main(String [] args){
        new sqlConnector();
    }
}

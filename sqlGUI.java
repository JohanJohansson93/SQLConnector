package Database;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.regex.*;

/**
 * This class acts as the gui for Database.sqlConnector.
 * This class extends JFrame and implements the ActionListener.
 */
public class sqlGUI extends JFrame implements ActionListener {
    /*
        Creates and declares instance variables.
    */
    private sqlConnector connect;
    private JPanel panel;
    private JTextField name = new JTextField();
    private JTextField email = new JTextField();
    private JTextField homepage = new JTextField();
    private JTextField comment = new JTextField();
    private JButton btnPost;
    private JTextArea text = new JTextArea();
    private Pattern compile;
    private Matcher match;

    /*
        This constructor takes a reference to Database.sqlConnector.
        It sets up the gui.
    */
    public sqlGUI(sqlConnector connect){
        this.connect = connect;
        btnPost = new JButton("Add Post");
        btnPost.addActionListener(this);
        this.getContentPane().add("Center", new JScrollPane(this.text));
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(new JLabel("Homepage:"));
        panel.add(homepage);
        panel.add(new JLabel("Comment:"));
        panel.add(comment);
        panel.add(new JLabel("Add:"));
        panel.add(btnPost);
        this.getContentPane().add("North", panel);
        this.setLocation(0, 0);
        this.setSize(640, 480);
        this.setVisible(true);
    }

    /*
       This method sets the title of the gui.
   */
    public void setHead(String title){
        this.setTitle(title);
    }
    /*
        This method takes a string as argument.
        It checks if the the string contains any html. If it does
        the string is replaced with CENCUR.
    */
    private String addCencor(String toCompile){
        compile = Pattern.compile("<.*>");
        match = compile.matcher(toCompile);
        return match.replaceAll("Cencor");
    }
    /*
        This method appends the incoming string to the gui.
    */
    public void setMessage(String msg){
        text.append(msg);
    }
    /*
        THis method is invoked when an action is occured.
        It passes the strings through the addCencor method.
        Then it passes the string to the addComment method.
        After passing the strings this method calls the comments method to display all posts.
        Finally this method resets all textfields.
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = addCencor(this.name.getText());
        String email = addCencor(this.email.getText());
        String homepage = addCencor(this.homepage.getText());
        String comment = addCencor(this.comment.getText());
        connect.addComment(name, email, homepage, comment);
        connect.comments();
        this.name.setText("");
        this.email.setText("");
        this.homepage.setText("");
        this.comment.setText("");
    }
}

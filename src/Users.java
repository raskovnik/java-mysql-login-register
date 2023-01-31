import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class Users extends JFrame {
    Users(String user) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("List of Users");

        String query = "SELECT * FROM users";
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/catwo?useSSL=false";
            String uname = "root";
            String pass = "";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, uname, pass);
            PreparedStatement prepStmt = conn.prepareStatement(query);
            ResultSet rs = prepStmt.executeQuery();
            int y = 25;
            JLabel title = new JLabel("List of Users");
            title.setBounds(1, 1, 200, 40);
            this.add(title);
            int counter = 1;
            while (rs.next()) {
                if (Objects.equals(rs.getString("username"), user)) {
                    JLabel u = new JLabel("*" +counter+". "+ rs.getString("username"));
                    u.setBounds(47, y, 100, 40);
                    y += 25;
                    counter++;
                    this.add(u);
                } else {
                    JLabel u = new JLabel(counter+". "+ rs.getString("username"));
                    u.setBounds(50, y, 100, 40);
                    y += 25;
                    counter++;
                    this.add(u);
                }
            }

        } catch (Exception exp) {
            System.out.println(exp);
        }
        this.setLayout(null);
        this.setVisible(true);
    }
}

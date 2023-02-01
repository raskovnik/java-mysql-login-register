import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import io.github.cdimascio.dotenv.Dotenv;

public class Users extends JFrame {
    Dotenv dotenv = Dotenv.load();
    Users(String user) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("List of Users");

        String query = "SELECT * FROM users";
        try {
            String dbUrl = String.format("jdbc:mysql://localhost:3306/%s?useSSL=false", dotenv.get("DB_NAME"));
            String uname = dotenv.get("USERNAME");
            String pass = dotenv.get("USERNAME");
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

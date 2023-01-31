import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JButton login = new JButton("Login");
    JButton back = new JButton("Back");
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    Login() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Login");

        usernameLabel.setBounds(50, 50, 100, 40);
        username.setBounds(200, 50, 100, 40);
        passwordLabel.setBounds(50, 100, 100, 40);
        password.setBounds(200, 100, 100, 40);
        login.setBounds(50, 200, 100, 40);
        back.setBounds(250, 200, 100, 40);

        login.addActionListener(this);
        back.addActionListener(this);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(login);
        this.add(back);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();

        } else {
            String user = username.getText();
            String pwd = String.valueOf(password.getPassword());
            String query = "SELECT * FROM users where username=? AND password=?";
            try {
                //connect to database
                String dbUrl = "jdbc:mysql://localhost:3306/catwo?useSSL=false";
                String uname = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(dbUrl, uname, pass);
                PreparedStatement prepStmt = conn.prepareStatement(query);

                prepStmt.setString(1, user);
                prepStmt.setString(2, pwd);
                ResultSet rs = prepStmt.executeQuery();
                rs.last();
                if (rs.getRow() >= 1) {
                    this.dispose();
                    new Users(user);
                } else {
                    JOptionPane.showMessageDialog(this, "User does not exists, or you entered wrong details");
                }
                rs.beforeFirst();

            // handle exceptions
            } catch (Exception exp) {
                System.out.println(exp);
            }
        }
    }
}

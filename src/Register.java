import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame implements ActionListener {
    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JLabel idLabel = new JLabel("ID Number");
    JLabel genderLabel = new JLabel("Gender");
    JButton register = new JButton("Register");
    JButton back = new JButton("Back");
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField id = new JTextField();
    JRadioButton male = new JRadioButton("Male");
    JRadioButton female = new JRadioButton("Female");
    ButtonGroup gender = new ButtonGroup();

    Register() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Register");

        usernameLabel.setBounds(50, 50, 100, 40);
        username.setBounds(200, 50, 100, 40);
        passwordLabel.setBounds(50, 100, 100, 40);
        password.setBounds(200, 100, 100, 40);
        idLabel.setBounds(50, 150, 100, 40);
        id.setBounds(200, 150, 100, 40);
        genderLabel.setBounds(50, 200, 100, 40);
        male.setBounds(120, 200, 100, 40);
        female.setBounds(220, 200, 100, 40);
        register.setBounds(50, 250, 100, 40);
        back.setBounds(250, 250, 100, 40);

        register.addActionListener(this);
        back.addActionListener(this);

        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(idLabel);
        this.add(id);
        this.add(genderLabel);
        gender.add(male);
        gender.add(female);
        this.add(male);
        this.add(female);
        this.add(register);
        this.add(back);

        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            String user = username.getText();
            String pwd = String.valueOf(password.getPassword());
            String idNo = id.getText();
            String sex = female.isSelected() ? "Female" : "Male";
            
            if (user.isEmpty() || pwd.isEmpty() || idNo.isEmpty() || (!female.isSelected() && !male.isSelected())) {
                JOptionPane.showMessageDialog(this, "Your input contains null fields");
            } else {
                String query = "INSERT INTO users(username, password, id, sex)" + "VALUES(?, ?, ?, ?)";
                try {
                    String dbUrl = "jdbc:mysql://localhost:3306/catwo?useSSL=false";
                    String uname = "root";
                    String pass = "";
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(dbUrl, uname, pass);
                    PreparedStatement prepStmt = conn.prepareStatement(query);

                    prepStmt.setString(1, user);
                    prepStmt.setString(2, pwd);
                    prepStmt.setInt(3, Integer.parseInt(idNo));
                    prepStmt.setString(4, sex);
                    prepStmt.execute();
                    this.dispose();
                    new Users(user);
                } catch (Exception exp) {
                    System.out.println(exp);
                }
            }

        } else {
            this.dispose();
        }
    }
}

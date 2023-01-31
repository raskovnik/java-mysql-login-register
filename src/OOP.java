import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OOP extends JFrame implements ActionListener {
    JButton register;
    JButton login;
   OOP() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("Login and Register");

        //create the required components and set position on the frame
        register = new JButton("Register");
        register.setBounds(100, 200, 100, 40);
        login = new JButton("Login");
        login.setBounds(300, 200, 100, 40);

       // add an action listener to listen for button presses
        register.addActionListener(this);
        login.addActionListener(this);

        // add the created components to the frame
        this.add(register);
        this.add(login);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // create and go to frame when button is clicked
       if (e.getSource() == login) { // if login button is clicked
           new Login();
       } else {
            new Register();
       }
    }
}

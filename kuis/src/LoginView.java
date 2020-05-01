

import java.awt.*;
import javax.swing.*;

public class LoginView extends JFrame{
   
   JButton tombol2;
   JLabel user,pass,judul;
   TextField uname;
   JPasswordField pw;
    
    public LoginView(){
        setTitle("LOGIN");
        judul = new JLabel("LOGIN ADMIN");
        user = new JLabel("Username:");
        pass = new JLabel("Password:");
        uname = new TextField();
        pw = new JPasswordField();
        tombol2 = new JButton("Login");
        
        setLayout(null);
        add(judul);
        add(user);
        add(pass);
        add(uname);
        add(pw);
        add(tombol2);
        
        judul.setBounds(110, 20, 90, 20);
        user.setBounds(50, 60, 100, 20);
        uname.setBounds(150, 60, 100, 20);
        pass.setBounds(50, 90, 100, 20);
        pw.setBounds(150, 90, 100, 20);
        tombol2.setBounds(105, 130, 80, 20);
        
        setSize(320,220);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public String getUname(){
        return uname.getText();
    }
    
    public String getPw(){
         String pass = new String(pw.getPassword());
         return pass;
//        return pw.getPassword().toString();
    }
}

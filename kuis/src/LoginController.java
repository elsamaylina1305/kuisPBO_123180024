
import java.awt.event.*;
import javax.swing.*;

public class LoginController {
    LoginView loginview;
    public LoginController(LoginView loginview){
        this.loginview = loginview;
        
        loginview.tombol2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String uname = loginview.getUname();
                String pw = loginview.getPw();
                
                if(uname.isEmpty() || pw.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Username atau Password kosong");
                } else if (uname.equals("admin") && pw.equals("admin")){
                    loginview.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Login Berhasil");
                    new FilmMVC();
                } else {
                    JOptionPane.showMessageDialog(null, "Username atau Password salah");
                }
            }
        });
    }
}

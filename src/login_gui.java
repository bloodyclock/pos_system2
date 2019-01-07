import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class login_gui {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JPanel RootPanel;
    private JFrame frame = new JFrame("Cashier System");;
    String [] logindata={"0","0","0"};

    public login_gui() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Connect_login co =new Connect_login();
          logindata= co.connect(textField1.getText(),passwordField1.getText());
          if(logindata[0]==""){
              JOptionPane.showMessageDialog(null,"Your ID or Password is not Correct","log in",JOptionPane.WARNING_MESSAGE);
              passwordField1.setText("");
          }
          else{

              MainGui gu = new MainGui(logindata);
              gu.Guitest(logindata);
              RootPanel.hide();
          }
          System.out.println(logindata[1]);
            }
        });
        passwordField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logInButton.doClick();
                }
            }
        });
    }

    public void login_gui(){
        frame.setPreferredSize(new Dimension(400,200));
        frame.setContentPane(new login_gui().RootPanel);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package frameku;


import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.swing.*;
import java.util.prefs.Preferences;

public class login extends JFrame{
    private JButton btn2;
    private JButton btn1;
    private JTextField tf1;
    private JTextField tf2;
    private JPanel flogin;

        public login() {
            setContentPane(flogin);
            btn1.addActionListener(e -> {
            if (tf1.getText().equals("admin")) {
                if (new String(tf2.getText()).equals("admin")) {
                    Preferences pref = Preferences.userRoot().node(Main.class.getName());
                    pref.put("USER_ID","1");
                    login lf = new login();
                    dispose();
                }
            }

        });
    }
}


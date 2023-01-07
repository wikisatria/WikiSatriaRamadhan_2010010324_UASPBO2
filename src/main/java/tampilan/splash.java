package tampilan;
import data.fdata;
import frameku.login;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

public class splash extends JFrame{
    private JPanel mainPanel;
    public splash(){
        setContentPane(mainPanel);
        setSize(640,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Preferences pref =Preferences.userRoot().node(Main.class.getName());
        String userID = pref.get("USER_ID","");
        System.out.println(userID);

        try {
            TimeUnit.SECONDS.sleep(2);
            if (userID.equals("")){
                setVisible(false);
                login lf = new login();
                lf.setVisible(true);
                lf.setSize(320,140);
            } else {
                setVisible(false);
                fdata Fd = new fdata();
                Fd.setVisible(true);
                Fd.setSize(320,120);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

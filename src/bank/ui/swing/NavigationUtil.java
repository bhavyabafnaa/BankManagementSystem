package bank.ui.swing;

import javax.swing.*;

public class NavigationUtil {
    public static void goBack(JFrame current, JFrame target) {
        current.dispose();
        if (target != null) {
            target.setVisible(true);
        }
    }
}

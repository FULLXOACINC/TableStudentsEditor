package Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by alex on 15.3.17.
 */
public class AddComponent {
    private static final String IMG_PATCH = "img/";

    public static void add(Container container, Component component,int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gridBagConstr = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0);
        container.add(component, gridBagConstr);
    }

    public static void add(Container container, String nameLabel,int gridx, int gridy, int gridwidth, int gridheight) {
        JLabel label = new JLabel(nameLabel);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints gridBagConstr = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        container.add(label, gridBagConstr);
    }

    public static JButton makeButton(JButton button, String imgString, ActionListener action){
        button.addActionListener(action);
        ImageIcon img = new ImageIcon(IMG_PATCH + imgString);
        button.setIcon(img);
        return button;
    }
}

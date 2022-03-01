package view.Swing.Dialog;

import javax.swing.*;
import java.awt.*;

public class CreateComponent{
    public static void createJLabel(String title, int x, int y, JPanel contentPanel){
        JLabel lblNewLabel = new JLabel(title);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = x;
        gbc_lblNewLabel.gridy = y;
        contentPanel.add(lblNewLabel, gbc_lblNewLabel);
    }

    public static JTextField createJTextField(String value, int x, int y, JPanel contentPanel){
        JTextField textField = new JTextField();
        textField.setText(value);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = x;
        gbc_textField.gridy = y;
        contentPanel.add(textField, gbc_textField);
        textField.setColumns(10);
        return textField;
    }
}
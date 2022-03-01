package view.Swing.Dialog.Food.Basic;

import controller.FoodChangeListener;
import model.Basic;
import model.Food;
import model.Logs;
import model.SwingModels.ListModels.FoodListModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodBasicAddDialog extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldName;
    private JTextField textFieldCalories;
    private JTextField textFieldCabs;
    private JTextField textFieldProteins;
    private JTextField textFieldFats;

    private Logs logs;
    private FoodChangeListener listener;

    /**
     * Create the dialog.
     */
    public FoodBasicAddDialog(Logs logs) {
        this.logs = logs;
        listener = new FoodChangeListener(logs);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Add Basic Food");
        setSize(new Dimension(300, 240));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblName = new JLabel("Name");
            lblName.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblName = new GridBagConstraints();
            gbc_lblName.anchor = GridBagConstraints.WEST;
            gbc_lblName.insets = new Insets(0, 0, 5, 5);
            gbc_lblName.gridx = 0;
            gbc_lblName.gridy = 0;
            contentPanel.add(lblName, gbc_lblName);
        }
        {
            textFieldName = new JTextField();
            textFieldName.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldName = new GridBagConstraints();
            gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
            gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldName.gridx = 1;
            gbc_textFieldName.gridy = 0;
            contentPanel.add(textFieldName, gbc_textFieldName);
            textFieldName.setColumns(10);
        }
        {
            JLabel lblCalories = new JLabel("Calories");
            lblCalories.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblCalories = new GridBagConstraints();
            gbc_lblCalories.anchor = GridBagConstraints.WEST;
            gbc_lblCalories.insets = new Insets(0, 0, 5, 5);
            gbc_lblCalories.gridx = 0;
            gbc_lblCalories.gridy = 1;
            contentPanel.add(lblCalories, gbc_lblCalories);
        }
        {
            textFieldCalories = new JTextField();
            textFieldCalories.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldCalories = new GridBagConstraints();
            gbc_textFieldCalories.insets = new Insets(0, 0, 5, 0);
            gbc_textFieldCalories.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldCalories.gridx = 1;
            gbc_textFieldCalories.gridy = 1;
            contentPanel.add(textFieldCalories, gbc_textFieldCalories);
            textFieldCalories.setColumns(10);
        }
        {
            JLabel lblCarbs = new JLabel("Carbs");
            lblCarbs.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblCarbs = new GridBagConstraints();
            gbc_lblCarbs.anchor = GridBagConstraints.WEST;
            gbc_lblCarbs.insets = new Insets(0, 0, 5, 5);
            gbc_lblCarbs.gridx = 0;
            gbc_lblCarbs.gridy = 2;
            contentPanel.add(lblCarbs, gbc_lblCarbs);
        }
        {
            textFieldCabs = new JTextField();
            textFieldCabs.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldCabs = new GridBagConstraints();
            gbc_textFieldCabs.insets = new Insets(0, 0, 5, 0);
            gbc_textFieldCabs.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldCabs.gridx = 1;
            gbc_textFieldCabs.gridy = 2;
            contentPanel.add(textFieldCabs, gbc_textFieldCabs);
            textFieldCabs.setColumns(10);
        }
        {
            JLabel lblProteins = new JLabel("Proteins");
            lblProteins.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblProteins = new GridBagConstraints();
            gbc_lblProteins.anchor = GridBagConstraints.WEST;
            gbc_lblProteins.insets = new Insets(0, 0, 5, 5);
            gbc_lblProteins.gridx = 0;
            gbc_lblProteins.gridy = 3;
            contentPanel.add(lblProteins, gbc_lblProteins);
        }
        {
            textFieldProteins = new JTextField();
            textFieldProteins.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldProteins = new GridBagConstraints();
            gbc_textFieldProteins.insets = new Insets(0, 0, 5, 0);
            gbc_textFieldProteins.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldProteins.gridx = 1;
            gbc_textFieldProteins.gridy = 3;
            contentPanel.add(textFieldProteins, gbc_textFieldProteins);
            textFieldProteins.setColumns(10);
        }
        {
            JLabel lblFats = new JLabel("Fats");
            lblFats.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblFats = new GridBagConstraints();
            gbc_lblFats.anchor = GridBagConstraints.WEST;
            gbc_lblFats.insets = new Insets(0, 0, 0, 5);
            gbc_lblFats.gridx = 0;
            gbc_lblFats.gridy = 4;
            contentPanel.add(lblFats, gbc_lblFats);
        }
        {
            textFieldFats = new JTextField();
            textFieldFats.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldFats = new GridBagConstraints();
            gbc_textFieldFats.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldFats.gridx = 1;
            gbc_textFieldFats.gridy = 4;
            contentPanel.add(textFieldFats, gbc_textFieldFats);
            textFieldFats.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton addButton = new JButton("Add");
                addButton.setActionCommand("OK");
                addButton.addActionListener(e -> {
                    var name = textFieldName.getText();
                    var calories = textFieldCalories.getText();
                    var carbs = textFieldCabs.getText();
                    var proteins = textFieldProteins.getText();
                    var fats = textFieldFats.getText();


                    if (name.isEmpty() || calories.isEmpty() || carbs.isEmpty() || proteins.isEmpty() || fats.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields");
                        return;
                    }

                    Food basic = new Basic(name, Double.parseDouble(calories), Double.parseDouble(carbs), Double.parseDouble(proteins), Double.parseDouble(fats));

                    Object[] obj = new Object[]{
                            Logs.LOGS_TYPE.BASIC,
                            basic,
                    };

                    try {
                        listener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));
//                        model.addData(basic.getName());
                        dispose();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        JOptionPane.showMessageDialog(null, "Add failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                });
                buttonPane.add(addButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(e -> dispose());
                buttonPane.add(cancelButton);
            }
        }
    }

}

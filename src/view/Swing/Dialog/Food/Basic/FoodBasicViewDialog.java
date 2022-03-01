package view.Swing.Dialog.Food.Basic;

import controller.FoodChangeListener;
import model.Basic;
import model.Logs;
import model.SwingModels.ListModels.FoodListModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FoodBasicViewDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();

    private FoodBasicEditDialog foodBasicEditDialog;

    private Logs logs;
    private FoodChangeListener foodChangeListener;

    /**
     * Create the dialog.
     */
    public FoodBasicViewDialog(Basic basic, FoodListModel foodListModel, Logs logs) {
        this.logs = logs;
        foodChangeListener = new FoodChangeListener(logs);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("View Basic Food");
        setSize(new Dimension(300, 220));
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
            JLabel lblBasicName = new JLabel(basic.getName());
            lblBasicName.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblBasicName = new GridBagConstraints();
            gbc_lblBasicName.anchor = GridBagConstraints.WEST;
            gbc_lblBasicName.insets = new Insets(0, 0, 5, 0);
            gbc_lblBasicName.gridx = 1;
            gbc_lblBasicName.gridy = 0;
            contentPanel.add(lblBasicName, gbc_lblBasicName);
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
            JLabel lblBasicCalories = new JLabel(String.valueOf(basic.getCalories()));
            lblBasicCalories.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblBasicCalories = new GridBagConstraints();
            gbc_lblBasicCalories.anchor = GridBagConstraints.WEST;
            gbc_lblBasicCalories.insets = new Insets(0, 0, 5, 0);
            gbc_lblBasicCalories.gridx = 1;
            gbc_lblBasicCalories.gridy = 1;
            contentPanel.add(lblBasicCalories, gbc_lblBasicCalories);
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
            JLabel lblBasicCarbs = new JLabel(String.valueOf(basic.getCarbs()));
            lblBasicCarbs.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblBasicCarbs = new GridBagConstraints();
            gbc_lblBasicCarbs.anchor = GridBagConstraints.WEST;
            gbc_lblBasicCarbs.insets = new Insets(0, 0, 5, 0);
            gbc_lblBasicCarbs.gridx = 1;
            gbc_lblBasicCarbs.gridy = 2;
            contentPanel.add(lblBasicCarbs, gbc_lblBasicCarbs);
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
            JLabel lblBasicProteins = new JLabel(String.valueOf(basic.getProteins()));
            lblBasicProteins.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblBasicProteins = new GridBagConstraints();
            gbc_lblBasicProteins.anchor = GridBagConstraints.WEST;
            gbc_lblBasicProteins.insets = new Insets(0, 0, 5, 0);
            gbc_lblBasicProteins.gridx = 1;
            gbc_lblBasicProteins.gridy = 3;
            contentPanel.add(lblBasicProteins, gbc_lblBasicProteins);
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
            JLabel lblBasicFats = new JLabel(String.valueOf(basic.getFats()));
            lblBasicFats.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblBasicFats = new GridBagConstraints();
            gbc_lblBasicFats.anchor = GridBagConstraints.WEST;
            gbc_lblBasicFats.gridx = 1;
            gbc_lblBasicFats.gridy = 4;
            contentPanel.add(lblBasicFats, gbc_lblBasicFats);
        }
        // edit button
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton editButton = new JButton("Edit");
                editButton.setActionCommand("Edit");
                editButton.addActionListener(e -> {
                    foodBasicEditDialog = new FoodBasicEditDialog(basic, logs);
                    foodBasicEditDialog.setVisible(true);
                    dispose();
                });
                buttonPane.add(editButton);
            }
            // remove button
            {
                JButton removeButton = new JButton("Remove");
                removeButton.setActionCommand("Remove");
                removeButton.addActionListener(e -> {
                            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this food?", "Remove food", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                Object[] obj = new Object[]{
                                        Logs.LOGS_TYPE.BASIC,
                                        basic.getName(),
                                };

                                try {
                                    if (foodListModel.removeData(basic.getName())) {
                                        foodChangeListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "delete"));
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "The food exists in the food list of a recipe. So the delete operation cannot be performed.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                    }

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                );
                buttonPane.add(removeButton);
            }
            // cancel button
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(e -> dispose());
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}

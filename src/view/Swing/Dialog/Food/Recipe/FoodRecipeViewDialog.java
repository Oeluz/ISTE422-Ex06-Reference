package view.Swing.Dialog.Food.Recipe;

import controller.FoodChangeListener;
import model.Food;
import model.Logs;
import model.Recipe;
import model.SwingModels.ListModels.FoodListModel;
import model.SwingModels.ListModels.RecipeEditListModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FoodRecipeViewDialog extends JDialog {
    private final JPanel contentPanel = new JPanel();

    private Logs logs;
    private FoodListModel foodListModel;
    private FoodChangeListener foodChangeListener;

    /**
     * Create the dialog.
     */
    public FoodRecipeViewDialog(Recipe recipe, FoodListModel foodListModel, Logs logs) {
        this.logs = logs;
        this.foodListModel = foodListModel;
        this.foodChangeListener = new FoodChangeListener(logs);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("View Recipe Food");
        setSize(new Dimension(450, 300));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblName = new JLabel("Name");
            lblName.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblName = new GridBagConstraints();
            gbc_lblName.insets = new Insets(0, 0, 5, 5);
            gbc_lblName.anchor = GridBagConstraints.EAST;
            gbc_lblName.gridx = 0;
            gbc_lblName.gridy = 0;
            contentPanel.add(lblName, gbc_lblName);
        }
        {
            JLabel lblRecipeName = new JLabel(recipe.getName());
            lblRecipeName.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblRecipeName = new GridBagConstraints();
            gbc_lblRecipeName.anchor = GridBagConstraints.WEST;
            gbc_lblRecipeName.insets = new Insets(0, 0, 5, 0);
            gbc_lblRecipeName.gridx = 1;
            gbc_lblRecipeName.gridy = 0;
            contentPanel.add(lblRecipeName, gbc_lblRecipeName);
        }
        {
            JLabel lblFood = new JLabel("Food");
            lblFood.setToolTipText("Hold \"ctrl\" keyboard to multiple selection.");
            lblFood.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblFood = new GridBagConstraints();
            gbc_lblFood.insets = new Insets(0, 0, 0, 5);
            gbc_lblFood.gridx = 0;
            gbc_lblFood.gridy = 1;
            contentPanel.add(lblFood, gbc_lblFood);
        }
        {
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            GridBagConstraints gbc_scrollPane = new GridBagConstraints();
            gbc_scrollPane.fill = GridBagConstraints.BOTH;
            gbc_scrollPane.gridx = 1;
            gbc_scrollPane.gridy = 1;
            contentPanel.add(scrollPane, gbc_scrollPane);
            {
                JList<String> list = new JList<>();
                list.setFont(new Font("Arial", Font.PLAIN, 16));
                list.setVisibleRowCount(-1);
                list.setModel(new AbstractListModel<>() {

                    final String[] values = recipe.getFoods().stream().map(Food::getName).toArray(String[]::new);

                    public int getSize() {
                        return values.length;
                    }

                    public String getElementAt(int index) {
                        return values[index];
                    }
                });
                scrollPane.setViewportView(list);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton editButton = new JButton("Edit");
                editButton.setActionCommand("OK");
                editButton.addActionListener(e -> {
                            try {
                                new FoodRecipeEditDialog(recipe, logs, new RecipeEditListModel(logs, recipe.getName())).setVisible(true);
                                dispose();
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                );

                buttonPane.add(editButton);
            }
            {
                JButton removeButton = new JButton("Remove");
                removeButton.addActionListener(e -> {
                            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this recipe?", "Remove food", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {

                                Object[] obj = new Object[]{
                                        Logs.LOGS_TYPE.RECIPE,
                                        recipe.getName(),
                                };

                                try {
                                    if (foodListModel.removeData(recipe.getName())) {
                                        foodChangeListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "delete"));
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "The recipe exists in the food list of a recipe. So the delete operation cannot be performed.", "Error", JOptionPane.ERROR_MESSAGE);
                                    }

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                );
                buttonPane.add(removeButton);
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

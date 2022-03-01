package view.Swing.Dialog.Food.Recipe;

import controller.FoodChangeListener;
import model.Food;
import model.Logs;
import model.Recipe;
import model.SwingModels.ListModels.RecipeEditListModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

public class FoodRecipeEditDialog extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField textField;

    private RecipeEditListModel recipeEditListModel;
    private FoodChangeListener foodChangeListener;

    /**
     * Create the dialog.
     */
    public FoodRecipeEditDialog(Recipe recipe, Logs logs, RecipeEditListModel recipeEditListModel) {
        this.recipeEditListModel = recipeEditListModel;
        this.foodChangeListener = new FoodChangeListener(logs);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Add Recipe Food");
        setSize(new Dimension(450, 300));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{100, 100, 0};
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
            textField = new JTextField(recipe.getName());
            textField.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textField = new GridBagConstraints();
            gbc_textField.insets = new Insets(0, 0, 5, 0);
            gbc_textField.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField.gridx = 1;
            gbc_textField.gridy = 0;
            contentPanel.add(textField, gbc_textField);
            textField.setColumns(10);
        }
        {
            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.gridwidth = 2;
            gbc_panel.insets = new Insets(0, 0, 0, 5);
            gbc_panel.fill = GridBagConstraints.BOTH;
            gbc_panel.gridx = 0;
            gbc_panel.gridy = 1;
            contentPanel.add(panel, gbc_panel);
            GridBagLayout gbl_panel = new GridBagLayout();
            gbl_panel.columnWidths = new int[]{210, 0, 0};
            gbl_panel.rowHeights = new int[]{0, 0, 0};
            gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
            panel.setLayout(gbl_panel);
            {
                JLabel lblNewLabel = new JLabel("Current");
                lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
                GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
                gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
                gbc_lblNewLabel.gridx = 0;
                gbc_lblNewLabel.gridy = 0;
                panel.add(lblNewLabel, gbc_lblNewLabel);
            }
            {
                JLabel lblNewLabel_1 = new JLabel("Food");
                lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 16));
                GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
                gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
                gbc_lblNewLabel_1.gridx = 1;
                gbc_lblNewLabel_1.gridy = 0;
                panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
            }
            {
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                GridBagConstraints gbc_scrollPane = new GridBagConstraints();
                gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
                gbc_scrollPane.fill = GridBagConstraints.BOTH;
                gbc_scrollPane.gridx = 0;
                gbc_scrollPane.gridy = 1;
                panel.add(scrollPane, gbc_scrollPane);
                {
                    JList<String> list = new JList();
                    list.setVisibleRowCount(-1);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    list.setFont(new Font("Arial", Font.PLAIN, 16));
                    list.setModel(recipeEditListModel);
                    scrollPane.setViewportView(list);
                }
            }
            {
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                GridBagConstraints gbc_scrollPane = new GridBagConstraints();
                gbc_scrollPane.fill = GridBagConstraints.BOTH;
                gbc_scrollPane.gridx = 1;
                gbc_scrollPane.gridy = 1;
                panel.add(scrollPane, gbc_scrollPane);
                {
                    JList<String> list = new JList<>();
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    list.setVisibleRowCount(-1);
                    list.setFont(new Font("Arial", Font.PLAIN, 16));
                    list.setModel(new AbstractListModel<>() {
                        final String[] values = logs.foods.values().stream().map(Food::getName).toArray(String[]::new);

                        @Override
                        public int getSize() {
                            return values.length;
                        }

                        @Override
                        public String getElementAt(int index) {
                            return values[index];
                        }
                    });
                    list.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            var list = (JList) evt.getSource();
                            if (evt.getClickCount() == 2) {
                                int index = list.locationToIndex(evt.getPoint());
                                if (index >= 0) {
                                    String selected = (String) list.getModel().getElementAt(index);
                                    recipeEditListModel.addData(selected);
                                }
                            }
                        }
                    });
                    scrollPane.setViewportView(list);
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton saveButton = new JButton("Save");
                saveButton.setActionCommand("OK");
                saveButton.addActionListener(e -> {
                    var name = textField.getText();
                    var addFoods = recipeEditListModel.getData();

                    if (name.isEmpty() || addFoods.length == 0) {
                        JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    Object[] obj = new Object[]{
                            Logs.LOGS_TYPE.RECIPE,
                            recipe.getName(),
                            name,
                            addFoods,
                            "s"
                    };

                    try {
                        foodChangeListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Add failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
                buttonPane.add(saveButton);
                getRootPane().setDefaultButton(saveButton);
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

package view.Swing.Dialog.LogEntry.Add;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LogChangeListener;
import model.*;
import view.Swing.Dialog.CreateComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class LogEntryAddFoodDialog extends JDialog {
    private Logs logs;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldDate;
    private JTextField textFieldServings;
    private LogChangeListener logListener;
    private JList list;

    /**
     * Create the dialog.
     */
    public LogEntryAddFoodDialog(Logs logs) {
        this.logs = logs;
        this.logListener = new LogChangeListener(logs);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Add Log Food");
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{100, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        
        JLabel lblNewLabel = new JLabel("Date");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        contentPanel.add(lblNewLabel, gbc_lblNewLabel);

        CreateComponent.createJLabel("Date", 0, 0, contentPanel);
        textFieldDate = CreateComponent.createJTextField(String.valueOf(LocalDate.now()), 1, 0, contentPanel);
        CreateComponent.createJLabel("Serving Consumed", 0, 1, contentPanel);
        textFieldServings = CreateComponent.createJTextField("0", 1, 1, contentPanel);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridwidth = 2;
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 3;
        contentPanel.add(panel, gbc_panel);

        panel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        {
            list = new JList();
            list.setFont(new Font("Arial", Font.PLAIN, 16));
            list.setModel(new AbstractListModel<>() {
                final String[] values = logs.foods.values().stream().map(Food::getName).toArray(String[]::new);

                public int getSize() {
                    return values.length;
                }

                public String getElementAt(int index) {
                    return values[index];
                }
            });
            scrollPane.setViewportView(list);
        }
        {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                try {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this log?", "Update", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {

                        ArrayList<Food> tempFood = new ArrayList<>(logs.foods.values());
                        Food food = tempFood.get(list.getSelectedIndex());
                        LogEntry log = new LogEntry(LocalDate.parse(textFieldDate.getText()), LogEntry.LOG_TYPE.FOOD, food, Double.parseDouble(textFieldServings.getText()));
                        Object[] obj = new Object[]{
                            -1, //0
                            "food", //1
                            log, //2
                        };

                        logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
        {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            buttonPane.add(cancelButton);
        }
        }
    }
}

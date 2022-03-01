package view.Swing.Dialog.LogEntry.Edit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LogChangeListener;
import model.*;
import view.Swing.Dialog.CreateComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.*;

public class LogEntryEditFoodDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final Logs logs;
    private JTextField textFieldDate;
    private JTextField textFieldServings;
    private JList list;
    private final Collection<Food> keySet;
    private final ArrayList<Food> arrayList;
    private final int index;
    private final LogChangeListener logListener;

    /**
     * Create the dialog.
     */
    public LogEntryEditFoodDialog(Logs logs, LogChangeListener logListener, int index) {
        this.logs = logs;
        this.logListener = logListener;
        this.index = index;
        keySet = logs.foods.values();
        arrayList = new ArrayList<Food>(keySet);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Edit Log Food");
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

        CreateComponent.createJLabel("Date", 0, 0, contentPanel);
        textFieldDate = CreateComponent.createJTextField(logs.logEntries.get(index).getDaily().toString(), 1, 0, contentPanel);
        CreateComponent.createJLabel("Serving Consumed", 0, 1, contentPanel);
        textFieldServings = CreateComponent.createJTextField(String.valueOf(logs.logEntries.get(index).getServingConsumed()), 1, 1, contentPanel);

        JLabel lblNewLabel_2 = new JLabel("Food");
        lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 16));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.gridwidth = 2;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 0;
        gbc_lblNewLabel_2.gridy = 2;
        contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

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
            int count = 0;
            for(Food food : arrayList){
                if (food.getName().equals(logs.logEntries.get(index).getFood().getName())){
                    list.setSelectedIndex(count);
                    break;
                }
                count++;
            }
            scrollPane.setViewportView(list);
            list.ensureIndexIsVisible(count);
        }
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> {
                try {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this log?", "Update", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {

                        LogEntry log = logs.logEntries.get(index);
                        log.setDaily(LocalDate.parse(textFieldDate.getText()));
                        log.setFood(arrayList.get(list.getSelectedIndex()));
                        log.setServingConsumed(Double.parseDouble(textFieldServings.getText()));

                        Object[] obj = new Object[]{
                            index, //0
                            "food", //1
                            log, //2
                        };

                        logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
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

package view.Swing.Dialog.LogEntry.Edit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LogChangeListener;
import model.*;
import view.Swing.Dialog.CreateComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class LogEntryEditCaloriesDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private Logs logs;
    private JTextField textFieldDate;
    private JTextField textFieldCalories;
    private LogChangeListener logListener;
    private final int index;

    /**
     * Create the dialog.
     */
    public LogEntryEditCaloriesDialog(Logs logs, LogChangeListener logListener, int index) {
        this.logs = logs;
        this.logListener = logListener;
        this.index = index;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Edit Log Calories");
        setBounds(100, 100, 320, 150);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{100, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);

        CreateComponent.createJLabel("Date", 0, 0, contentPanel);
        textFieldDate = CreateComponent.createJTextField(logs.logEntries.get(index).getDaily().toString(), 1, 0, contentPanel);

        CreateComponent.createJLabel("Limit Calories", 0, 1, contentPanel);
        textFieldCalories = CreateComponent.createJTextField(String.valueOf(logs.logEntries.get(index).getValue()), 1, 1, contentPanel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton saveButton = new JButton("Save");
            buttonPane.add(saveButton);
            saveButton.addActionListener(e -> {
                try {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this log?", "Update", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {

                        LogEntry log = logs.logEntries.get(index);
                        log.setDaily(LocalDate.parse(textFieldDate.getText()));
                        log.setValue(Double.parseDouble(textFieldCalories.getText()));

                        Object[] obj = new Object[]{
                            index, //0
                            "calories", //1
                            log
                        };

                        logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            getRootPane().setDefaultButton(saveButton);
        }
        {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            buttonPane.add(cancelButton);
        }
    }
}

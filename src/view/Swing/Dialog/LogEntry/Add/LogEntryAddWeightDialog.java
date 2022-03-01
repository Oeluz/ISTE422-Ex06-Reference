package view.Swing.Dialog.LogEntry.Add;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.LogChangeListener;
import model.*;
import view.Swing.Dialog.CreateComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class LogEntryAddWeightDialog extends JDialog {
    private Logs logs;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldDate;
    private JTextField textFieldWeight;
    private LogChangeListener logListener;

    /**
     * Create the dialog.
     */
    public LogEntryAddWeightDialog(Logs logs) {
        this.logs = logs;
        logListener = new LogChangeListener(logs);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Add Log Weight");
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
        textFieldDate = CreateComponent.createJTextField(String.valueOf(LocalDate.now()), 1, 0, contentPanel);
        CreateComponent.createJLabel("Weight", 0, 1, contentPanel);
        textFieldWeight = CreateComponent.createJTextField("0", 1, 1, contentPanel);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton saveButton = new JButton("Save");
            buttonPane.add(saveButton);
            getRootPane().setDefaultButton(saveButton);

            saveButton.addActionListener(e -> {
                try {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this log?", "Update", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {

                        LogEntry log = new LogEntry(LocalDate.parse(textFieldDate.getText()), LogEntry.LOG_TYPE.WEIGHT, Double.parseDouble(textFieldWeight.getText()));

                        Object[] obj = new Object[]{
                            -1, //0
                            "weight", //1
                            log, //2
                        };

                        logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));
                        dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        {
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            buttonPane.add(cancelButton);
        }
    }
}

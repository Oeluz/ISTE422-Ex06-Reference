package view.Swing.Dialog.LogEntry;

import controller.LogChangeListener;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LogEntryViewDialog extends JDialog implements Observer{

    private final JPanel contentPane = new JPanel();
    private JLabel lblDate;
    private JLabel lblType;
    private JLabel lblValue;
    private LogChangeListener logListener;
    private Logs logs;
    private int index;

    /**
     * Create the dialog.
     */
    public LogEntryViewDialog(Logs logs, LogChangeListener logListener, int index) {
        this.logs = logs;
        this.logListener = logListener;
        this.index = index;
        logs.addObserver(this);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 200);
        setContentPane(contentPane);
        // set GridBagLayout
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        setLayout(gbl_contentPane);

        // Label
        lblDate = new JLabel(logs.logEntries.get(index).getDaily().toString());
        GridBagConstraints gbc_lblDate = new GridBagConstraints();
        gbc_lblDate.insets = new Insets(0,0,5,5);
        gbc_lblDate.gridx = 0;
        gbc_lblDate.gridy = 0;
        gbc_lblDate.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(lblDate, gbc_lblDate);

        lblType = new JLabel(logs.logEntries.get(index).getLogType().toString());
        GridBagConstraints gbc_lblType = new GridBagConstraints();
        gbc_lblType.insets = new Insets(0,0,5,5);
        gbc_lblType.gridx = 0;
        gbc_lblType.gridy = 1;
        gbc_lblType.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(lblType, gbc_lblType);

        lblValue = new JLabel();
        switch(logs.logEntries.get(index).getLiteralLetter()){
            case "w":
            case "c": lblValue.setText(String.valueOf(logs.logEntries.get(index).getValue())); break;
            case "f": lblValue.setText(logs.logEntries.get(index).getFood().toString()); break;
            case "e": lblValue.setText(logs.logEntries.get(index).getExercise().toString()); break;
        }

        GridBagConstraints gbc_lblValue = new GridBagConstraints();
        gbc_lblValue.insets = new Insets(0,0,5,5);
        gbc_lblValue.gridx = 0;
        gbc_lblValue.gridy = 3;
        gbc_lblValue.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(lblValue, gbc_lblValue);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        GridBagConstraints gbc_buttonPane = new GridBagConstraints();
        gbc_buttonPane.insets = new Insets(0,0,0,0);
        gbc_buttonPane.gridx = 2;
        gbc_buttonPane.gridy = 4;
        contentPane.add(buttonPane, gbc_buttonPane);

        JButton editButton = new JButton("Edit");
        editButton.setActionCommand("edit_s");
        editButton.addActionListener(logListener);
        buttonPane.add(editButton);

        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand("delete_s");
        buttonPane.add(removeButton);
        removeButton.addActionListener(e -> {
            try {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this log?", "Remove log", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    logListener.actionPerformed(e);
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
    }

    public void update(Observable obs, Object o) {
        /*
         * If received from other than the logs we're
         * observing, just return.
         */
        if( obs != logs ) {
            return ;
        }

        switch(logs.logEntries.get(index).getLiteralLetter()){
            case "w":
            case "c": lblValue.setText(String.valueOf(logs.logEntries.get(index).getValue())); break;
            case "f": lblValue.setText(logs.logEntries.get(index).getFood().toString()); break;
            case "e": lblValue.setText(logs.logEntries.get(index).getExercise().toString()); break;
        }
    }
}

package controller;

import model.Logs;
import view.Swing.Dialog.LogEntry.LogEntryViewDialog;
import view.Swing.Dialog.LogEntry.Edit.*;

import java.awt.event.*;

import javax.swing.JDialog;
import javax.swing.JList;

/**
 * This class is used to listen to the log change events. It is used to update
 * the log in the database.
 *
 * @author TeamZXQ
 * @see Logs
 */
public class LogChangeListener extends MouseAdapter implements ActionListener {

    Logs logs;
    LogEntryViewDialog dialog;
    JDialog editDialog;
    int index;

    /**
     * Constructor for the LogChangeListener class.
     *
     * @param logs The database to update. This is used to update the log.
     */
    public LogChangeListener(Logs logs) {
        this.logs = logs;
    }

    /**
     * This method is used to update the log in the database.
     *
     * @param e ActionEvent object.
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "create":
                logs.createDailyLog((Object[]) e.getSource());
                break;
            case "get":
                var logsType = (Logs.LOGS_TYPE) e.getSource();
                logs.getDailyLog(logsType);
                break;
            case "update":
                logs.updateDailyLog((Object[]) e.getSource());
                break;
            case "delete":
                logs.deleteDailyLog((Object[]) e.getSource());
                break;
            // dialog buttons
            case "edit_s": openEditDialog();
                break;
            case "delete_s":
                logs.deleteDailyLog(new Object[]{Logs.LOGS_TYPE.DAILY_LOG, index});
                close();
                break;
        }
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        var list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            index = list.locationToIndex(evt.getPoint());
            if (index >= 0) {
                close();
                dialog = new LogEntryViewDialog(logs, this, index);
                dialog.setVisible(true);
            }
        }
    }

    public void close(){
        if(dialog != null)
            dialog.dispose();

        if(editDialog != null)
            editDialog.dispose();;
    }


    public void openEditDialog(){
        switch(logs.logEntries.get(index).getLiteralLetter()){
            case "w": editDialog = new LogEntryEditWeightDialog(logs, this, index); break;
            case "c": editDialog = new LogEntryEditCaloriesDialog(logs, this, index); break;
            case "f": editDialog = new LogEntryEditFoodDialog(logs, this, index); break;
            case "e": editDialog = new LogEntryEditExerciseDialog(logs, this, index); break;
        }
        editDialog.setVisible(true);
    }
}
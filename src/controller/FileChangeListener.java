package controller;

import model.Logs;

import java.awt.event.*;

/**
 * This class is used to listen for file changes. It is used to update the model
 * when a file is changed.
 *
 * @author TeamZXQ
 * @see Logs
 */
public class FileChangeListener implements ActionListener {
    Logs logs;

    /**
     * Constructor for the FileChangeListener.
     *
     * @param logs The database to update.
     */
    public FileChangeListener(Logs logs) {
        this.logs = logs;
    }

    /**
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load":
                logs.loadCSVToData();
                break;
            case "save":
                logs.saveCSVToData();
                break;
        }
    }
}
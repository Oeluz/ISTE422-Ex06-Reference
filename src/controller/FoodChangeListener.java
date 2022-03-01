package controller;

import model.Logs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to listen for file changes. It is used to update the model
 * when a file is changed.
 *
 * @author TeamZXQ
 * @see Logs
 */
public class FoodChangeListener implements ActionListener {
    Logs logs;

    /**
     * Constructor for the FoodChangeListener.
     *
     * @param logs The database to update.
     */
    public FoodChangeListener(Logs logs) {
        this.logs = logs;
    }

    /**
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "create":
                logs.createFood((Object[]) e.getSource());
                break;
            case "get":
                var logsType = (Logs.LOGS_TYPE) e.getSource();
                logs.getFood(logsType);
                break;
            case "update":
                logs.updateFood((Object[]) e.getSource());
                break;
            case "delete":
                logs.deleteFood((Object[]) e.getSource());
                break;
        }
    }
}
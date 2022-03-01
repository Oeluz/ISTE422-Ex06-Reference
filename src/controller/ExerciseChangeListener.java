package controller;

import model.Logs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseChangeListener implements ActionListener {
    Logs logs;

    public ExerciseChangeListener(Logs logs) {
        this.logs = logs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "create":
                logs.createExercise((Object[]) e.getSource());
                break;
            case "get":
                var logsType = (Logs.LOGS_TYPE) e.getSource();
                logs.getExercise(logsType);
                break;
            case "update":
                logs.updateExercise((Object[]) e.getSource());
                break;
            case "delete":
                logs.deleteExercise((Object[]) e.getSource());
                break;
            case "create_s":
                break;
            case "get_s":
                break;
            case "update_s":
                break;
            case "delete_s":
                break;
        }
    }
}

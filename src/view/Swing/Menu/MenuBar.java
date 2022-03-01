package view.Swing.Menu;

import controller.FileChangeListener;
import model.Logs;
import view.Swing.Dialog.Exercise.ExerciseAddDialog;
import view.Swing.Dialog.Food.Basic.FoodBasicAddDialog;
import view.Swing.Dialog.Food.Recipe.FoodRecipeAddDialog;
import view.Swing.Dialog.LogEntry.Add.LogEntryAddCaloriesDialog;
import view.Swing.Dialog.LogEntry.Add.LogEntryAddExerciseDialog;
import view.Swing.Dialog.LogEntry.Add.LogEntryAddFoodDialog;
import view.Swing.Dialog.LogEntry.Add.LogEntryAddWeightDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuBar extends javax.swing.JMenuBar {
    private Logs logs; // database object

    private FoodBasicAddDialog foodBasicAddDialog;
    private FoodRecipeAddDialog foodRecipeAddDialog;
    private ExerciseAddDialog exerciseAddDialog;
    private LogEntryAddCaloriesDialog logEntryAddCaloriesDialog;
    private LogEntryAddExerciseDialog logEntryAddExerciseDialog;
    private LogEntryAddFoodDialog logEntryAddFoodDialog;
    private LogEntryAddWeightDialog logEntryAddWeightDialog;

    private FileChangeListener fileChangeListener;

    public MenuBar(Logs logs) {
        this.logs = logs;
        fileChangeListener = new FileChangeListener(logs);

        // File menu
        JMenu menuFile = new JMenu("File");
        add(menuFile);

        // JMenuItem menuFileLoad = new JMenuItem("Load");
        // menuFileLoad.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         fileListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "load"));
        //         JOptionPane.showMessageDialog(null,
        //                 "Loaded all csv files.");
        //     }
        // });
        // menuFile.add(menuFileLoad);

        JMenuItem menuFileSave = new JMenuItem("Save");
        menuFileSave.addActionListener(e -> {
            fileChangeListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "save"));
        });
        menuFile.add(menuFileSave);

        JMenuItem menuFileExit = new JMenuItem("Exit");
        menuFileExit.addActionListener(e -> System.exit(0));
        menuFile.add(menuFileExit);

        // Edit menu
        JMenu menuAdd = new JMenu("Add");
        add(menuAdd);

        JMenu menuLogEntry = new JMenu("Log Entry");
        menuAdd.add(menuLogEntry);

        JMenuItem mntmWeight = new JMenuItem("Weight");
        mntmWeight.addActionListener(e -> {
            logEntryAddWeightDialog = new LogEntryAddWeightDialog(logs);
            logEntryAddWeightDialog.setVisible(true);
        });
        menuLogEntry.add(mntmWeight);

        JMenuItem mntmCalories = new JMenuItem("Calories");
        mntmCalories.addActionListener(e -> {
            logEntryAddCaloriesDialog = new LogEntryAddCaloriesDialog(logs);
            logEntryAddCaloriesDialog.setVisible(true);
        });
        menuLogEntry.add(mntmCalories);

        JMenuItem mntmFood = new JMenuItem("Food");
        mntmFood.addActionListener(e -> {
            logEntryAddFoodDialog = new LogEntryAddFoodDialog(logs);
            logEntryAddFoodDialog.setVisible(true);
        });
        menuLogEntry.add(mntmFood);

        JMenuItem mntmExercise = new JMenuItem("Exercise");
        mntmExercise.addActionListener(e -> {
            logEntryAddExerciseDialog = new LogEntryAddExerciseDialog(logs);
            logEntryAddExerciseDialog.setVisible(true);
        });
        menuLogEntry.add(mntmExercise);

        JMenuItem menuLogsBasic = new JMenuItem("Basic");
        menuLogsBasic.addActionListener(e -> {
            foodBasicAddDialog = new FoodBasicAddDialog(logs);
            foodBasicAddDialog.setVisible(true);
        });
        menuAdd.add(menuLogsBasic);

        JMenuItem menuLogsRecipe = new JMenuItem("Recipe");
        menuLogsRecipe.addActionListener(e -> {
            foodRecipeAddDialog = new FoodRecipeAddDialog(logs);
            foodRecipeAddDialog.setVisible(true);
        });
        menuAdd.add(menuLogsRecipe);

        JMenuItem menuLogsExercise = new JMenuItem("Exercise");
        menuLogsExercise.addActionListener(e -> {
            exerciseAddDialog = new ExerciseAddDialog(logs);
            exerciseAddDialog.setVisible(true);
        });
        menuAdd.add(menuLogsExercise);

        // About menu
        JMenu mnAbout = new JMenu("About");
        add(mnAbout);

        JMenuItem menuAboutTeam = new JMenuItem("Team Info.");
        menuAboutTeam.addActionListener(e ->
                JOptionPane.showMessageDialog(null,
                        "Wellness Manager\n" +
                                "Team: ZXQ\n" +
                                "Date: Dec 5, 2021\n" +
                                "Course: SWEN 383\n" +
                                "Professor: Kenn"));
        mnAbout.add(menuAboutTeam);
    }
}

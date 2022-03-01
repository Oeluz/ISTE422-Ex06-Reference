package view.Swing.Panel;


import model.Exercise;
import model.Logs;
import model.SwingModels.ListModels.ExerciseListModel;
import view.Swing.Dialog.Exercise.ExerciseViewDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;
import java.util.Observable;

public class ExercisesPanel extends JPanel implements Observer{

    private Logs logs;

    ExerciseViewDialog exerciseViewDialog;

    JList<String> exercisesList;
    ExerciseListModel listModel;

    public ExercisesPanel(Logs logs) {
        this.logs = logs;

        logs.addObserver(this);

        setLayout(new BorderLayout());

        exercisesList = new JList<>();
        exercisesList.setFont(new Font("Arial", Font.PLAIN, 20));
        exercisesList.setVisibleRowCount(-1);
        exercisesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listModel = new ExerciseListModel(logs);        
        exercisesList.setModel(listModel);

        // listener
        exercisesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                var list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        String selected = (String) list.getModel().getElementAt(index);
                        Exercise exercise = logs.exercises.get(selected);
                        exerciseViewDialog = new ExerciseViewDialog((Exercise) exercise, listModel, logs);
                        exerciseViewDialog.setVisible(true);
                    }
                }
            }
        });

        JScrollPane exercisesScrollPane = new JScrollPane(exercisesList);
        exercisesScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        exercisesScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(exercisesScrollPane);
    }

    @Override
    public void update(Observable o, Object arg) {
        logs = (Logs)o;

        exercisesList.setModel(new ExerciseListModel(logs));
    }
}

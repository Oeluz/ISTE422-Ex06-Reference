package model.SwingModels.ListModels;

import model.Exercise;
import model.Logs;

import javax.swing.*;

public class ExerciseListModel extends AbstractListModel<String> {

    private String[] exerciseList = new String[]{};
    private Logs logs;

    public ExerciseListModel(Logs logs){
        this.logs = logs;
        this.exerciseList = logs.exercises.values().stream().map(Exercise::getName).toArray(String[]::new);
    }

    @Override
    public int getSize() {
        return exerciseList.length;
    }

    @Override
    public String getElementAt(int index) {
        return exerciseList[index];
    }

    // add data into list
    public void addData(String name) {
        exerciseList[exerciseList.length - 1] = name;

        // update list
        fireContentsChanged(this, 0, exerciseList.length - 1);
    }

    // update data in the list
    public void updateData(String oldName, String newName) {
        for (int i = 0; i < exerciseList.length; i++) {
            if (exerciseList[i].equals(oldName)) {
                exerciseList[i] = newName;
                break;
            }
        }
        // update list
        fireContentsChanged(this, 0, exerciseList.length - 1);
    }

    // remove data from list
    public boolean removeData(String name) {

        // find index of name then remove it
        for (int i = 0; i < exerciseList.length; i++) {
            if (exerciseList[i].equals(name)) {
                exerciseList[i] = null;
                break;
            }
        }

        // remove null value
        for (int i = 0; i < exerciseList.length; i++) {
            if (exerciseList[i] == null) {
                if (exerciseList.length - 1 - i >= 0)
                    System.arraycopy(exerciseList, i + 1, exerciseList, i, exerciseList.length - 1 - i);
            }
        }

        // reduce array space
        String[] temp = new String[exerciseList.length - 1];
        System.arraycopy(exerciseList, 0, temp, 0, exerciseList.length - 1);
        exerciseList = temp;


        // update list
        fireContentsChanged(this, 0, exerciseList.length - 1);
        return true;
    }
}

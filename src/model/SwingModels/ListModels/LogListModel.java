package model.SwingModels.ListModels;

import javax.swing.AbstractListModel;

import model.LogEntry;
import model.Logs;

public class LogListModel extends AbstractListModel<String> {

    final String[] values;

    public LogListModel(Logs logs){
        values = logs.logEntries.stream().map(LogEntry::toString).toArray(String[]::new);
    }

    public int getSize() {
        return values.length;
    }

    public String getElementAt(int index) {
        return values[index];
    }
}
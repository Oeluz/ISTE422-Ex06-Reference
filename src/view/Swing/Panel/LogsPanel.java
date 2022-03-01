package view.Swing.Panel;

import javax.swing.*;

import controller.LogChangeListener;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import model.*;
import model.SwingModels.ListModels.LogListModel;

public class LogsPanel extends JPanel implements Observer{

    Logs logs;
    JList<String> logsList;

    public LogsPanel(Logs logs) {
        this.logs = logs;
        logs.addObserver(this);

        setLayout(new BorderLayout());

        logsList = new JList<>(new LogListModel(logs));

        logsList.setFont(new Font("Arial", Font.PLAIN, 20));
        logsList.setVisibleRowCount(-1);
        logsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        logsList.addMouseListener(new LogChangeListener(logs));

        JScrollPane logsScrollPane = new JScrollPane(logsList);
        logsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        logsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(logsScrollPane);
    }

    public void update(Observable obs, Object o) {
        /*
         * If received from other than the logs we're
         * observing, just return.
         */
//        if( obs != logs ) {
//            return ;
//        }

        logs = (Logs) obs;

        logsList.setModel(new LogListModel(logs));
    }
}

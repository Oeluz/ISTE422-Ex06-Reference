package view.Swing.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import model.*;
import model.LogEntry.LOG_TYPE;

public class NetCaloriesPanel extends JPanel implements Observer {

    //store the date and the calories so the panel will able to draw bars based on the values
    private LinkedHashMap<LocalDate, Double> bars = new LinkedHashMap<LocalDate, Double>();
    Logs logs;

    public NetCaloriesPanel(Logs logs) {
        this.logs = logs;
        //allow this panel to be notified if the data is changed
        logs.addObserver(this);
        createBar();
    }

    private void createBar(){
        bars.clear();

        // start with the first object on the logEntry list
        LocalDate date = logs.logEntries.get(0).getDaily();
        double netCal = 0;

        // the for loop will go through the list to add calories of same date
        for (LogEntry entry : logs.logEntries) {
            //ignore the calories intake or weight entry
            if (entry.getLogType() == LOG_TYPE.CALORIES || entry.getLogType() == LOG_TYPE.WEIGHT)
                continue;

            //if the date is same
            if (date.equals(entry.getDaily())) {
                netCal += entry.getTotalCalories();
            } else { //if different, it mean new date, so the program will be done with previous date
                addBar(netCal, date);

                date = entry.getDaily();
                netCal = entry.getTotalCalories();
            }

            //to add the last entry
            if (entry == logs.logEntries.get(logs.logEntries.size() - 1)) {
                addBar(netCal, date);
            }
        }

        //if the bars size is below 7 (a week)
        int i = 1;
        while (bars.size() < 7) {
            bars.put(LocalDate.of(i, i, i), 0.0);
            i++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        logs = (Logs)o;
        createBar();
    }

    public void addBar(double value, LocalDate date) {
        bars.put(date, value);

        //remove the first item if there is a new item
        for (LocalDate item : bars.keySet()) {
            if (bars.size() > 7)
                bars.remove(item);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        double max = bars.values().stream().max((value1, value2) -> value1 > value2 ? 1 : -1).get();
        
        //if the list only have negative net calories or zero
        if(max == 0){
            max = bars.values().stream().min((value1, value2) -> value1 > value2 ? 1 : -1).get();
        }

        //if the max is negative, change it to positive for the width size
        if(max < 0){
            max *= -1;
        }

        int height = (getHeight() / bars.size()) - 10;
        int y = 10;

        //sort the dates based on the timeline then put them into ArrayList due to casting problem
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        Set<LocalDate> set = bars.keySet();
        set.stream().sorted().forEach(date -> dates.add(date));

        //make a bar for each date out of the 7 dates
        for (LocalDate date : dates) {
            double value = bars.get(date);
            int width = 0;
            //calculate the width based on the highest calories
            if(value < 0){
                width = (int) ((getWidth() - 5) * ((value * -1) / (max - 20)));
            }
            else{
                width = (int) ((getWidth() - 5) * (value / (max - 20)));
            }

            //display the date and amount of calories
            g.setColor(Color.black);
            g.drawString(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)), 0, y + (height/6));
            g.drawString(String.format("cal: %.2f", value), 0, y + (height / 3));

            //if negative, RED
            //if positive, CYAN
            if (value < 0)
                g.setColor(Color.RED);
            else
                g.setColor(Color.cyan);
            
            //create the bar
            g.fillRect(70, y, width, height);

            //move to next bar
            y += (height + 10);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 10 + 2, 50);
    }
}

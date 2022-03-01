package view.Swing.Panel;

import model.Basic;
import model.Food;
import model.Logs;
import model.Recipe;
import model.SwingModels.ListModels.FoodListModel;
import view.Swing.Dialog.Food.Basic.FoodBasicViewDialog;
import view.Swing.Dialog.Food.Recipe.FoodRecipeViewDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class FoodPanel extends JPanel implements Observer {

    FoodBasicViewDialog foodBasicViewDialog;
    FoodRecipeViewDialog foodRecipeViewDialog;

    JList<String> foodList;
    FoodListModel foodListModel;

    private Logs logs;

    public FoodPanel(Logs logs) {
        setLayout(new BorderLayout());
        this.logs = logs;

        logs.addObserver(this);

        foodList = new JList<>();
        foodList.setFont(new Font("Arial", Font.PLAIN, 20));
        foodList.setVisibleRowCount(-1);
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        foodListModel = new FoodListModel(logs);
        foodList.setModel(foodListModel);

        // listener
        foodList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                var list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    if (index >= 0) {
                        String selected = (String) list.getModel().getElementAt(index);
                        Food food = logs.foods.get(selected);
                        if (food instanceof Basic) {
                            foodBasicViewDialog = new FoodBasicViewDialog((Basic) food, foodListModel, logs);
                            foodBasicViewDialog.setVisible(true);
                        } else {
                            foodRecipeViewDialog = new FoodRecipeViewDialog((Recipe) food, foodListModel, logs);
                            foodRecipeViewDialog.setVisible(true);
                        }
                    }
                }
            }
        });

        JScrollPane foodScrollPane = new JScrollPane(foodList);
        foodScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        foodScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(foodScrollPane);
    }

    @Override
    public void update(Observable o, Object arg) {
        logs = (Logs) o;

        foodList.setModel(new FoodListModel(logs));
    }
}

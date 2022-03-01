package model.SwingModels.ListModels;

import model.Food;
import model.Logs;
import model.Recipe;

import javax.swing.*;

public class FoodListModel extends AbstractListModel<String> {

    private String[] foodList = new String[]{};
    private Logs logs;

    public FoodListModel(Logs logs) {
        this.logs = logs;
        this.foodList = logs.foods.values().stream().map(Food::getName).toArray(String[]::new);
    }

    @Override
    public int getSize() {
        return foodList.length;
    }

    @Override
    public String getElementAt(int index) {
        return foodList[index];
    }

    // remove data from list
    public boolean removeData(String name) {
        // find name in recipe's food if it exists don't remove it
        for (Food food : logs.foods.values()) {
            if (food instanceof Recipe) {
                Recipe recipe = (Recipe) food;
                if (recipe.getFoods().stream().anyMatch(f -> f.getName().equals(name))) {
                    return false;
                }
            }
        }

        // find index of name then remove it
        for (int i = 0; i < foodList.length; i++) {
            if (foodList[i].equals(name)) {
                foodList[i] = null;
                break;
            }
        }

        // remove null value
        for (int i = 0; i < foodList.length; i++) {
            if (foodList[i] == null) {
                if (foodList.length - 1 - i >= 0)
                    System.arraycopy(foodList, i + 1, foodList, i, foodList.length - 1 - i);
            }
        }

        // reduce array space
        String[] temp = new String[foodList.length - 1];
        System.arraycopy(foodList, 0, temp, 0, foodList.length - 1);
        foodList = temp;


        // update list
        fireContentsChanged(this, 0, foodList.length - 1);
        return true;
    }
}

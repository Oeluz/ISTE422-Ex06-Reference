package model.SwingModels.ListModels;

import model.Food;
import model.Logs;
import model.Recipe;

import javax.swing.*;
import java.util.Arrays;

public class RecipeEditListModel extends AbstractListModel<String> {
    private String[] recipes;

    public RecipeEditListModel(Logs logs, String recipeName) {
        for (Food food : logs.foods.values()) {
            if (food instanceof Recipe) {
                if (food.getName().equals(recipeName)) {
                    recipes = ((Recipe) food).getFoods().stream().map(Food::getName).toArray(String[]::new);
                    break;
                }
            }
        }
    }

    @Override
    public int getSize() {
        return recipes.length;
    }

    @Override
    public String getElementAt(int index) {
        return recipes[index];
    }

    public void addData(String name) {
        // add to the end of the array
        String[] temp = Arrays.copyOf(recipes, recipes.length + 1);
        temp[recipes.length] = name;
        recipes = temp;

        fireContentsChanged(this, 0, recipes.length - 1);
    }

    public void removeData(String name) {
        // find index of name then remove it
        for (int i = 0; i < recipes.length; i++) {
            if (recipes[i].equals(name)) {
                recipes[i] = null;
                break;
            }
        }

        // remove null value
        for (int i = 0; i < recipes.length; i++) {
            if (recipes[i] == null) {
                if (recipes.length - 1 - i >= 0)
                    System.arraycopy(recipes, i + 1, recipes, i, recipes.length - 1 - i);
            }
        }

        // reduce array space
        String[] temp = new String[recipes.length - 1];
        System.arraycopy(recipes, 0, temp, 0, recipes.length - 1);
        recipes = temp;
    }

    public String[] getData() {
        return recipes;
    }
}

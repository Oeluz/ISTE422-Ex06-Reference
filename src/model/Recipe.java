package model;

import java.util.ArrayList;

/**
 * This class represents a recipe.
 *
 * @author Team ZXQ
 * @see Basic
 * @see Food
 */
public class Recipe implements Food {
    private String name; // name of the recipe

    private ArrayList<Food> foods = new ArrayList<Food>(); // list of foods in the recipe

    /**
     * Constructor with no parameters
     */
    public Recipe() {
    }

    /**
     * Constructor with recipe's name and foods
     *
     * @param name  Recipe's name
     * @param foods Recipe has food list
     */
    public Recipe(String name, ArrayList<Food> foods) {
        setName(name);
        setFoods(foods); // set food list
    }

    @Override
    public String getLiteralLetter() {
        return "r";
    }

    /**
     * @return name of the recipe
     */
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return get total calories from list of foods in the recipe
     */
    @Override
    public double getCalories() {
        return foods.stream().mapToDouble(Food::getCalories).sum();
    }

    /**
     * @return get total carbs from list of foods in the recipe
     */
    @Override
    public double getCarbs() {
        return foods.stream().mapToDouble(Food::getCarbs).sum();
    }

    /**
     * @return get proteins from list of foods in the recipe
     */
    @Override
    public double getProteins() {
        return foods.stream().mapToDouble(Food::getProteins).sum();
    }

    /**
     * @return get fats from list of foods in the recipe
     */
    @Override
    public double getFats() {
        return foods.stream().mapToDouble(Food::getFats).sum();
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public String toCSVString() {
        StringBuilder sb = new StringBuilder("r," + getName() + ",");

        // add foods to the string
        for (Food food : foods) {
            sb.append(food.getName()).append(",0,");
        }

        // trim trailing comma
        sb.deleteCharAt(sb.length() - 1);

        // return string
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" Recipe: ").append(getName()).append("\n");
        sb.append("\t\tCalories: ").append(getCalories()).append("\n");
        sb.append("\t\tFoods: ").append("\n");

        for (Food food : foods) {
            sb.append("\t\t\t").append(food.getName()).append("\n");
        }

        return sb.toString();
    }
}
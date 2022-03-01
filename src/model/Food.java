package model;

/**
 * This class represents a food item.
 *
 * @author Team ZXQ
 * @see Basic
 * @see Recipe
 */
public interface Food {
    String getLiteralLetter();

    String getName(); // returns the name of the food

    double getCalories(); // returns the number of calories in the food

    double getCarbs(); // returns the number of carbs in the food

    double getProteins(); // returns the number of proteins in the food

    double getFats(); // returns the number of fats in the food

    String toCSVString();
}
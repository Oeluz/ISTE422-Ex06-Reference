package model;

/**
 * The Basic class is the superclass of all other classes in the model package.
 *
 * @author Team ZXQ
 * @see Food
 * @see Recipe
 */
public class Basic implements Food {

    private String name; // name of the food
    private double calories; // calories of the food
    private double carbs; // carbs of the food
    private double proteins; // proteins of the food
    private double fats; // fats of the food
    private double servings; // servings of the food

    /**
     * Constructor with no parameters
     */
    public Basic() {
        setName("");
        setCalories(0);
        setCarbs(0);
        setProteins(0);
        setFats(0);
    }

    /**
     * Constructor with name
     */
    public Basic(String name) {
        setName(name);
    }

    /**
     * Constructor with name & servings
     */
    public Basic(String name, double servings) {
        setName(name);
        setServings(servings);
    }

    /**
     * Constructor with parameters
     *
     * @param calories Basic food's calories
     * @param carbs    Basic food's carbs
     * @param proteins Basic food's proteins
     * @param fats     Basic food's fats
     */
    public Basic(String name, double calories, double carbs, double proteins, double fats) {
        setName(name); // set name of the food
        setCalories(calories); // set calories of the food
        setCarbs(carbs); // set carbs of the food
        setProteins(proteins); // set proteins of the food
        setFats(fats); // set fats of the food
    }

    @Override
    public String getLiteralLetter() {
        return "b";
    }

    /**
     * @return name of the food
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return calories of the food
     */
    @Override
    public double getCalories() {
        return calories;
    }

    /**
     * @return carbs of the food
     */
    @Override
    public double getCarbs() {
        return carbs;
    }

    /**
     * @return proteins of the food
     */
    @Override
    public double getProteins() {
        return proteins;
    }

    /**
     * @return fats of the food
     */
    @Override
    public double getFats() {
        return fats;
    }

    /**
     * @return servings of the food
     */
    public double getServings() {
        return servings;
    }

    /**
     * @param name name of the food
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param calories calories of the food
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * @param carbs carbs of the food
     */
    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    /**
     * @param proteins proteins of the food
     */
    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    /**
     * @param fats fats of the food
     */
    public void setFats(double fats) {
        this.fats = fats;
    }

    /**
     * @param servings servings of the food
     */
    public void setServings(double servings) {
        this.servings = servings;
    }

    public String toCSVString() {
        return String.format("%s,%s,%.1f,%.1f,%.1f,%.1f", getLiteralLetter(), getName(), getCalories(), getCarbs(), getProteins(), getFats());
    }

    /**
     * @return string representation of the food
     */
    @Override
    public String toString() {
        return String.format("%s: %.1f calories, %.1f carbs, %.1f proteins, %.1f fats", getName(), getCalories(), getCarbs(), getProteins(), getFats());
    }
}
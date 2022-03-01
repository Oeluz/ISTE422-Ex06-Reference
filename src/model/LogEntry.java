package model;

import java.time.LocalDate;

/**
 * This class is used to store the log information.
 *
 * @author Team ZXQ
 */
public class LogEntry {

    // Enum for log types
    public enum LOG_TYPE {
        WEIGHT, FOOD, CALORIES, EXERCISE
    }

    // Date of the log
    private LocalDate daily;
    // Type of log
    private LOG_TYPE log_type;
    // Value of the log base on weight or calories
    private double value;
    // Food object if the log is food log
    private Food food;
    // Number of servings is consumed
    private double servingConsumed;
    // Exercise object if the log is exercise log
    private Exercise exercise;
    // calculate exercise calories
    private double exerciseCalories;

    /**
     * Accept Log_Type only Exercise
     *
     * @return literal letter of the log type
     */
    public String getLiteralLetter() {
        switch (getLogType()) {
            case WEIGHT:
                return "w";
            case CALORIES:
                return "c";
            case FOOD:
                return "f";
            case EXERCISE:
                return "e";
        }
        return "";
    }

    /**
     * @return the daily
     */
    public LocalDate getDaily() {
        return daily;
    }

    /**
     * @param daily the daily to set
     */
    public void setDaily(LocalDate daily) {
        this.daily = daily;
    }

    /**
     * @return the log_type
     */
    public LOG_TYPE getLogType() {
        return log_type;
    }

    /**
     * @param logType the log_type to set
     */
    public void setLogType(LOG_TYPE logType) {
        this.log_type = logType;
    }

    /**
     * @return the value
     */
    public double getValue() {
        switch (log_type) {
            case WEIGHT:
            case CALORIES:
                return value;
            case FOOD:
                return food.getCalories();
            case EXERCISE:
                return exercise.getCalories() * -1;
            default:
                return 0;
        }
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return the food
     */
    public Food getFood() {
        return food;
    }

    /**
     * @param food the food to set
     */
    public void setFood(Food food) {
        this.food = food;
    }

    /**
     * @return the servingConsumed
     */
    public double getServingConsumed() {
        return servingConsumed;
    }

    /**
     * @param servingConsumed the servingConsumed to set
     */
    public void setServingConsumed(double servingConsumed) {
        this.servingConsumed = servingConsumed;
    }

    /**
     * @return the exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * @param exercise the exercise to set
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * from calculate calories method in Logs Class to get exercise calories
     *
     * @return the exerciseCalories
     */
    public double getExerciseCalories() {
        return exerciseCalories * -1;
    }

    /**
     * from calculate calories method in Logs Class to set exercise calories
     */
    public void setExerciseCalories(double exerciseCalories) {
        this.exerciseCalories = exerciseCalories;
    }

    public double getTotalCalories() {
        switch (getLogType()) {
            case FOOD:
                return getFood().getCalories() * getServingConsumed();
            case EXERCISE:
                return getExerciseCalories();
            default:
                return 0;
        }
    }


    /**
     * Constructor with no parameters
     */
    public LogEntry() {
    }

    /**
     * Constructor with parameters
     *
     * @param daily    the date of the log
     * @param log_type the type of log
     * @param value    the value of the log base on weight or calories
     * @param food     the food object if the log is food log
     */
    public LogEntry(LocalDate daily, LOG_TYPE log_type, double value, Food food, double servingConsumed, Exercise exercise) {
        setDaily(daily); // Set the date
        setLogType(log_type); // Set the log type
        // Set the value based on the log type
        switch (log_type) {
            case WEIGHT:
                if (value != 0) setValue(value);
                else setValue(150);
                break;
            case CALORIES:
                if (value != 0) setValue(value);
                else setValue(2000);
                break;
            case FOOD:
                setFood(food);
                setServingConsumed(servingConsumed);
                break;
            case EXERCISE:
                setExercise(exercise);
                break;
        }
    }

    /**
     * Accept Log_Type are WEIGHT or CALORIES
     *
     * @param daily    the date of the log
     * @param log_type the type of log
     * @param value    the value of the log base on weight or calories
     */
    public LogEntry(LocalDate daily, LOG_TYPE log_type, double value) {
        this(daily, log_type, value, null, 0, null);
    }

    /**
     * Accept Log_Type only Food
     *
     * @param daily           the date of the log
     * @param log_type        the type of log
     * @param food            the food object if the log is food log
     * @param servingConsumed the double of servingConsumed
     */
    public LogEntry(LocalDate daily, LOG_TYPE log_type, Food food, double servingConsumed) {
        this(daily, log_type, 0, food, servingConsumed, null);
    }

    /**
     * Accept Log_Type only Exercise
     *
     * @param daily    the date of the log
     * @param log_type the type of log
     * @param exercise the exercise object if the log is an exercise log
     */
    public LogEntry(LocalDate daily, LOG_TYPE log_type, Exercise exercise) {
        this(daily, log_type, 0, null, 0, exercise);
    }

    public String toCSVString() {
        String str = "";

        // Get the date
        String year = String.valueOf(daily.getYear());
        String month = String.valueOf(daily.getMonthValue());
        String day = String.valueOf(daily.getDayOfMonth());

        // Get the log type and print csv formula
        switch (log_type) {
            case WEIGHT:
            case CALORIES:
                str = String.format("%s,%s,%s,%s,%.1f", year, month, day, getLiteralLetter(), getValue());
                break;
            case FOOD:
                str = String.format("%s,%s,%s,%s,%s,%.1f", year, month, day, getLiteralLetter(), getFood().getName(), getServingConsumed());
                break;
            case EXERCISE:
                str = String.format("%s,%s,%s,%s,%s,%.1f", year, month, day, getLiteralLetter(), getExercise().getName(), getExercise().getMinutes());
                break;
        }

        return str;
    }

    /**
     * @return the daily
     */
    @Override
    public String toString() {

        switch (getLogType()) {
            case WEIGHT:
                return String.format("%s - Weight: %.1f", getDaily(), getValue());
            case CALORIES:
                return String.format("%s - Calories: %.1f", getDaily(), getValue());
            case FOOD:
                return String.format("%s - Food: %s, Serving Consumed: %.1f", getDaily(), getFood().getName(), getServingConsumed());
            case EXERCISE:
                return String.format("%s - Exercise: %s, Calories: %.1f", getDaily(), getExercise().getName(), getExerciseCalories());
            default:
                return "";
        }
    }
}
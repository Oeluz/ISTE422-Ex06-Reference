package model;

public class Exercise {

    public enum Exercise_TYPE {
        MINUTES, CALORIES
    }

    private String name;
    private double minutes;
    private double calories;

    public String getLiteralLetter() {
        return "e";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public Exercise() {
        setName("Unknown");
        setCalories(0);
    }

    public Exercise(String name, Exercise_TYPE type, double value) {
        setName(name);
        switch (type) {
            case MINUTES:
                setMinutes(value);
                break;
            case CALORIES:
                setCalories(value);
                break;
        }
    }

    public String toCSVString() {
        return String.format("%s,%s,%.1f", getLiteralLetter(), getName(), getCalories());
    }

    @Override
    public String toString() {
        return String.format("%s: %.1f calories", getName(), getCalories());
    }

}

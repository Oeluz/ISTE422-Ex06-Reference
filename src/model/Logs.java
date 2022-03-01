package model;

import model.Exercise.Exercise_TYPE;
import model.LogEntry.LOG_TYPE;
import model.SwingModels.ListModels.FoodListModel;
import model.SwingModels.ListModels.LogListModel;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import javax.print.DocFlavor.STRING;

/**
 * Database class for storing and retrieving data.
 *
 * @author Team ZXQ
 */
public class Logs extends Observable {
    // constants
    private static final String LOG_CSV_FILENAME = "log.csv";
    private static final String FOODS_CSV_FILENAME = "foods.csv";
    private static final String EXERCISE_CSV_FILENAME = "exercise.csv";

    // instance variables
    public HashMap<String, Food> foods = new HashMap<>();
    public HashMap<String, Exercise> exercises = new HashMap<>();
    public ArrayList<LogEntry> logEntries = new ArrayList<>();

    // self-explanatory
    private static double userWeight = 0; // User's weight

    static void setUserWeight(double weight) {
        userWeight = weight;
    }

    static double getUserWeight() {
        return userWeight;
    }

    /**
     * Make enum for Food type.
     */
    public enum LOGS_TYPE {
        BASIC, RECIPE, DAILY_LOG, EXERCISE
    }

    /**
     * Program reads log and food csv files and parses/stores into database
     */
    public void loadCSVToData() {
        parseFoodsFromCSV(); // load foods.csv
        parseExerciseFromCSV(); // load exercise.csv
        parseDailyLogFromCSV(); // load log.csv

        // notify observers
        setChanged(); // set changed
        notifyObservers(); // notify observers
    }

    /**
     * Programs read 'log.csv' and parse into an object of LogEntry and adds to logEntries
     */
    public void parseDailyLogFromCSV() {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(LOG_CSV_FILENAME))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                LogEntry log = new LogEntry();

                // Date is deprecated and cannot take m/d/yyyy as a parameter for some reason. Using LocalDate class as a temporarily
                LocalDate date = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));

                // LogEntry needs an attribute called servingConsumed when an item's LOG_TYPE is food
                switch (data[3]) {
                    case "f":
                        log = new LogEntry(date, LogEntry.LOG_TYPE.FOOD, foods.get(data[4]), Double.parseDouble(data[5]));
                        break;
                    case "c":
                        log = new LogEntry(date, LogEntry.LOG_TYPE.CALORIES, Double.parseDouble(data[4]));
                        break;
                    case "w":
                        log = new LogEntry(date, LogEntry.LOG_TYPE.WEIGHT, Double.parseDouble(data[4]));
                        setUserWeight(log.getValue());
                        break;
                    case "e":
                        // log = new LogEntry(date, LogEntry.LOG_TYPE.EXERCISE, exercises.get(data[4]));
                        log = new LogEntry(date, LogEntry.LOG_TYPE.EXERCISE, new Exercise(data[4], Exercise.Exercise_TYPE.MINUTES, Double.parseDouble(data[5])));
                        log.setExerciseCalories(calcExerciseCalories(log.getExercise(), exercises));
                        break;
                }

                logEntries.add(log);
            }
        } catch (IOException ioe) {
            System.out.println("Error reading log.csv");
            ioe.printStackTrace();
        }

    }

    /**
     * Programs read 'foods.csv' and parse into an object of Food and adds to foods list
     */
    public void parseFoodsFromCSV() {
        String line;

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(FOODS_CSV_FILENAME))) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    switch (data[0]) {
                        case "b":
                            Basic basic = new Basic(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                            foods.put(basic.getName(), basic);
                            break;
                        case "r":
                            ArrayList<Food> food = new ArrayList<>();
                            for (int i = 2; i < data.length; i += 2) {
                                // -need to add Basic constructor to take name and servings as a parameter
                                food.add(foods.get(data[i]));
                            }
                            Recipe recipe = new Recipe(data[1], food);
                            foods.put(recipe.getName(), recipe);
                            break;
                    }
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading foods.csv");
            ioe.printStackTrace();
        }
    }

    /**
     * Programs read 'exercise.csv' and parse into an object of Exercise and adds to exercise list
     */
    public void parseExerciseFromCSV() {
        String line;

        try {
            try (BufferedReader br = new BufferedReader(new FileReader(EXERCISE_CSV_FILENAME))) {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    Exercise exercise = new Exercise(data[1], Exercise.Exercise_TYPE.CALORIES, Double.parseDouble(data[2]));
                    exercises.put(exercise.getName(), exercise);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Error reading exercise.csv");
            ioe.printStackTrace();
        }
    }

    public double calcExerciseCalories(Exercise exercise, HashMap<String, Exercise> exercises) {
        final double POUNDS = 100.0;
        final double MINUTES_FOR_ONE_HOUR = 60.0;

        String exerciseName = exercise.getName();
        double weight = Logs.getUserWeight();
        double minutes = exercise.getMinutes();

        // find the exercise name in the list of exercises and get the calories
        double calories = 0;
        for (Exercise e : exercises.values()) {
            if (e.getName().equals(exerciseName)) {
                calories = e.getCalories();
            }
        }

        return calories * (weight / POUNDS) * (minutes / MINUTES_FOR_ONE_HOUR);
    }

    /**
     * Program saves the csv files after the files are updated
     */
    public void saveCSVToData() {
        parseFoodsToCSV();
        parseExerciseToCSV();
        parseDailyLogToCSV();
        System.out.println("All successfully written to the CSV file。");
    }

    private void parseExerciseToCSV() {
        // write exercise.csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("exercise.csv"))) {
            for (Exercise exercise : exercises.values()) {
                bw.write(exercise.toCSVString());
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error writing to exercise.csv");
            ioe.printStackTrace();
        }
    }

    private void parseFoodsToCSV() {
        // write foods.csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("foods.csv"))) {
            for (Food food : foods.values()) {
                bw.write(food.toCSVString());
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error writing to foods.csv");
            ioe.printStackTrace();
        }
    }

    private void parseDailyLogToCSV() {
        // write log.csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.csv"))) {
            for (LogEntry log : logEntries) {
                bw.write(log.toCSVString());
                bw.newLine();
            }
        } catch (IOException ioe) {
            System.out.println("Error writing to log.csv");
            ioe.printStackTrace();
        }
    }

    // CRUD for Food classes
    public void getFood(LOGS_TYPE type) {
        var index = 1;
        switch (type) {
            case BASIC:
                System.out.println("\nBasic Food: ");
                for (Food food : foods.values()) {
                    if (food instanceof Basic) {
                        System.out.printf("\t%d. %s%n", index, food);
                    }
                    index++;
                }
                break;
            case RECIPE:
                System.out.println("\nRecipes: ");
                for (Food food : foods.values()) {
                    if (food instanceof Recipe) {
                        System.out.printf("\t%d. %s%n", index, food);
                    }
                    index++;
                }
                break;
        }
        notifys();
    }

    public void createFood(Object[] obj) {
        var type = (LOGS_TYPE) obj[0];
        switch (type) {
            case BASIC:
                Food basic = (Basic) obj[1];
                foods.put(basic.getName(), basic);
                break;
            case RECIPE:
                String recipeName = (String) obj[1];
                String[] recipeFoods = (String[]) obj[2];
                ArrayList<Food> recipeFoodList = new ArrayList<>();
                ArrayList<Food> tempFood = new ArrayList<>(foods.values());

                if (obj[3] != null) {
                    for (String foodName : recipeFoods) {
                        for (Food food : tempFood) {
                            if (food.getName().equals(foodName)) {
                                recipeFoodList.add(food);
                            }
                        }
                    }
                } else {
                    for (String indexStr : recipeFoods) {
                        int index = Integer.parseInt(indexStr);
                        recipeFoodList.add(tempFood.get(index - 1));
                    }
                }

                Food recipe = new Recipe(recipeName, recipeFoodList);
                foods.put(recipe.getName(), recipe);
                break;
        }
        notifys();
    }

    public void updateFood(Object[] obj) {
        LOGS_TYPE type = (LOGS_TYPE) obj[0];

        if (obj[1] instanceof Integer) {
            int index = (int) obj[1];
            ArrayList<Food> tempFood = new ArrayList<>(foods.values());
            switch (type) {
                case BASIC:
                    Food basic = (Basic) obj[2];
                    foods.put(tempFood.get(index - 1).getName(), basic);
                    break;
                case RECIPE:
                    String recipeName = (String) obj[2];
                    String[] recipeFoods = (String[]) obj[3];
                    ArrayList<Food> recipeFoodList = new ArrayList<>();
                    ArrayList<Food> tempRecipe = new ArrayList<>(foods.values());
                    for (String indexStr : recipeFoods) {
                        int i = Integer.parseInt(indexStr);
                        recipeFoodList.add(tempRecipe.get(i - 1));
                    }

                    Food recipe = new Recipe(recipeName, recipeFoodList);
                    foods.replace(tempFood.get(index).getName(), recipe);
            }
        } else {
            String oldName = (String) obj[1];
            switch (type) {
                case BASIC:
                    Food basic = (Basic) obj[2];

                    if (oldName.equals(basic.getName())) {
                        foods.replace(oldName, basic);
                    } else {
                        foods.remove(oldName);
                        foods.put(basic.getName(), basic);
                    }
                    break;
                case RECIPE:
                    String recipeName = (String) obj[2];
                    String[] recipeFoods = (String[]) obj[3];
                    ArrayList<Food> recipeFoodList = new ArrayList<>();
                    for (String str : recipeFoods) {
                        recipeFoodList.add(foods.get(str));
                    }

                    Food recipe = new Recipe(recipeName, recipeFoodList);

                    if (oldName.equals(recipeName)) {
                        foods.replace(oldName, recipe);
                    } else {
                        foods.remove(oldName);
                        foods.put(recipe.getName(), recipe);
                    }
                    break;
            }
        }

        notifys();
    }

    public void deleteFood(Object[] obj) {
        var type = (LOGS_TYPE) obj[0];
        if (obj[1] instanceof Integer) {
            var index = (int) obj[1];
            switch (type) {
                case BASIC:
                case RECIPE:
                    ArrayList<Food> tempFood = new ArrayList<>(foods.values());
                    foods.remove(tempFood.get(index).getName());
                    break;
            }
        } else {
            var name = (String) obj[1];
            switch (type) {
                case BASIC:
                case RECIPE:
                    foods.remove(name);
                    break;
            }
        }


        notifys();
    }

    // CRUD for Daily Log class
    public void getDailyLog(LOGS_TYPE type) {
        if (type == LOGS_TYPE.DAILY_LOG) {
            System.out.println("\nDaily Logs: ");
            for (var i = 1; i <= logEntries.size(); i++) {
                System.out.printf("\t%d. %s%n", i, logEntries.get(i - 1));
            }
        } else {
            System.out.println("Error something for getDailyLog.");
        }
        notifys();
    }

    public void createDailyLog(Object[] obj) {
        logEntries.add((LogEntry) obj[2]);
        notifys();
    }

    public void updateDailyLog(Object[] obj) {
        logEntries.set((int) obj[0], (LogEntry) obj[2]);
        notifys();
    }

    public void deleteDailyLog(Object[] obj) {
        LOGS_TYPE type = (LOGS_TYPE) obj[0];
        if (type == LOGS_TYPE.DAILY_LOG) {
            int index = (int) obj[1];
            logEntries.remove(index);
        }
        notifys();
    }

    // CRUD for Exercise class
    public void getExercise(LOGS_TYPE type) {
        if (type == LOGS_TYPE.EXERCISE) {
            System.out.println("\nExercises: ");
            ArrayList<Exercise> tempExercise = new ArrayList<>(exercises.values());
            for (int i = 0; i < exercises.size(); i++) {
                System.out.printf("\t%d. %s%n", i + 1, exercises.get(tempExercise.get(i).getName()));
            }
        } else {
            System.out.println("Error something for getExercise.");
        }
        notifys();
    }

    public void createExercise(Object[] obj) {
        LOGS_TYPE type = (LOGS_TYPE) obj[0];
        if (type == LOGS_TYPE.EXERCISE) {
            Exercise exercise = (Exercise) obj[1];
            exercises.put(exercise.getName(), exercise);
        }
        notifys();
    }

    public void updateExercise(Object[] obj) {
        LOGS_TYPE type = (LOGS_TYPE) obj[0];
        if (type == LOGS_TYPE.EXERCISE) {
            if (obj[1] instanceof Integer) {
                int index = (int) obj[1];
                ArrayList<Exercise> tempExercise = new ArrayList<>(exercises.values());
                Exercise exercise = (Exercise) obj[2];
                exercises.replace(tempExercise.get(index - 1).getName(), exercise);
            }
            else{
                Exercise newExercise = (Exercise)obj[2];
                exercises.remove((String)obj[1]);
                exercises.put(newExercise.getName(), newExercise);
            }
        }
        notifys();
    }

    public void deleteExercise(Object[] obj) {
        LOGS_TYPE type = (LOGS_TYPE) obj[0];
        if (type == LOGS_TYPE.EXERCISE) {
            if (obj[1] instanceof Integer) {
                int index = (int) obj[1];
                ArrayList<Exercise> tempExercise = new ArrayList<>(exercises.values());
                exercises.remove(tempExercise.get(index - 1).getName());
            }
            else{
                exercises.remove((String)obj[1]);
            }
        }
        notifys();
    }

    public void notifys() {
        setChanged();
        notifyObservers(this);
    }
}
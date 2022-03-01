package view.CLI;

import controller.ExerciseChangeListener;
import controller.FileChangeListener;
import controller.FoodChangeListener;
import controller.LogChangeListener;
import model.*;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * This class is the CommandLine class. <br/>
 * It is an observer of the database. <br/>
 * It is used to display the command line of the program. <br/>
 * It is used to display the current state of the database. <br/>
 *
 * @author Team ZXQ
 * @see Logs
 */
public class CommandLine implements Observer {

    private final Logs logs = new Logs(); // database object
    private final Scanner scanner = new Scanner(System.in); // scanner object
    private final FileChangeListener fileListener = new FileChangeListener(logs); // fileChangerListener object
    private final LogChangeListener logListener = new LogChangeListener(logs); // logChangerListener object
    private final FoodChangeListener foodListener = new FoodChangeListener(logs); // logChangerListener object
    private final ExerciseChangeListener exerciseListener = new ExerciseChangeListener(logs); // logChangerListener
    // object

    public enum TypeMenu {
        FOOD, RECIPE, LOG, EXERCISE
    }

    /**
     * @param observable the observable object.
     * @param arg        the argument passed to the notifyObservers method.
     */
    @Override
    public void update(Observable observable, Object arg) {
        if (observable != logs) {
            System.out.println("\nSomething went wrong");
            System.exit(0);
        }
    }

    public void run() {
        loadAllCSVs();
        mainMenu();
        saveAllCSVs();
    }

    private String getMenuSentence(String typeString) {
        return "\n" + "What would you like to do with " + typeString + " list?\n" + "1. Display the list of "
                + typeString + "\n" + "2. Add a " + typeString + " to the list\n" + "3. Edit a " + typeString + "\n"
                + "4. Remove " + typeString + " from the list\n" + "5. Cancel";
    }

    private void mainMenu() {
        System.out.println("Wellness Manager");

        while (true) {
            System.out.println("\n" + "Which list would you like to manage or view?\n" + "1. Basic Foods\n"
                    + "2. Recipe Foods\n" + "3. Daily Logs\n" + "4. Exercises\n" + "5. Exit the program");
            System.out.print("Option: ");
            String input = scanner.nextLine();

            if (input.matches("[1-5]")) {
                switch (input) {
                    case "1":
                        subMenu(TypeMenu.FOOD, getMenuSentence("food"));
                        break;
                    case "2":
                        subMenu(TypeMenu.RECIPE, getMenuSentence("recipe"));
                        break;
                    case "3":
                        subMenu(TypeMenu.LOG, getMenuSentence("log"));
                        break;
                    case "4":
                        subMenu(TypeMenu.EXERCISE, getMenuSentence("exercise"));
                        break;
                    case "5":
                        System.out.println("\nGoodbye!");
                        return;
                }
            } else {
                System.out.println("You have entered invalid input, please again");
            }
        }
    }

    private void subMenu(TypeMenu type, String menu) {
        boolean IsContinue = true;
        while (IsContinue) {
            System.out.println(menu);
            System.out.print("Option: ");
            String input = scanner.nextLine();

            if (input.matches("[1-5]")) {
                switch (input) {
                    case "1":
                        switch (type) {
                            case FOOD:
                                getAllBasicFood();
                                break;
                            case RECIPE:
                                getAllRecipes();
                                break;
                            case LOG:
                                getAllDailyLog();
                                break;
                            case EXERCISE:
                                getAllExercises();
                                break;
                        }
                        break;
                    case "2":
                        switch (type) {
                            case FOOD:
                                createBasicFood();
                                break;
                            case RECIPE:
                                createRecipe();
                                break;
                            case LOG:
                                createDailyLog();
                                break;
                            case EXERCISE:
                                createExercise();
                                break;
                        }
                        break;
                    case "3":
                        switch (type) {
                            case FOOD:
                                updateBasicFood();
                                break;
                            case RECIPE:
                                updateRecipe();
                                break;
                            case LOG:
                                updateDailyLog();
                                break;
                            case EXERCISE:
                                updateExercise();
                                break;
                        }
                        break;
                    case "4":
                        switch (type) {
                            case FOOD:
                                deleteBasicFood();
                                break;
                            case RECIPE:
                                deleteRecipe();
                                break;
                            case LOG:
                                deleteDailyLog();
                                break;
                            case EXERCISE:
                                deleteExercise();
                                break;
                        }
                        break;
                    case "5":
                        IsContinue = false;
                        break;
                }
            } else {
                System.out.println("You have entered invalid input, please try again");
            }
        }
    }

    private void getAllBasicFood() {
        foodListener.actionPerformed(new ActionEvent(Logs.LOGS_TYPE.BASIC, ActionEvent.ACTION_PERFORMED, "get"));
    }

    private void getAllRecipes() {
        foodListener.actionPerformed(new ActionEvent(Logs.LOGS_TYPE.RECIPE, ActionEvent.ACTION_PERFORMED, "get"));
    }

    private void getAllExercises() {
        exerciseListener.actionPerformed(new ActionEvent(Logs.LOGS_TYPE.EXERCISE, ActionEvent.ACTION_PERFORMED, "get"));
    }

    private void getAllDailyLog() {
        logListener.actionPerformed(new ActionEvent(Logs.LOGS_TYPE.DAILY_LOG, ActionEvent.ACTION_PERFORMED, "get"));
    }

    private void createBasicFood() {
        System.out.print("\nName: ");
        String name = scanner.nextLine();
        System.out.print("Calories: ");
        String calories = scanner.nextLine();
        System.out.print("Carbs: ");
        String carbs = scanner.nextLine();
        System.out.print("Proteins: ");
        String proteins = scanner.nextLine();
        System.out.print("Fats: ");
        String fats = scanner.nextLine();

        // create new basic food
        Food food = new Basic(name, Integer.parseInt(calories), Double.parseDouble(carbs), Double.parseDouble(proteins),
                Double.parseDouble(fats));

        Object[] obj = new Object[]{
                Logs.LOGS_TYPE.BASIC,
                food
        };

        // add to Logs
        foodListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));

        // print result in console
        System.out.println("\n" + food.getName() + " was inserted.");
    }

    private void createRecipe() {
        System.out.print("\nName: ");
        String name = scanner.nextLine();

        getAllBasicFood();
        System.out.print("\nEnter the foods (separate space): ");
        var recipeFood = scanner.nextLine().split(" ");

        Object[] obj = new Object[]{Logs.LOGS_TYPE.RECIPE, name, recipeFood, null};

        foodListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));
    }

    private void createExercise() {
        System.out.print("\nName: ");
        String name = scanner.nextLine();
        System.out.print("Number of calories burned: ");
        String calories = scanner.nextLine();

        Exercise exercise = new Exercise(name, Exercise.Exercise_TYPE.CALORIES, Double.parseDouble(calories));

        Object[] obj = new Object[]{Logs.LOGS_TYPE.EXERCISE, exercise};

        exerciseListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));
    }

    public Object selectFoodTypeForDailyLog(int updateIndex) {
        while (true) {
            System.out.println("\n" + "Food type\n" + "1. Basic Foods\n" + "2. Recipe Foods");
            System.out.print("Option: ");
            String input = scanner.nextLine();
            if (input.matches("[1-2]")) {
                switch (input) {
                    case "1":
                        return addBasicFoodToDailyLog(updateIndex);
                    case "2":
                        return addRecipeToDailyLog(updateIndex);
                }
            } else {
                System.out.println("You have entered invalid input");
            }
        }
    }

    private Object addBasicFoodToDailyLog(int updateIndex) {
        getAllBasicFood();
        return addObjectToDailyLog(updateIndex, "food");
    }

    private Object addRecipeToDailyLog(int updateIndex) {
        getAllRecipes();
        return addObjectToDailyLog(updateIndex, "food");
    }

    private Object addExerciseToDailyLog(int updateIndex) {
        getAllExercises();
        return addObjectToDailyLog(updateIndex, "exercise");
    }

    private Object addObjectToDailyLog(int updateIndex, String type) {
        try {
            System.out.print("\nEnter the index of the " + type + " you want to add to this log: ");
            var index = Integer.parseInt(scanner.nextLine());
            String value = "";
            LogEntry log;
            if (type.equals("food")) {
                System.out.print("Number of Servings consumed: ");
                value = scanner.nextLine();
                ArrayList<Food> tempFood = new ArrayList<>(logs.foods.values());
                log = new LogEntry(LocalDate.now(), LogEntry.LOG_TYPE.FOOD, tempFood.get(index-1), Double.parseDouble(value));
                return new Object[]{updateIndex, type, log, index,};
            } else{
                System.out.print("Minutes: ");
                value = scanner.nextLine();
                ArrayList<Exercise> tempExercise = new ArrayList<>(logs.exercises.values());
                log = new LogEntry(LocalDate.now(), LogEntry.LOG_TYPE.EXERCISE, tempExercise.get(index-1));
                return new Object[]{updateIndex, type, log, index,};
            }
        } catch (Exception e) {
            System.out.println("You have entered invalid input, please try again");
            e.printStackTrace();
            return null;
        }
    }

    private Object setWeightForDailyLog(int updateIndex) {
        System.out.print("Pounds: ");
        String pound = scanner.nextLine();
        LogEntry log = new LogEntry(LocalDate.now(), LogEntry.LOG_TYPE.WEIGHT, Double.parseDouble(pound));
        return new Object[]{updateIndex, "weight", log};
    }

    private Object setCaloriesForDailyLog(int updateIndex) {
        System.out.print("Calories (number): ");
        String calories = scanner.nextLine();
        LogEntry log = new LogEntry(LocalDate.now(), LogEntry.LOG_TYPE.CALORIES, Double.parseDouble(calories));
        return new Object[]{updateIndex, "calories", log};
    }

    private void createDailyLog() {
        while (true) {
            System.out.print("\n" +
                    "Type of log?\n" +
                    "1. Weight\n" +
                    "2. Calories\n" +
                    "3. Food\n" +
                    "4. Exercise\n\n"
                    + "Enter your option: ");
            String type = scanner.nextLine();

            if (type.matches("[1-4]")) {
                Object obj = new Object() {
                };
                switch (type) {
                    case "1":
                        obj = setWeightForDailyLog(-1);
                        break;
                    case "2":
                        obj = setCaloriesForDailyLog(-1);
                        break;
                    case "3":
                        obj = selectFoodTypeForDailyLog(-1);
                        break;
                    case "4":
                        obj = addExerciseToDailyLog(-1);
                        break;
                }
                if (obj != null) {
                    logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "create"));
                    System.out.println("\nNew log was inserted.");
                    break;
                } else {
                    System.out.println("You might have entered invalid input, please try again");
                }
            } else {
                System.out.println("You have entered invalid input, please try again");
            }
        }
    }

    private void updateBasicFood() {
        getAllBasicFood();

        try {
            System.out.print("\nEnter the index of the basic food you want to update: ");
            var index = Integer.parseInt(scanner.nextLine());

            System.out.print("\nName: ");
            String name = scanner.nextLine();
            System.out.print("Calories: ");
            String calories = scanner.nextLine();
            System.out.print("Carbs: ");
            String carbs = scanner.nextLine();
            System.out.print("Proteins: ");
            String proteins = scanner.nextLine();
            System.out.print("Fats: ");
            String fats = scanner.nextLine();

            // create new basic food
            Food food = new Basic(name, Integer.parseInt(calories), Double.parseDouble(carbs), Double.parseDouble(proteins), Double.parseDouble(fats));

            Object[] obj = new Object[]{Logs.LOGS_TYPE.BASIC, index, food};

            foodListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
            System.out.println("\n" + food.getName() + " was updated.");
        } catch (Exception e) {
            System.out.println("You have entered invalid input, please try again");
            e.printStackTrace();
        }
    }

    private void updateRecipe() {
        getAllRecipes();
        try {
            System.out.print("\nEnter the index of the recipe you want to update: ");
            var index = Integer.parseInt(scanner.nextLine());

            System.out.print("\nName: ");
            String name = scanner.nextLine();

            getAllBasicFood();
            System.out.print("\nEnter the foods (separate space): ");
            var recipeFood = scanner.nextLine().split(" ");

            Object[] obj = new Object[]{Logs.LOGS_TYPE.RECIPE, index - 1, name, recipeFood};

            foodListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
            System.out.println("\n" + name + " was updated.");
        } catch (Exception e) {
            System.out.println("You have entered invalid input, please try again");
            e.printStackTrace();
        }
    }

    private void updateExercise() {
        getAllExercises();
        try {
            System.out.print("\nEnter the index of the exercise you want to update: ");
            var index = Integer.parseInt(scanner.nextLine());

            System.out.print("\nName: ");
            String name = scanner.nextLine();
            System.out.print("Calories: ");
            String calories = scanner.nextLine();

            // create new basic food
            Exercise exercise = new Exercise(name, Exercise.Exercise_TYPE.CALORIES, Double.parseDouble(calories));

            Object[] obj = new Object[]{Logs.LOGS_TYPE.EXERCISE, index - 1, exercise};

            exerciseListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
            System.out.println("\n" + name + " was updated.");
        } catch (Exception e) {
            System.out.println("You have entered invalid input, please try again");
            e.printStackTrace();
        }
    }

    private void updateDailyLog() {
        getAllDailyLog();
        System.out.print("\nEnter the index of the log you want to update: ");
        var index = Integer.parseInt(scanner.nextLine()) - 1;

        while (true) {
            System.out.print("\n" + "Type of log?\n" + "1. Weight\n" + "2. Calories\n" + "3. Food\n" + "4. Exercise\n\n"
                    + "Enter your option: ");
            String type = scanner.nextLine();

            if (type.matches("[1-4]")) {
                Object obj = new Object() {
                };
                switch (type) {
                    case "1":
                        obj = setWeightForDailyLog(index);
                        break;
                    case "2":
                        obj = setCaloriesForDailyLog(index);
                        break;
                    case "3":
                        obj = selectFoodTypeForDailyLog(index);
                        break;
                    case "4":
                        obj = addExerciseToDailyLog(index);
                        break;
                }
                if (obj != null && -1 < index) {
                    logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));
                    break;
                } else {
                    System.out.println("You might have entered invalid input, please try again");
                }
            } else {
                System.out.println("You have entered invalid input, please try again");
            }
        }
    }

    private void deleteBasicFood() {
        getAllBasicFood();
        deleteObject(Logs.LOGS_TYPE.BASIC, "basic food");
    }

    private void deleteRecipe() {
        getAllRecipes();
        deleteObject(Logs.LOGS_TYPE.RECIPE, "recipe");
    }

    private void deleteExercise() {
        getAllExercises();
        deleteObject(Logs.LOGS_TYPE.EXERCISE, "exercise");
    }

    private void deleteDailyLog() {
        getAllDailyLog();
        deleteObject(Logs.LOGS_TYPE.DAILY_LOG, "daily log");
    }

    private void deleteObject(Logs.LOGS_TYPE type, String name) {
        try {
            System.out.print("\nEnter the index of the " + name + " you want to delete: ");
            var index = Integer.parseInt(scanner.nextLine());

            Object[] obj = new Object[]{type, index - 1};

            switch (type.ordinal()) {
                case 0:
                case 1:
                    foodListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "delete"));
                    break;
                case 2:
                    logListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "delete"));
                    break;
                case 3:
                    exerciseListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "delete"));
                    break;
            }
            System.out.println("\nSuccessfully deleted");

        } catch (Exception e) {
            System.out.println("You have entered invalid input, please try again");
            e.printStackTrace();
        }
    }

    private void loadAllCSVs() {
        fileListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "load"));
    }

    private void saveAllCSVs() {
        fileListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "save"));
    }
}
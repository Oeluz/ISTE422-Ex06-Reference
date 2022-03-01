import view.CLI.CommandLine;
import view.Swing.SwingUI;

import java.awt.*;
import java.util.Scanner;

/**
 * Wellness Manager is a program that allows the user to manage their wellness
 *
 * @author Team ZXQ
 */
public class WellnessManager {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        while (true) {
            System.out.println("\nWelcome to the Wellness Manager!\n");
            System.out.println("Please select an option for application:");
            System.out.println("1. CommandLine");
            System.out.println("2. Swing UI");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        new CommandLine().run();
                        break;
                    case 2:
                        EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                try {
                                    SwingUI frame = new SwingUI();
                                    frame.setVisible(true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
                e.printStackTrace();
            }
        }
    }
}
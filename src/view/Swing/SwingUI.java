package view.Swing;

import controller.FileChangeListener;
import model.Logs;
import view.Swing.Menu.MenuBar;
import view.Swing.Panel.ExercisesPanel;
import view.Swing.Panel.FoodPanel;
import view.Swing.Panel.LogsPanel;
import view.Swing.Panel.NetCaloriesPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SwingUI extends JFrame {

    private JPanel contentPane;
    private Logs logs = new Logs();
    private FileChangeListener fileChangeListener = new FileChangeListener(logs);

    /**
     * Create the frame.
     */
    public SwingUI() {
        loadAllCSVs();

        setSize(new Dimension(800, 600));
        setTitle("Wellness Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(new MenuBar(logs));

        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // set GridBagLayout
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        // Label
        JLabel lblLogs = new JLabel("Logs");
        GridBagConstraints gbc_lblLogs = new GridBagConstraints();
        gbc_lblLogs.insets = new Insets(0, 0, 5, 5);
        gbc_lblLogs.gridx = 0;
        gbc_lblLogs.gridy = 0;
        contentPane.add(lblLogs, gbc_lblLogs);
        lblLogs.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogs.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel lblExercises = new JLabel("Exercises");
        lblExercises.setFont(new Font("Arial", Font.PLAIN, 20));
        GridBagConstraints gbc_lblExercises = new GridBagConstraints();
        gbc_lblExercises.insets = new Insets(0, 0, 5, 5);
        gbc_lblExercises.gridx = 1;
        gbc_lblExercises.gridy = 0;
        contentPane.add(lblExercises, gbc_lblExercises);

        JLabel lblNetCalories = new JLabel("Net Calories");
        lblNetCalories.setFont(new Font("Arial", Font.PLAIN, 20));
        GridBagConstraints gbc_lblNetCalories = new GridBagConstraints();
        gbc_lblNetCalories.insets = new Insets(0, 0, 5, 0);
        gbc_lblNetCalories.gridx = 2;
        gbc_lblNetCalories.gridy = 0;
        contentPane.add(lblNetCalories, gbc_lblNetCalories);

        JLabel lblFood = new JLabel("Food");
        lblFood.setFont(new Font("Arial", Font.PLAIN, 20));
        GridBagConstraints gbc_lblFood = new GridBagConstraints();
        gbc_lblFood.insets = new Insets(0, 0, 5, 5);
        gbc_lblFood.gridx = 1;
        gbc_lblFood.gridy = 2;
        contentPane.add(lblFood, gbc_lblFood);

        // Panel
        JPanel logsPanel = new LogsPanel(logs);
        GridBagConstraints gbc_logsPanel = new GridBagConstraints();
        gbc_logsPanel.gridheight = 3;
        gbc_logsPanel.insets = new Insets(0, 0, 0, 5);
        gbc_logsPanel.fill = GridBagConstraints.BOTH;
        gbc_logsPanel.gridx = 0;
        gbc_logsPanel.gridy = 1;
        contentPane.add(logsPanel, gbc_logsPanel);

        JPanel exercisePanel = new ExercisesPanel(logs);
        GridBagConstraints gbc_exercisePanel = new GridBagConstraints();
        gbc_exercisePanel.insets = new Insets(0, 0, 5, 5);
        gbc_exercisePanel.fill = GridBagConstraints.BOTH;
        gbc_exercisePanel.gridx = 1;
        gbc_exercisePanel.gridy = 1;
        contentPane.add(exercisePanel, gbc_exercisePanel);

        JPanel foodsPanel = new FoodPanel(logs);
        GridBagConstraints gbc_foodsPanel = new GridBagConstraints();
        gbc_foodsPanel.insets = new Insets(0, 0, 0, 5);
        gbc_foodsPanel.fill = GridBagConstraints.BOTH;
        gbc_foodsPanel.gridx = 1;
        gbc_foodsPanel.gridy = 3;
        contentPane.add(foodsPanel, gbc_foodsPanel);

        JPanel netCaloriesPanel = new NetCaloriesPanel(logs);
        GridBagConstraints gbc_NetCaloriesPanel = new GridBagConstraints();
        gbc_NetCaloriesPanel.gridheight = 3;
        gbc_NetCaloriesPanel.fill = GridBagConstraints.BOTH;
        gbc_NetCaloriesPanel.gridx = 2;
        gbc_NetCaloriesPanel.gridy = 1;
        contentPane.add(netCaloriesPanel, gbc_NetCaloriesPanel);

        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void loadAllCSVs() {
        fileChangeListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "load"));
    }
}

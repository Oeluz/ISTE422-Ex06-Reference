package view.Swing.Dialog.Exercise;

import controller.ExerciseChangeListener;
import model.Exercise;
import model.Logs;
import model.Exercise.Exercise_TYPE;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExerciseEditDialog  extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldName;
    private JTextField textFieldCalories;

    private Logs logs;
    private final ExerciseChangeListener exerciseChangeListener;
    
    /**
     * Create the dialog.
     */
    public ExerciseEditDialog(Exercise exercise, Logs logs) {
        this.logs = logs;
        exerciseChangeListener = new ExerciseChangeListener(logs);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Edit Exercise");
        setSize(new Dimension(300, 150));
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblName = new JLabel("Name");
            lblName.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblName = new GridBagConstraints();
            gbc_lblName.insets = new Insets(0, 0, 5, 5);
            gbc_lblName.anchor = GridBagConstraints.WEST;
            gbc_lblName.gridx = 0;
            gbc_lblName.gridy = 0;
            contentPanel.add(lblName, gbc_lblName);
        }
        {
            textFieldName = new JTextField(exercise.getName());
            textFieldName.setToolTipText("Enter the exercise name");
            textFieldName.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldName = new GridBagConstraints();
            gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
            gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldName.gridx = 1;
            gbc_textFieldName.gridy = 0;
            contentPanel.add(textFieldName, gbc_textFieldName);
            textFieldName.setColumns(10);
        }
        {
            JLabel lblCalories = new JLabel("Calories");
            lblCalories.setFont(new Font("Arial", Font.BOLD, 16));
            GridBagConstraints gbc_lblCalories = new GridBagConstraints();
            gbc_lblCalories.anchor = GridBagConstraints.WEST;
            gbc_lblCalories.insets = new Insets(0, 0, 0, 5);
            gbc_lblCalories.gridx = 0;
            gbc_lblCalories.gridy = 1;
            contentPanel.add(lblCalories, gbc_lblCalories);
        }
        {
            textFieldCalories = new JTextField(String.valueOf(exercise.getCalories()));
            textFieldCalories.setToolTipText("Enter the exercise calories");
            textFieldCalories.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_textFieldCalories = new GridBagConstraints();
            gbc_textFieldCalories.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldCalories.gridx = 1;
            gbc_textFieldCalories.gridy = 1;
            contentPanel.add(textFieldCalories, gbc_textFieldCalories);
            textFieldCalories.setColumns(10);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton addButton = new JButton("Save");
                addButton.setActionCommand("Save");
                addButton.addActionListener(e -> {

                    try {
                        Exercise newExercise = new Exercise(
                                textFieldName.getText(),
                                Exercise_TYPE.CALORIES,
                                Double.parseDouble(textFieldCalories.getText())
                                );

                        Object[] obj = new Object[]{
                                Logs.LOGS_TYPE.EXERCISE, // type
                                exercise.getName(), // old exercise name
                                newExercise // new exercise object
                        };

                        exerciseChangeListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "update"));

                        dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Wrong input data", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
                buttonPane.add(addButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(e -> dispose());
                buttonPane.add(cancelButton);
            }
        }
    }

}

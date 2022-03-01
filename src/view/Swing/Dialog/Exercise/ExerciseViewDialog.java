package view.Swing.Dialog.Exercise;

import controller.ExerciseChangeListener;
import model.Exercise;
import model.Logs;
import model.SwingModels.ListModels.ExerciseListModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExerciseViewDialog  extends JDialog {
    private final JPanel contentPanel = new JPanel();

    private ExerciseEditDialog exerciseEditDialog;

    private Logs logs;
    private ExerciseChangeListener exerciseChangeListener;

    /**
     * Create the dialog.
     */
    public ExerciseViewDialog(Exercise exercise, ExerciseListModel exerciseListModel, Logs logs) {
        this.logs = logs;
        exerciseChangeListener = new ExerciseChangeListener(logs);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("View Exercise");
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
            JLabel lblShowName = new JLabel(exercise.getName());
            lblShowName.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblShowName = new GridBagConstraints();
            gbc_lblShowName.anchor = GridBagConstraints.WEST;
            gbc_lblShowName.insets = new Insets(0, 0, 5, 0);
            gbc_lblShowName.gridx = 1;
            gbc_lblShowName.gridy = 0;
            contentPanel.add(lblShowName, gbc_lblShowName);
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
            JLabel lblShowCalories = new JLabel(String.valueOf(exercise.getCalories()));
            lblShowCalories.setFont(new Font("Arial", Font.PLAIN, 16));
            GridBagConstraints gbc_lblShowCalories = new GridBagConstraints();
            gbc_lblShowCalories.anchor = GridBagConstraints.WEST;
            gbc_lblShowCalories.gridx = 1;
            gbc_lblShowCalories.gridy = 1;
            contentPanel.add(lblShowCalories, gbc_lblShowCalories);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton editButton = new JButton("Edit");
                editButton.setActionCommand("Edit");
                editButton.addActionListener(e -> {
                    exerciseEditDialog = new ExerciseEditDialog(exercise, logs);
                    exerciseEditDialog.setVisible(true);
                    dispose();
                });
                buttonPane.add(editButton);
            }
            {
                JButton removeButton = new JButton("Remove");
                removeButton.setActionCommand("Remove");
                removeButton.addActionListener(e -> {
                            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this exercise?", "Remove food", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                Object[] obj = new Object[]{
                                        Logs.LOGS_TYPE.EXERCISE,
                                        exercise.getName()
                                };

                                try {
                                    if (exerciseListModel.removeData(exercise.getName())) {
                                        exerciseChangeListener.actionPerformed(new ActionEvent(obj, ActionEvent.ACTION_PERFORMED, "delete"));
                                        dispose();
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "The exercise exists in the list of log entries. So the delete operation cannot be performed.",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                    }

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Delete failed.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                );
                buttonPane.add(removeButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(e -> dispose());
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}

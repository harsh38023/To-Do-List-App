import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ToDoListApp {
    // GUI components
    private JFrame frame;
    private JPanel panel;
    private JTextField taskField;
    private JButton addButton, removeButton, markCompleteButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel; // To hold tasks
    private ArrayList<String> completedTasks;   // To keep track of completed tasks

    public ToDoListApp() {
        // Initialize the frame
        frame = new JFrame("To-Do List Application");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initialize the panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Initialize task input field
        taskField = new JTextField();
        taskField.setPreferredSize(new Dimension(200, 30));

        // Initialize buttons
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        markCompleteButton = new JButton("Mark Complete");

        // Initialize task list and its model
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);  // Use the model for the JList
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Initialize completed tasks list
        completedTasks = new ArrayList<>();

        // Set up action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    listModel.addElement(task);  // Add the task to the list
                    taskField.setText("");       // Clear the input field
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a task.");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);  // Remove the selected task
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a task to remove.");
                }
            }
        });

        markCompleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String completedTask = taskList.getSelectedValue();
                    completedTasks.add(completedTask);  // Add to completed tasks
                    listModel.set(selectedIndex, completedTask + " (Completed)");  // Mark as completed
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a task to mark as complete.");
                }
            }
        });

        // Set up the layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(markCompleteButton);

        // Add components to the panel
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);  // Scrollable list
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create the application instance on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListApp();
            }
        });
    }
}

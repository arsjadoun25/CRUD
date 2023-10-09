import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Employeelist extends JFrame implements ActionListener {
    private JTextField nameField, emailField;
    private JButton createButton, readButton, updateButton, deleteButton;
    private Connection connection;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/YOUR_DATABASE";  //Enter Your Database name in place of YOUR_DATABASE 
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "YOUR_PASSWORD";   //Enter Your Password name in place of YOUR_PASSWORD

    public Employeelist() {
        initializeUI();
        connectToDatabase();
    }

    private void initializeUI() {
        // Create components
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        createButton = new JButton("Create");
        readButton = new JButton("Read");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");

        // Set layout
        setLayout(new FlowLayout());

        // Add components to the frame
        // add(new JLabel("ID:"));
        // add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Email:"));
        add(emailField);
        add(createButton);
        add(readButton);
        add(updateButton);
        add(deleteButton);

        // Add action listeners
        createButton.addActionListener(this);
        readButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        // Set frame properties
        setTitle("CRUD Operations");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.");
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            createRecord();
        } else if (e.getSource() == readButton) {
            readRecords();
        } else if (e.getSource() == updateButton) {
            updateRecord();
        } else if (e.getSource() == deleteButton) {
            deleteRecord();
        }
    }

    private void createRecord() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        try {
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Record created successfully.", "Create Record", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to create record.", "Create Record", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void readRecords() {    
        String targetName = JOptionPane.showInputDialog(this, "Enter the name to view records:");

    try {
        String query = "SELECT * FROM users WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, targetName);

        ResultSet resultSet = statement.executeQuery();

        StringBuilder result = new StringBuilder();
        boolean found = false;
        while (resultSet.next()) {
            found = true;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            result.append("ID: ").append(id).append(", Name: ").append(name).append(", Email: ").append(email).append("\n");
        }

        if (found) {
            JOptionPane.showMessageDialog(this, result.toString(), "User Records for " + targetName, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No records found for the specified name.", "Read Records", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to read records.", "Read Records", JOptionPane.ERROR_MESSAGE);
    }
    }

    private void updateRecord() {
        // Implement the update operation similar to the previous example
        // ...
        try {
            int userId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the user ID to update:"));
            String newEmail = JOptionPane.showInputDialog(this, "Enter the new email:");
    
            String query = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newEmail);
            statement.setInt(2, userId);
            int rowsAffected = statement.executeUpdate();
    
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully.", "Update Record", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No record found with the specified ID.", "Update Record", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update record.", "Update Record", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRecord() {
        // Implement the delete operation similar to the previous example
        // ...
        try {
            int userId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the user ID to delete:"));
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
    
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully.", "Delete Record", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No record found with the specified ID.", "Delete Record", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to delete record.", "Delete Record", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Employeelist form = new Employeelist();
            form.setVisible(true);
        });
    }
}

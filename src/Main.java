import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {  

    private TextField idField;
    private TextField nameField;
    private TextField roleField;
    private TextField hoursField;
    private TextArea outputArea;

    private WorkHourDAO dao = new WorkHourDAO();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create Labels and TextFields for user input
        Label idLabel = new Label("ID:");
        idField = new TextField();
        idField.setPromptText("Enter ID for update/delete");

        Label nameLabel = new Label("Name:");
        nameField = new TextField();
        nameField.setPromptText("Enter name");

        Label roleLabel = new Label("Role:");
        roleField = new TextField();
        roleField.setPromptText("Enter role");

        Label hoursLabel = new Label("Hours:");
        hoursField = new TextField();
        hoursField.setPromptText("Enter hours");

        // Buttons for CRUD operations
        Button loadButton = new Button("Load from Database");
        Button addButton = new Button("Add to Database");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        // Create TextArea to display the output 
        outputArea = new TextArea(); // display output
        outputArea.setEditable(false); // non editble
        outputArea.setPrefHeight(250); // set height

        // Load button action and fetch all entries from the database
        loadButton.setOnAction(e -> {
            outputArea.clear();
            List<WorkHourEntry> entries = dao.getAllEntries();
            if (entries.isEmpty()) {
                outputArea.setText("No records found.");
            } else {
                for (WorkHourEntry entry : entries) {
                    outputArea.appendText(entry.toString() + "\n");
                }
            }
        });

        // Add button action to Insert a new entry into the database
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText().trim();
                String role = roleField.getText().trim();
                String hoursText = hoursField.getText().trim();

                if (name.isEmpty() || role.isEmpty() || hoursText.isEmpty()) {
                    outputArea.setText("Error: Name, Role, and Hours cannot be empty.");
                    return;
                }

                double hours = Double.parseDouble(hoursText);
                WorkHourEntry newEntry = new WorkHourEntry(name, role, hours);
                outputArea.setText(dao.addEntry(newEntry));

                clearFields();  // Clear fields after operation
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: Hours must be a valid number.");
            }
        });

        // Update button action to Update an existing entry by ID
        updateButton.setOnAction(e -> {
            try {
                String idText = idField.getText().trim();
                String name = nameField.getText().trim();
                String role = roleField.getText().trim();
                String hoursText = hoursField.getText().trim();

                if (idText.isEmpty() || name.isEmpty() || role.isEmpty() || hoursText.isEmpty()) {
                    outputArea.setText("Error: ID, Name, Role, and Hours are required for update.");
                    return;
                }

                int id = Integer.parseInt(idText);
                double hours = Double.parseDouble(hoursText);
                WorkHourEntry updatedEntry = new WorkHourEntry(id, name, role, hours);

                outputArea.setText(dao.updateEntry(updatedEntry));
                clearFields();
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: ID must be an integer and Hours must be a valid number.");
            }
        });

        // Delete button action to Delete an entry by ID
        deleteButton.setOnAction(e -> {
            try {
                String idText = idField.getText().trim();

                if (idText.isEmpty()) {
                    outputArea.setText("Error: ID is required for delete.");
                    return;
                }

                int id = Integer.parseInt(idText);
                outputArea.setText(dao.deleteEntry(id));

                clearFields();
            } catch (NumberFormatException ex) {
                outputArea.setText("Error: ID must be a valid integer.");
            }
        });

        // Layout setup (VBox)
        VBox layout = new VBox(10); // arange vertically with spacing
        layout.setPadding(new Insets(15)); // add padding
        layout.getChildren().addAll( 
                idLabel, idField,
                nameLabel, nameField,
                roleLabel, roleField,
                hoursLabel, hoursField,
                loadButton, addButton, updateButton, deleteButton,
                outputArea
        );

        // Scene setup
        Scene scene = new Scene(layout, 500, 600); // pass the layout and set height and width

        // Stage setup
        primaryStage.setTitle("Work Hours Management System"); // set title
        primaryStage.setScene(scene); // Sets the scene that contains all the UI components
        primaryStage.show(); // make it visible
    }

    // Method to clear the input fields
    private void clearFields() { // every time they click button it will clear the inputs
        idField.clear();
        nameField.clear();
        roleField.clear();
        hoursField.clear();
    }
}
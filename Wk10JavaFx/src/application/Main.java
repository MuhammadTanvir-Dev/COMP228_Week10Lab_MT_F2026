
package application;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        UserDao userDao = new UserDao();

        primaryStage.setTitle("Employment Application");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Employment Application");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(title, 0, 0, 2, 1);

        Label fullNameLabel = new Label("Full Name:");
        grid.add(fullNameLabel, 0, 1);
        TextField fullNameField = new TextField();
        grid.add(fullNameField, 1, 1);

        Label addressLabel = new Label("Current Address:");
        grid.add(addressLabel, 0, 2);
        TextField addressField = new TextField();
        grid.add(addressField, 1, 2);

        Label contactLabel = new Label("Contact Number:");
        grid.add(contactLabel, 0, 3);
        TextField contactField = new TextField();
        grid.add(contactField, 1, 3);

        Label emailLabel = new Label("Email Address:");
        grid.add(emailLabel, 0, 4);
        TextField emailField = new TextField();
        grid.add(emailField, 1, 4);

        Label educationLabel = new Label("Education:");
        grid.add(educationLabel, 0, 5);
        TextField educationField = new TextField();
        grid.add(educationField, 1, 5);

        Label dateLabel = new Label("Date Available:");
        grid.add(dateLabel, 0, 6);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 1, 6);

        Label positionLabel = new Label("Desired Position:");
        grid.add(positionLabel, 0, 7);
        TextField positionField = new TextField();
        grid.add(positionField, 1, 7);

        Label salaryLabel = new Label("Desired Salary:");
        grid.add(salaryLabel, 0, 8);
        TextField salaryField = new TextField();
        grid.add(salaryField, 1, 8);

        // Gender

        Label genderLabel = new Label("Gender:");
        grid.add(genderLabel, 0, 9);

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");

        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);
        male.setUserData("Male");
        female.setUserData("Female");

        HBox genderBox = new HBox(10, male, female);
        grid.add(genderBox, 1, 9);

        // Legal Authorization

        Label authorizedLabel = new Label("Legally Authorized to Work:");
        grid.add(authorizedLabel, 0, 10);

        ToggleGroup authorizedGroup = new ToggleGroup();
        RadioButton authorizedYes = new RadioButton("Yes");
        RadioButton authorizedNo = new RadioButton("No");

        authorizedYes.setToggleGroup(authorizedGroup);
        authorizedNo.setToggleGroup(authorizedGroup);
        authorizedYes.setUserData("Yes");
        authorizedNo.setUserData("No");

        HBox authorizedBox = new HBox(10, authorizedYes, authorizedNo);
        grid.add(authorizedBox, 1, 10);

        // Relatives Working

        Label relativesLabel = new Label("Relatives Working:");
        grid.add(relativesLabel, 0, 11);

        ToggleGroup relativesGroup = new ToggleGroup();
        RadioButton relativesYes = new RadioButton("Yes");
        RadioButton relativesNo = new RadioButton("No");

        relativesYes.setToggleGroup(relativesGroup);
        relativesNo.setToggleGroup(relativesGroup);
        relativesYes.setUserData("Yes");
        relativesNo.setUserData("No");

        HBox relativesBox = new HBox(10, relativesYes, relativesNo);
        grid.add(relativesBox, 1, 11);

        Label explanationLabel = new Label("Explanation:");
        grid.add(explanationLabel, 0, 12);
        TextArea explanationArea = new TextArea();
        explanationArea.setPrefRowCount(2);
        grid.add(explanationArea, 1, 12);

        Label signatureLabel = new Label("Signature:");
        grid.add(signatureLabel, 0, 13);
        TextField signatureField = new TextField();
        grid.add(signatureField, 1, 13);

        Button saveButton = new Button("Submit");
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(saveButton);
        grid.add(hBox, 1, 14);

        saveButton.setOnAction(actionEvent -> {

            String gender = genderGroup.getSelectedToggle() == null
                    ? "" : genderGroup.getSelectedToggle().getUserData().toString();

            String authorized = authorizedGroup.getSelectedToggle() == null
                    ? "" : authorizedGroup.getSelectedToggle().getUserData().toString();

            String relatives = relativesGroup.getSelectedToggle() == null
                    ? "" : relativesGroup.getSelectedToggle().getUserData().toString();

            if (fullNameField.getText().trim().isEmpty()
                    || addressField.getText().trim().isEmpty()
                    || contactField.getText().trim().isEmpty()
                    || emailField.getText().trim().isEmpty()
                    || educationField.getText().trim().isEmpty()
                    || positionField.getText().trim().isEmpty()
                    || salaryField.getText().trim().isEmpty()
                    || signatureField.getText().trim().isEmpty()
                    || datePicker.getValue() == null
                    || gender.isEmpty() || authorized.isEmpty()
                    || relatives.isEmpty()) {

                this.alert("Error", "Please complete all fields!",
                        Alert.AlertType.ERROR);
                return;
            }

            try {
                BigDecimal salary = new BigDecimal(salaryField.getText().trim());

                if (salary.compareTo(BigDecimal.ZERO) < 0
                        || salary.compareTo(new BigDecimal("99999999.99")) > 0) {
                    this.alert("Error", "Invalid salary!",
                            Alert.AlertType.ERROR);
                    return;
                }

                User user = this.createUserObject(
                        fullNameField.getText().trim(),
                        addressField.getText().trim(),
                        contactField.getText().trim(),
                        emailField.getText().trim(),
                        educationField.getText().trim(),
                        gender, datePicker.getValue(),
                        positionField.getText().trim(),
                        salary, authorized, relatives,
                        explanationArea.getText().trim(),
                        signatureField.getText().trim());

                int userId = userDao.saveUser(user);

                if (userId > 0) {
                    this.alert("Save", "Successful!",
                            Alert.AlertType.INFORMATION);
                } else {
                    this.alert("Error", "Failed!",
                            Alert.AlertType.ERROR);
                }

            } catch (NumberFormatException ex) {
                this.alert("Error", "Please enter a valid salary!",
                        Alert.AlertType.ERROR);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                System.out.println(ex.getSQLState());

                this.alert("Error", "Database error!",
                        Alert.AlertType.ERROR);
            }
        });

        Scene scene = new Scene(new ScrollPane(grid), 750, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void alert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public User createUserObject(String fullName, String address,
            String contact, String email, String education,
            String gender, LocalDate date, String position,
            BigDecimal salary, String authorized, String relatives,
            String explanation, String signature) {

        User user = new User();
        user.setFullName(fullName);
        user.setCurrentAddress(address);
        user.setContactNumber(contact);
        user.setEmailAddress(email);
        user.setEducation(education);
        user.setGender(gender);
        user.setDateAvailable(date);
        user.setDesiredPosition(position);
        user.setDesiredSalary(salary);
        user.setLegallyAuthorized(authorized);
        user.setRelativesWorking(relatives);
        user.setExplanation(explanation);
        user.setSignature(signature);

        return user;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
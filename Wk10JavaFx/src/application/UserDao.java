
package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class UserDao {

    public int saveUser(User user) throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.getDBConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO employment_application "
                    + "(full_name, current_address, contact_number, email_address, "
                    + "education, gender, date_available, desired_position, "
                    + "desired_salary, legally_authorized, relatives_working, "
                    + "explanation, signature) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(
                    query, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;

            statement.setString(counter++, user.getFullName());
            statement.setString(counter++, user.getCurrentAddress());
            statement.setString(counter++, user.getContactNumber());
            statement.setString(counter++, user.getEmailAddress());
            statement.setString(counter++, user.getEducation());
            statement.setString(counter++, user.getGender());

            statement.setDate(counter++,
                    java.sql.Date.valueOf(user.getDateAvailable()));

            statement.setString(counter++, user.getDesiredPosition());
            statement.setBigDecimal(counter++, user.getDesiredSalary());
            statement.setString(counter++, user.getLegallyAuthorized());
            statement.setString(counter++, user.getRelativesWorking());
            statement.setString(counter++, user.getExplanation());
            statement.setString(counter++, user.getSignature());

            statement.executeUpdate();

            connection.commit();

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException exception) {

            if (connection != null) {
                connection.rollback();
            }

            throw exception;

        } finally {

            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return 0;
    }
}

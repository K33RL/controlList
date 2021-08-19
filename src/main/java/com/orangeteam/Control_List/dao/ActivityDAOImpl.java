package com.orangeteam.Control_List.dao;

import com.orangeteam.Control_List.exception.EmptyIdException;
import com.orangeteam.Control_List.exception.UserUniqueViolationException;
import com.orangeteam.Control_List.model.Activity;
import com.orangeteam.Control_List.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityDAOImpl implements ActivityDAO {
    private static final String TABLE_NAME = "activity";

    private Connection connection;

    public ActivityDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /* ================================
     ActivityDAO implementation
    ================================ */

    @Override
    public List<Activity> getAll() {
        final String query = "SELECT * FROM " + TABLE_NAME + ";";
        List<Activity> activityList = new ArrayList<>();
        try (Statement statement = this.connection.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Activity nextActivity = makeQueriedActivity(rs);

                UserDAO userDAO = new UserDAOImpl(this.connection);
                Optional<User> nextUser = userDAO.getById(rs.getInt("user_id"));
                nextUser.ifPresent(nextActivity::setUser);

                activityList.add(nextActivity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityList;
    }

    @Override
    public List<Activity> getAllByUser(@NotNull User user) throws EmptyIdException {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?;";
        List<Activity> activityList = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(query)){
            if (user.getId() == 0) {
                throw new EmptyIdException();
            }
            statement.setLong(1, user.getId());

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Activity nextActivity = makeQueriedActivity(rs);
                nextActivity.setUser(user);

                activityList.add(nextActivity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activityList;
    }

    @Override
    public Optional<Activity> getById(int activityId) {
        final String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?;";
        Activity activity = null;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, activityId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                activity = makeQueriedActivity(rs);

                UserDAO userDAO = new UserDAOImpl(this.connection);
                Optional<User> user = userDAO.getById(rs.getInt("user_id"));
                user.ifPresent(activity::setUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(activity);

    }

    @Override
    public Optional<Activity> addByUser(@NotNull User user, @NotNull Activity activity) throws EmptyIdException {
        final String query = "INSERT INTO " + TABLE_NAME + "(user_id, duration_min, description) VALUES (?, ?, ?);";
        Activity addedActivity = null;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            if (user.getId() == 0) {
                throw new EmptyIdException();
            }
            statement.setLong(1, user.getId());
            statement.setObject(2, activity.getDurationMin());
            statement.setString(3, activity.getDescription());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 1) {
                ResultSet idsRs = statement.getGeneratedKeys();
                if (idsRs.next()) {
                    int insertedId = idsRs.getInt(1);
                    addedActivity = new Activity(insertedId, user, activity.getDurationMin(), activity.getDescription());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(addedActivity);
    }

    @Override
    public int removeAllByUserId(int userId) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE user_id = ?;";
        int deletedRowsCount = 0;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            deletedRowsCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRowsCount;
    }

    @Override
    public int update(Activity activity) throws EmptyIdException {
        final String query = "UPDATE " + TABLE_NAME + " SET user_id = ? , duration_min = ?, description = ? WHERE id = ?;";
        int updatedRowsCount = 0;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, activity.getUser().getId());
            statement.setInt(2, activity.getDurationMin());
            statement.setString(3, activity.getDescription());
            if (activity.getId() == 0) {
                throw new EmptyIdException();
            }
            statement.setInt(4, activity.getId());

            updatedRowsCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedRowsCount;
    }

    @Override
    public int remove(int activityId) {
        final String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        int deletedRowsCount = 0;
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, activityId);

            deletedRowsCount = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedRowsCount;
    }

    /* ================================
     static methods
    ================================ */

    private static Activity makeQueriedActivity(@NotNull ResultSet rs) throws SQLException {
        int activityId = rs.getInt("id");
        int activityDuration = rs.getInt("duration_min");
        String description = rs.getString("description");
        return new Activity(activityId, new User(), activityDuration, description);
    }
}

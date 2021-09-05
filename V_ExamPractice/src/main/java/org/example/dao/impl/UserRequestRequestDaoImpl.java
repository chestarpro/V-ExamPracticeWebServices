package org.example.dao.impl;

import org.example.config.BaseDao;
import org.example.dao.UserRequestDao;
import org.example.entity.UserRequestEntity;
import org.example.model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRequestRequestDaoImpl implements UserRequestDao {

    @Override
    public UserRequestEntity saveUserRequest(UserRequestEntity userRequestEntity) {
        String sql = "INSERT INTO user_requests (request_date_time, user_name, year_of_birth, gender) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = BaseDao.connect().prepareStatement(sql)){
            userRequestEntity.setRequestDateTime(LocalDateTime.now());
            ps.setTimestamp(1, Timestamp.valueOf(userRequestEntity.getRequestDateTime()));
            ps.setString(2, userRequestEntity.getName());
            ps.setInt(3, userRequestEntity.getYearOfBirth());
            ps.setString(4, userRequestEntity.getGender());

            if(ps.executeUpdate() > 0)
                return userRequestEntity;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<UserRequestEntity> getAllUserRequests() {
        String sql = "SELECT * FROM user_requests";
        List<UserRequestEntity> userEntities = new ArrayList<>();
        try (ResultSet rs = BaseDao.connect().createStatement().executeQuery(sql)) {
            while (rs.next()) {
                UserRequestEntity userRequestEntity = new UserRequestEntity();
                userRequestEntity.setId(rs.getLong("id"));
                userRequestEntity.setRequestDateTime(rs.getTimestamp("request_date_time").toLocalDateTime());
                userRequestEntity.setName(rs.getString("user_name"));
                userRequestEntity.setYearOfBirth(rs.getInt("year_of_birth"));
                userRequestEntity.setGender(rs.getString("gender"));
                userEntities.add(userRequestEntity);
            }
            return userEntities;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean deleteByUserRequestId(Long id) {
        String sql = "DELETE FROM user_requests WHERE id = ?";
        try (PreparedStatement ps = BaseDao.connect().prepareStatement(sql)){
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<UserModel> getByUserName(String name) {
        String sql = "SELECT * FROM user_requests WHERE user_name = ?";
        List<UserModel> userModels = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = BaseDao.connect().prepareStatement(sql)) {
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel userModel = new UserModel();
                userModel.setName(rs.getString("user_name"));
                userModel.setYearOfBirth(rs.getInt("year_of_birth"));
                userModel.setGender(rs.getString("gender"));
                userModels.add(userModel);
            }
            return userModels;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<UserModel> getByUserYearOfBirth(Integer yearOfBirth) {
        String sql = "SELECT * FROM user_requests WHERE year_of_birth = ?";
        List<UserModel> userModels = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = BaseDao.connect().prepareStatement(sql)) {
            ps.setInt(1, yearOfBirth);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel userModel = new UserModel();
                userModel.setName(rs.getString("user_name"));
                userModel.setYearOfBirth(rs.getInt("year_of_birth"));
                userModel.setGender(rs.getString("gender"));
                userModels.add(userModel);
            }
            return userModels;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<UserModel> getByUserGender(String userGender) {
        String sql = "SELECT * FROM user_requests WHERE gender = ?";
        List<UserModel> userModels = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = BaseDao.connect().prepareStatement(sql)) {
            ps.setString(1, userGender);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel userModel = new UserModel();
                userModel.setName(rs.getString("user_name"));
                userModel.setYearOfBirth(rs.getInt("year_of_birth"));
                userModel.setGender(rs.getString("gender"));
                userModels.add(userModel);
            }
            return userModels;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}

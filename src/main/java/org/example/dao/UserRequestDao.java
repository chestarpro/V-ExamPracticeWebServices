package org.example.dao;

import org.example.entity.UserRequestEntity;
import org.example.model.UserModel;

import java.util.List;

public interface UserRequestDao {
    UserRequestEntity saveUserRequest(UserRequestEntity userRequestEntity);
    List<UserRequestEntity> getAllUserRequests();
    Boolean deleteByUserRequestId(Long id);

    // Создать отдельные точки, которые по Get возвращают всех людей определенного имени, года рождения или пола (поиск)
    List<UserModel> getByUserName(String name);
    List<UserModel> getByUserYearOfBirth(Integer yearOfBirth);
    List<UserModel> getByUserGender(String userGender);
}

package com.fours.tolevelup.repository.user;


import com.fours.tolevelup.model.entity.User;

import java.util.Optional;

public interface UserCustomRepository {
    void saveUser(User user);
    Optional<User> findById(String id);
    void delete(String id);
    void update(User user);

}

package com.fours.tolevelup.user;


import java.util.Optional;

public interface UserCustomRepository {
    void saveUser(User user);
    Optional<User> findById(String id);
    void delete(String id);
    void update(User user);

}

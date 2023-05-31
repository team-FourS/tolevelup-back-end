package com.fours.tolevelup.user;


public interface UserCustomRepository {
    void saveUser(User user);
    User findById(String id);
    void delete(String id);
    void update(User user);

}

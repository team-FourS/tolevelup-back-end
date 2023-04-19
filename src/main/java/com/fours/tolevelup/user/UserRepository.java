package com.fours.tolevelup.user;
import org.springframework.stereotype.Repository;


public interface UserRepository{
    void save(User user);
    void delete(User user);

}

package com.fours.tolevelup.user;
import org.springframework.stereotype.Repository;



public interface UserRepository{
    User save(User user);
}

package com.fours.tolevelup.user;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository{
    void save(User user);
    User findById(String id);
    void delete(String id);



}

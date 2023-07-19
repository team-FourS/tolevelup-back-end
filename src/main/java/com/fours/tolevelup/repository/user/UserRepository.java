package com.fours.tolevelup.repository.user;

import com.fours.tolevelup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {

}

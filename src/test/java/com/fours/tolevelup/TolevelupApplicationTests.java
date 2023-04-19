package com.fours.tolevelup;

import com.fours.tolevelup.user.User;
import com.fours.tolevelup.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class TolevelupApplicationTests {

	@Autowired
	private UserRepository userRepository;
	EntityManager em;


	@Test
	void contextLoads() {
	}

}

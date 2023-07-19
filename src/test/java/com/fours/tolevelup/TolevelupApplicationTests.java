package com.fours.tolevelup;

import com.fours.tolevelup.repository.user.UserCustomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class TolevelupApplicationTests {

	@Autowired
	private UserCustomRepository userRepository;
	EntityManager em;


	@Test
	void contextLoads() {
	}

}

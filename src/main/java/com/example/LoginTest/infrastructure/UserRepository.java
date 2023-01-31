package com.example.LoginTest.infrastructure;

import com.example.LoginTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


/*
	 	Data Acess Object의 줄임말인데 말 그대로 데이터 접근을 위한 객체
	 	즉, DB와의 접근을 위한 기능을 수행한다.
	 	스프링에서는 @Repository 어노테이션을 이용해 구현한다.

	 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}


package com.example.LoginTest.controller;

import com.example.LoginTest.domain.User;
import com.example.LoginTest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * User CRUD Controller
 * 유저와 관련된 매핑 url이 통하는 곳
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    /*
	 * 전체적인 restful 구조는 Uniform interface에 대한 개념이 적용됨.
	 * 하나의 URL을 이용해서 여러 응답을 가져갈 수 있다는 뜻.
	   /users라는 url로 하나는 GET 하나는 POST로 요청을 보내고
	   또 /users/{userid}라는 url로 GET, PUT, DELETE 메서드 요청을 보내고 있다.
	 */
//    @Autowired
    private UserService userService;
    // Layered Architecture는 인접한 계층끼리만 통신이 가능하다. Controller랑 Service랑 통신을 위해 의존 관계를 설정해줘야 하는데
    // @Autowired를 통해 자동으로 설정이 가능해진다.

    // 코드들의 흐름을 보면 UserController에서는 요청을 받고 해당 요청을 UserSerivce로 전달해 최종 결과만을 리턴하고 있다.

    // 모든 유저 조회
    // uri : localhost:8080/users

    /*
     *필드 주입이나 수정자 주입보다 생성자 주입의 사용이 권장
     * 순환 참조를 방지할 수 있다.
     * - 순환 참조가 발생하는 경우 애플리케이션이 구동되지 않는다.
     * 테스트 코드 작성이 편리하다.
     * - 단순 POJO를 이용한 테스트 코드를 만들 수 있다.
     * 나쁜 냄새를 없앤다.
     * - 조금 더 품질 좋은 코드를 만들 수 있다.
     * immutable 하다.
     * - 실행 중에 객체가 변하는 것을 막을 수 있다.
     * - 오류를 사전에 방지할 수 있다.
     */

//https://madplay.github.io/post/why-constructor-injection-is-better-than-field-injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();

    }

    // 유저 아이디 조회
    // uri : localhost:8080/users/{userid}
    @GetMapping("/{userid}")
    public Optional<User> getUserByUserid(@PathVariable Long userid) {
        log.info("-- GET: localhost:8080/gifs/{}, getUserByUserid() called", userid);
        log.debug("-- @PathVariable String id: {}", userid);

        return userService.getUserByUserId(userid);
    }

    // 유저 등록
    // uri : localhost:8080/users
    @PostMapping
    public User registerUser(@RequestBody User newUser) {
        log.info("-- POST: localhost:8080/users, registerUser() called");
        log.info("-- @RequestBody Gif: {}", newUser);

        return userService.registerUser(newUser);


    }

    // 유저 수정
    // uri : localhost:8080/users/{userid}
    // https://charliecharlie.tistory.com/263 TODO : anotation 정리
    @PutMapping("/{userid}")
    public void updateUser(@RequestBody User updateUser) {
        log.info("-- POST: localhost:8080/users, updateUser() called");
        log.info("-- @RequestBody Gif: {}", updateUser);
        userService.updateUser(updateUser);
    }

    // 유저 삭제
    // uri : localhost:8080/users
    // TODO : 후에 사라질수도 있는 메서드
    @DeleteMapping("/{userId}")
    public List<User> removeUser(@RequestParam("userId") Long deleteUserId) {
        log.info("-- POST: localhost:8080/users, removeUser() called");
        log.info("-- @RequestParam Gif: {}", deleteUserId);
        return userService.removeUser(deleteUserId);
    }

}

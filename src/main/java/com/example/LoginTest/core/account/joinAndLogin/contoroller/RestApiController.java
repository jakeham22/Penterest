package com.example.LoginTest.core.account.joinAndLogin.contoroller;


import com.example.LoginTest.config.auth.PrincipalDetailsService;

import com.example.LoginTest.core.account.domain.User;
import com.example.LoginTest.core.account.userCRUD.infrastructure.UserRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.LoginTest.config.auth.PrincipalDetails;



import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("api/accounts")
@Api(tags = "Login and Join API")
@RequiredArgsConstructor
public class RestApiController {
    private final UserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 모든 사람이 접근 가능
    @GetMapping("/home")
    public String home() {
        return "<h1>home</h1>";
    }

    //이상없음
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout";
    }

    //403 error 발생 ㅣ
    @GetMapping("/user")
    public PrincipalDetails user(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        
        System.out.println("principal : "+principal.getUser().getId());
        System.out.println("principal : "+principal.getUser().getUsername());
        System.out.println("principal : "+principal.getUser().getPassword());


        return principal;
    }

    // 어드민이 접근 가능
    @GetMapping("/admin/users")
    public List<User> users(){
        return userRepository.findAll();
    }

    @PostMapping("/join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setCreateDate(user.getCreateDate());
        user.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(user);

        return "회원가입완료";
    }

}

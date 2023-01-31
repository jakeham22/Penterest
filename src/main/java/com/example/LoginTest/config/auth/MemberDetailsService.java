//package com.example.LoginTest.config.auth;
//
//import com.example.LoginTest.domain.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import com.example.LoginTest.infrastructure.UserRepository;
//
//@Service
//@Slf4j
//public class MemberDetailsService implements UserDetailsService {
//
//    UserRepository userRepository;
//
//    public MemberDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if(user != null) {
//            return new MemberDetails(user);
//        }
//
//        return null; // TODO : NUll Pointer 예상 지점 checkpoint 예외처리 필요해 보임
//    }
//}

package com.example.LoginTest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Getter
@NoArgsConstructor  // ??
@AllArgsConstructor // ??
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO : AUTO vs IDENTITY ???
    private long id;
    private String username;
    private String password;
    private String email;

    private int phoneNumber;
    private String roles; //ROLE_USER, ROLE_ADMIN

    private LocalDateTime createDate;


    // TODO : 관련 에러 해결 https://jhkang-tech.tistory.com/92

    public List<String> getRoleList() throws NullPointerException{
        System.out.println("User getRoleList called()");
        if(this.roles.length() > 0){
            System.out.println(roles);
            return Arrays.asList(this.roles.split(","));
        }
        System.out.println(roles.getClass());
        return new ArrayList<>();


    }

}

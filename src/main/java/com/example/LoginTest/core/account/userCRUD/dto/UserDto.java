package com.example.LoginTest.core.account.userCRUD.dto;



import com.example.LoginTest.core.account.domain.User;
import lombok.*;


import java.time.LocalDateTime;

/**
 * User CRUD Dto
 * 회원가입을 위해서 User 중 필요한 정보를 넘길 Dto Class
 *
 */

@Data // @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private long id;
    private String username;
    private String password;
    private String email;

    private String phoneNumber;
    private String roles; //ROLE_USER, ROLE_ADMIN

    private LocalDateTime createDate;



    /**
     * Id, password는 데이터변경 금지를 위해 여기서는 처리하지 않음
     *
     */


    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .phoneNumber(phoneNumber)
                .roles(roles)
                .createDate(createDate)
                .build();





    }
}

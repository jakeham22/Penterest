package com.example.LoginTest.core.gifAPI.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GifBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 변경 사항 : String > Long , AutoIncrement로 변경할 예정 (V)
    // ERD : 게시물의 PostID가 int임(primary key) > 검색의 primakry key는 postId로 varchar(20)로 설정되어있음 > (수정했음)
    // question : primary key는 타입이 동일해야하는가 ?
    // gif(게시물)내에서는 GifId(long)로 관리하고, 검색 entity에서는 PostId(varchar, 여기서는 String)로 설정해야하는가 ?

    private String gifUrl;

    private String caption;
    // gif 제목

    // TODO 현재시간 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=varkiry05&logNo=221736856257
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")


    //    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;


}

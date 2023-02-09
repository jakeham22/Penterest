package com.example.LoginTest.core.gifAPI.aplication;


import com.example.LoginTest.core.gifAPI.domain.GifBoard;
import com.example.LoginTest.core.gifAPI.infrastructure.GifRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class GifManager implements GifFinder,GifEditor {
    private GifRepository gifRepository;

    public GifManager(GifRepository gifRepository){
        this.gifRepository = gifRepository;
    }


    /**
     * 등록된 전체 GIF 조회
     *
     */
    @Override
    public List<GifBoard> findAll() {
        // Dummy Data 테스트, DB 사용 전 -> 일단 메소드 내부에서 객체 생성 후 List<User>로 변환해서 반환
//        Gif gif1 = new Gif("1","c:\hello","2023-01-01","The man and woman are walking")
//        List<Gif> gifs = new ArrayList<>(Arrays.asList(gif1));
//
//        if (gifs.isEmpty()) return Collections.emptyList(); // immutable 불변리스트로 반환해줌.

        return gifRepository.findAll();
    }

    @Override
    public GifBoard findGif(Long id) {
        //        if (id == null) {
//            throw NoSuchElementException(message);
//        }
//        User u = new User(id, "1234");

        String message = String.format("%s에 해당하는 Gif가 없습니다.", id);
        GifBoard gifBoard = gifRepository.findById(id).orElseThrow(() -> new NoSuchElementException(message));
        return gifBoard;


        // TODO : 예외처리 로직 필요함
    }

//    @Override
//    public String  create(Gif newGif) {
//        if(gifRepository.findById(newGif)) {
//            String message = String.format("이미 존재하는 user id 입니다. %s", newGif.getId());
//            throw new IllegalArgumentException(message);
//        }
//        return gifRepository.save(newGif);
//        return newGif.getId(); 반환 값이 String 이였을 때
//    }

    public GifBoard create(GifBoard newGif) {
        if (!gifRepository.findById(newGif.getId()).isPresent()) {
            return gifRepository.save(newGif);
        }
        String message = String.format("이미 존재하는 user id 입니다. %s", newGif.getId());
        throw new IllegalArgumentException(message);
    }


    public List<GifBoard> delete(Long gifId) {
        gifRepository.deleteById(gifId);
        return findAll();


    }


}

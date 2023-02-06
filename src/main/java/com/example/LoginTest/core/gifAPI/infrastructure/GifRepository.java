package com.example.LoginTest.core.gifAPI.infrastructure;


import com.example.LoginTest.core.gifAPI.domain.GifBoard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GifRepository extends JpaRepository<GifBoard, Long> { // JpaRepository는 Generic type이라 <>가 가능함
    // <>안에는 1. 반환 타입과, 2. 기본 key의 타입을 써주면 됨.
}

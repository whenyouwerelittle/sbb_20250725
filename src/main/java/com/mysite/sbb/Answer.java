package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
/*
@Entity 어노테이션은 JPA에서 해당 클래스를 데이터베이스 테이블과 매핑되는 “엔티티”로 지정합니다.
- 클래스 레벨에 붙이며, 이 클래스의 인스턴스 하나가 DB 테이블의 한 레코드와 대응됩니다.
- 반드시 하나 이상의 필드를 @Id로 지정해 기본 키(PK)를 선언해야 합니다.
- 별도 @Table 지정이 없으면 클래스명이 테이블명으로 사용됩니다.

* */
@Entity // db
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    @ManyToOne  //Entity realationship type N:1 (Answear N / Question 1) //FK 자동생성
    // @ManyToOne(fetch = FetchType.EAGER)
    private Question question;
}
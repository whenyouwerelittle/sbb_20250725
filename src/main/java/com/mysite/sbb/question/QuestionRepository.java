package com.mysite.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {  // id 자료형을 선언
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable);
}

/*
@Repository
public interface QuestionServiceRepository extends JpaRepository<Question, Integer> {  // id 자료형을 선언
    Question findById(Integer id);
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
}*/

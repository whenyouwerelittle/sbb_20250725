package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //- Tells Spring this interface is part of the persistence layer. jpa 규칙
/* JpaRepository<Answer, Integer>: Inherits CRUD functionality.
→ 기본적인 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) 기능 자동 제공.
- Answer: Entity class (엔티티 클래스)
- Integer: Primary key type (기본 키의 자료형)
* */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}

package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest

class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	void testJpa3() {
		Optional<Question> oq = this.questionRepository.findById(1);

		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}

	@Test
	void testJpa4() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa5() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}

	@Test
	void testJpa6() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	void testJpa7() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	void testJpa8() {
		assertEquals(2, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(1, this.questionRepository.count());
	}
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testJpa9() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}
	@Test
	@Transactional // 외부에서 선언하면 오류남
	void testJpa010() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}

	@Test
	@Transactional
	@Rollback(false)/*transactional 을 설정하게되면 테스트 종료 후 db가 롤백되기 때문에 필요하다면 Rollback(false) annotation을 추가 한다*/
	void testJpa011() {

		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}
}
/* my code
package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

	@Autowired	// 클래스 객체 생성 자동화
	private QuestionRepository questionRepository;
	// 스프링에서 의존성 주입(Dependency Injection)을 통해
	// QuestionRepository 객체를 자동으로 생성해서 사용할 수 있도록 하는 부분
	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("What is the SBB anyway?");
		q1.setContent("I want to know about the SBB.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("Have a question about the SpringBoot Model.");
		q2.setContent("Is the value of id generated automatically?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}
	@Test
	void testJpa2(){
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());// 가져올 값이 2개가 맞냐?

		Question q = all.get(0);
		assertEquals("What is the SBB anyway?", q.getSubject()); // subject 내용이 맞냐?
	}

	void test3Jpa() {
		Optional<Question> oq = this.questionRepository.findById(1);
		// Optional 값이 존재하는지 모를 때사용. 다시말해서 	Question이 NULL 일 경우에 대비하여 사용
        // 데이터가 있는 경우에
        // 줄여서 사용하는 경우
		// oq.ifPresent(q -> assertEquals("What is the SBB anyway?", q.getSubject()));
		if (oq.isPresent()) {	// 데이터가 있는 경우에
			Question q = oq.get();
			assertEquals("What is the SBB anyway?", q.getSubject());

		}
	}

	@Test
	void test4Jpa() {
		Question q = this.questionRepository.findBySubject("What is the SBB anyway?");
		assertEquals(1, q.getId());
	}

	@Test
	void test5Jpa() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"What is the SBB anyway?", "I want to know about the SBB.");
		assertEquals(1, q.getId());
	}

	@Test
	void test6Jpa() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("What is the SBB anyway?", q.getSubject());
	}

	@Test
	void test7Jpa() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}
}

*/

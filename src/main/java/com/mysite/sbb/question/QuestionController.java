package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionRepository questionRepository; // springboot 에서 자동생성

    @GetMapping("/question/list")
    public String list(Model model) { //org.springframework.ui.Model;
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
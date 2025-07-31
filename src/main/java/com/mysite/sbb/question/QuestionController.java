package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping; // url prefix

@RequestMapping("/question") // url prefix
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    //@GetMapping("/question/list")
    @GetMapping("/list")    // url perfix
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    // @GetMapping("question/detail/{id}")
    @GetMapping(value= "/detail/{id}")// url perfix
    public String getQuestion(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);

        return "question_detail";
    }
}
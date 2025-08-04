package com.mysite.sbb.question;
import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@RequestMapping("/question") // url prefix
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    //@GetMapping("/question/list")
    @GetMapping("/list")    // url prefix
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
/*

    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
*/

    // @GetMapping("question/detail/{id}")
    @GetMapping(value= "/detail/{id}")  // url prefix
    public String getQuestion(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

/*    @PostMapping("/create")
    @ResponseBody
    public String createQuestion() {
        return "전송완료";
    }
*/

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
//
//    @PostMapping("/create")
//    public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
//        // validating
//
//        // db 저장
//        this.questionService.create(subject, content);
//        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
//    }

}
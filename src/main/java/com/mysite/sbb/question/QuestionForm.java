package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm { // db insert 이전 수행
    @NotEmpty(message="제목은 필수항목입니다.") // not null
    @Size(max=200) // 글자수 제한
    private String subject;

    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}
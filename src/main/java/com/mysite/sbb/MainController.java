package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    public String index(){
        String reMessage = "<br><br><br>방가방가";
        System.out.println(reMessage);
        return reMessage;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}

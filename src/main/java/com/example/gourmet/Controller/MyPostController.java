package com.example.gourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Service.MyPostService1;


@Controller
public class MyPostController {

    @Autowired
    public MyPostService1 myPostService1;
    
    /**
     * 自分の投稿を表示します
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/my-post")
    public String myPost(@AuthenticationPrincipal LoginUser user, Model model) {
        List<Article> myListOfAll = myPostService1.load(user.getRegister().getId());
        model.addAttribute("myListOfAll", myListOfAll);
        return "my-post";
    }

    @RequestMapping("/delete-my-post")
    public String deleteMyPost(Integer id) {
        myPostService1.delete(id);
        return "redirect:/my-post";
    }
}

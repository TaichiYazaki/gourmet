package com.example.gourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Service.ArticleService;


@Controller
@RequestMapping("/")
public class MainPageController {
    
    @Autowired
    private ArticleService articleService;

    
    /**
     * メインページの表示
     * @return
     */
    @RequestMapping("/main-page")
    public String mainPage(@AuthenticationPrincipal LoginUser user, Model model) {
        List<Article> articleOfAll = articleService.list();
        
        model.addAttribute("articleOfAll", articleOfAll);
        return "main-page";
    }

}

package com.example.gourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Form.ArticleForm;
import com.example.gourmet.Repository.MainpageRepository;
import com.example.gourmet.Service.ArticleService;

@Controller
@RequestMapping("/")
public class MainPageController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MainpageRepository mainpageRepository;

    @ModelAttribute
    public ArticleForm form() {
        return new ArticleForm();
    }

    /**
     * メインページの表示
     * 
     * @return
     */
    @RequestMapping("/main-page")
    public String mainPage(@AuthenticationPrincipal LoginUser user, Model model) {
        List<Article> articleOfAll = articleService.list();
        model.addAttribute("articleOfAll", articleOfAll);
        return "main-page";
    }

    /**
     * 店舗の検索
     * 
     * @param articleForm
     * @return
     */
    @RequestMapping("/research-article")
    public String researchArticle(ArticleForm articleForm, Model model) {
        List<Article> list = mainpageRepository.researchArticle(articleForm.getStore());
        if (list.isEmpty()) {
            return "redirect:/main-page";
        } else {
            model.addAttribute("articleOfAll", list);
            return "main-page";
        }
    }
}
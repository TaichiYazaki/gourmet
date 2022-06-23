package com.example.gourmet.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Domain.Register;
import com.example.gourmet.Form.ArticleForm;
import com.example.gourmet.Service.ArticleService;
import com.example.gourmet.Service.MypageService;

@Controller
public class ArticleController {

    @Autowired
    private MypageService mypageService;

    @Autowired
    private ArticleService articleService;

    @ModelAttribute
    public ArticleForm form() {
        return new ArticleForm();
    }

    /**
     * 記事投稿ページの表示
     * 
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/post-article-page")
    public String postArticlePage(@AuthenticationPrincipal LoginUser user, Model model) {
        Register register = mypageService.loadId(user.getRegister().getId());
        model.addAttribute("nickname", register.getNickname());
        return "post-article-page";
    }

    /**
     * 記事を投稿します
     * 
     * @param user
     * @param articleForm
     * @return
     */
    @RequestMapping("/execute-post-article")
    public String executePostArticle(@AuthenticationPrincipal LoginUser user, ArticleForm articleForm,
            @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get("/Users/YAZAKITAICHI/env/vs-code/gourmet/src/main/resources/static/image/" + fileName);
        Article article = new Article();
        article.setStore(articleForm.getStore());
        article.setArea(articleForm.getArea());
        article.setStation(articleForm.getStation());
        article.setCategory(articleForm.getCategory());
        article.setBudget(articleForm.getBudget());
        article.setSmoke(articleForm.getSmoke());
        article.setPhrase(articleForm.getPhrase());
        article.setRegisterId(user.getRegister().getId());
        article.setRegisterNickname(user.getRegister().getNickname());
        article.setImgFile(fileName);
        articleService.insert(article);
        try {
            byte[] bytes = file.getBytes();
            OutputStream stream = Files.newOutputStream(filePath);
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/main-page";
    }

}

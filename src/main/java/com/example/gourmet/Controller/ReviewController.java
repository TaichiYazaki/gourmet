package com.example.gourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Domain.Review;
import com.example.gourmet.Form.ReviewForm;
import com.example.gourmet.Service.ArticleService;
import com.example.gourmet.Service.ReviewService;

@Controller
public class ReviewController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ReviewService reviewService;

    @ModelAttribute
    public ReviewForm form() {
        return new ReviewForm();
    }

    /**
     * レビュー画面の表示
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/review")
    public String review(Integer articleId, Model model) {
        Article article = articleService.oneOfList(articleId);
        List<Review> reviewOfAll = reviewService.list(articleId);
        model.addAttribute("reviewOfAll", reviewOfAll);
        model.addAttribute("article", article);
        return "review";
    }

    /**
     * レビューを投稿します。
     * 
     * @param articleId
     * @param reviewForm
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/execute-review")
    public String executeReview(Integer articleId, ReviewForm reviewForm, @AuthenticationPrincipal LoginUser user,
            Model model) {
        reviewService.insert(reviewForm.getComment(), user.getRegister().getId(), articleId);
        List<Review> reviewOfAll = reviewService.list(articleId);
        model.addAttribute("reviewOfAll", reviewOfAll);
        return "forward:/review";
    }

    /**
     * いいねの数を数えます
     * @param count
     * @return
     */
    @RequestMapping("/count-good")
    @ResponseBody
    public int countGood( @RequestParam Integer count) {
        int json = count+1;
        return json;
    }
}

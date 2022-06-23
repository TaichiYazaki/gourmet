package com.example.gourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.Article;
import com.example.gourmet.Service.AreaPageService;

@Controller
public class AreaPageController {

    @Autowired
    private AreaPageService areaPageService;

    /**
     * 池袋〜高田馬場・早稲田エリアの投稿を表示
     * 
     * @param model
     * @return
     */
    @RequestMapping("/ikebukuro-area")
    public String ikebukuroArea(String area, Model model) {
        area="池袋〜高田馬場・早稲田";
        List<Article> article = areaPageService.listOfIkebukuro(area);
        model.addAttribute("articleOfAll", article);
        return "ikebukuro-area";
    }
}

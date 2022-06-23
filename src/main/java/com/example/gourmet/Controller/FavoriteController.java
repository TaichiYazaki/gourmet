package com.example.gourmet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.Favorite;
import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Service.FavoriteService;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    
    
    /**
     * お気に入り表示
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/favorite-page")
    public String favoritePage(@AuthenticationPrincipal LoginUser user, Model model) {
        List<Favorite> favoriteOfAll = favoriteService.list(user.getRegister().getId());
        model.addAttribute("favoriteOfAll", favoriteOfAll);
        return "favorite-page";
    }

    /**
     * お気に入り追加
     * @param user ユーザーID
     * @param id   投稿ID
     * @return
     */
    @RequestMapping("/execute-favorite")
    public String executeFavorite(@AuthenticationPrincipal LoginUser user, Integer id) {
        favoriteService.insert(user.getRegister().getId(), id);
        return "redirect:/main-page";
    }

    /**
     * お気に入り削除
     * @param id
     * @return
     */
    @RequestMapping("/delete-favorite")
    public String deleteFavorite(Integer id) {
        System.out.println("delete="+id);
        favoriteService.delete(id);
        return "redirect:/favorite-page";
    }
}

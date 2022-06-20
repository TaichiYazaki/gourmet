package com.example.gourmet.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Domain.Register;
import com.example.gourmet.Form.RegisterForm;
import com.example.gourmet.Service.MypageService;

@Controller
@RequestMapping("/")
public class MypageController {

    @ModelAttribute
    public RegisterForm form() {
        return new RegisterForm();
    }

    @Autowired
    public MypageService mypageService;

    @RequestMapping("/my-page")
    public String myPage(@AuthenticationPrincipal LoginUser user, Model model) {
        Register register = mypageService.loadId(user.getRegister().getId());
        System.out.println(register);
        model.addAttribute("nickname", register.getNickname());
        model.addAttribute("phrase", register.getPhrase());
        return "my-page";
    }

    //////////////////////////////////////////////////////////
    /////////////// プロフィール画像に関する設定開始////////////////
    //////////////////////////////////////////////////////////
    @RequestMapping("/profile-img-change-page")
    public String profileImgChangePage() {
        return "profile-img-change-page";
    }

    @RequestMapping("/execute-profile-img-change")
    public String executeProfileImgChange(@AuthenticationPrincipal LoginUser user,@RequestParam("image") MultipartFile multipartFile, Model model) {
    
        return "/my-page";
       }
    }
    //////////////////////////////////////////////////////////
    /////////////// プロフィール画像に関する設定終了////////////////
    //////////////////////////////////////////////////////////

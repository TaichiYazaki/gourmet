package com.example.gourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("nickname", register.getNickname());
        model.addAttribute("phrase", register.getPhrase());
        model.addAttribute("image", register.getImgFile());
        return "my-page";
    }

}

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
public class ProfileEditController {

    @ModelAttribute
    public RegisterForm form() {
        return new RegisterForm();
    }

    @Autowired
    private MypageService mypageService;

    @RequestMapping("/profile-edit")
    public String profileEdit() {
        return "profile-edit";
    }

    @RequestMapping("/execute-profile-edit")
    public String executeProfileEdit(@AuthenticationPrincipal LoginUser user, RegisterForm registerForm, Model model) {
        Register register = mypageService.loadId(user.getRegister().getId());
        register.setNickname(registerForm.getNickname());
        register.setPhrase(registerForm.getPhrase());
        mypageService.update(register);
        
        model.addAttribute("nickname", register.getNickname());
        model.addAttribute("phrase", register.getPhrase());
       
        return "/my-page";
    }
}

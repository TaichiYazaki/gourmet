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

     
    @RequestMapping("/profile-img-change-page")
    public String profileImgChangePage(@AuthenticationPrincipal LoginUser user, Model model) {
        Register register = mypageService.loadId(user.getRegister().getId());
        model.addAttribute("image", register.getImgFile());
        return "profile-img-change-page";
    }

    @RequestMapping("/execute-profile-img-change")
    public String executeProfileImgChange(@AuthenticationPrincipal LoginUser user,
            @RequestParam("file") MultipartFile file, Model model) {
        String fileName = file.getOriginalFilename();
        Register register = mypageService.loadId(user.getRegister().getId());
        Path filePath = Paths
                .get("/Users/YAZAKITAICHI/env/vs-code/gourmet/src/main/resources/static/image/" + fileName);

        register.setImgFile(fileName);
        mypageService.imgFileUpdate(register);
        model.addAttribute("img", register.getImgFile());
        try {
            byte[] bytes = file.getBytes();
            OutputStream stream = Files.newOutputStream(filePath);
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/my-page";

    }

    @RequestMapping("/execute-profile-edit")
    public String executeProfileEdit(@AuthenticationPrincipal LoginUser user, RegisterForm registerForm, Model model) {
        Register register = mypageService.loadId(user.getRegister().getId());
        register.setNickname(registerForm.getNickname());
        register.setPhrase(registerForm.getPhrase());
        mypageService.update(register);
        model.addAttribute("nickname", register.getNickname());
        model.addAttribute("phrase", register.getPhrase());
        model.addAttribute("image", register.getImgFile());
        return "/my-page";
    }
}

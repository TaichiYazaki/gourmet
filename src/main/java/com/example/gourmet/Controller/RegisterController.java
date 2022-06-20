package com.example.gourmet.Controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.Register;
import com.example.gourmet.Form.RegisterForm;
import com.example.gourmet.Service.RegisterService;

@Controller
@RequestMapping("/")
public class RegisterController {


    /**
     * Formクラスを利用できるようにするための設定
     * 
     */
    @ModelAttribute
    public RegisterForm form() {
        return new RegisterForm();
    }

    // メール送信のために必要
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RegisterService registerService;

    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////ユーザー登録///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    /**
     * 登録画面の表示
     * 
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * DBへユーザー情報を登録
     * 
     * @param form
     * @return
     */
    @RequestMapping("/insert")
    public String insert(RegisterForm form) {
        Register register = new Register();
        register.setName(form.getName());
        register.setEmail(form.getEmail());
        register.setPassword(form.getPassword());
        registerService.insert(register);
        return "redirect:/login";
    }
    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////ユーザー登録///////////////////////////////////
    //////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////ログイン//////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    /**
     * ログイン画面の表示
     * 
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * ログインエラー画面の表示
     * @return
     */
    @RequestMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////ログイン//////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    
    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////ログアウト/////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    @RequestMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "/login";
    }
    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////ログアウト/////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////パスワードリセット//////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    /**
     * パスワードリセット画面の表示
     * 
     */
    @RequestMapping("/password-reset-link")
    public String passwordResetLink() {
        return "password-reset-link";
    }

    /**
     * パスワードをリセットするためのメールを送信する処理
     * @param email
     * @return
     */
    @RequestMapping("/mail-sender")
    public String mailSender(RegisterForm registerForm) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom("tatataichi.taichi@gmail.com");
            helper.setTo(registerForm.getEmail());
            helper.setSubject("パスワードリセットのご案内");// タイトルの設定
            helper.setText("パスワードリセットのリンク→"+"http://localhost:8080/password-reset"); // 本文の設定
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/password-reset-page";

    }

    /**
     * パスワードリセット画面の表示
     * @return
     */
    @RequestMapping("/password-reset-page")
    public String passwordReset() {
        return "password-reset";
    }

    /**
     * パスワードを更新する処理
     * @param registerForm
     * @return
     */
   @RequestMapping("/execute-password-reset")
    public String executePasswordReset(RegisterForm registerForm) {
        return "redirect:/password-reset-done";
    }

    /**
     * パスワード更新完了画面の表示
     * @return
     */
    @RequestMapping("/password-reset-done")
    public String passwordResetDone(){
        return "password-reset-done";
    }

    //////////////////////////////////////////////////////////////////////////
    /////////////////////////////パスワードリセット//////////////////////////////
    //////////////////////////////////////////////////////////////////////////
    
}

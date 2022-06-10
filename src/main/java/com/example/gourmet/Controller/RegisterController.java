package com.example.gourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public RegisterForm form(){
        return new RegisterForm();
    }

@Autowired
private RegisterService registerService;

    /**
     * 登録画面の表示
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    
     @RequestMapping("/insert")
    public String insert(
        RegisterForm form
        ){
            if (form.getName().isEmpty() || form.getEmail().isEmpty() || form.getPassword().isEmpty()) {
            
                return "forward:/register";
            }
         Register register = new Register();
         register.setName(form.getName());
         register.setEmail(form.getEmail());
         register.setPassword(form.getPassword());
         registerService.insert(register);
            return "redirect:/login";
        }
     
        @RequestMapping("/login")
        public String login(){
            return "login";
        }
   
}

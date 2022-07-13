package com.example.gourmet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Form.ReserveForm;
import com.example.gourmet.Service.ReserveService;

@Controller
public class ReserveController {

    @ModelAttribute
    public ReserveForm form(){
        return new ReserveForm();
    }

    @Autowired
    private ReserveService reserveService;
    
    @RequestMapping("/reserve-page")
    public String reservePage(Integer articleId){
        return "reserve-page";
    }

    @RequestMapping("/execute-reserve")
    public String executeReserve(ReserveForm reserveForm, @AuthenticationPrincipal LoginUser user){
        reserveService.insert(reserveForm.getReserveDate(), reserveForm.getReserveTime(),reserveForm.getReservePeople(), user.getRegister().getId());
        return "reserve-done";
    }
}

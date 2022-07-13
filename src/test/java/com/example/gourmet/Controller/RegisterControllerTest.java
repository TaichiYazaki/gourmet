package com.example.gourmet.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gourmet.Domain.Register;
import com.example.gourmet.Service.RegisterService;

@SpringBootTest
public class RegisterControllerTest {
    
    @InjectMocks
    private RegisterControllerTest registerController;

    @Mock
    private RegisterService registerService;


    @Test
    void registerServiceメソッドを呼べているかテスト () {
        Register register = new Register();
        register.setName("test");
        register.setEmail("test@mail");
        register.setPassword("test");
        registerService.insert(register);
        Mockito.verify(registerService).insert(register);
    }
}

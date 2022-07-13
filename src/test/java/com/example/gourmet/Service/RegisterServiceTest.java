package com.example.gourmet.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.gourmet.Domain.Register;
import com.example.gourmet.Repository.RegisterRepository;

@SpringBootTest
public class RegisterServiceTest {

    // テスト対象のクラス
    @InjectMocks
    private RegisterService registerService;

    // テスト対象のクラスで使われているクラス
    @Mock
    private RegisterRepository registerRepository;

    /**
     *
     * / パスワードエンコーダに対する対応がわからない
     */
    @Test
    public void insert() {
        Register register = new Register();
        register.setId(1);
        register.setName("ServiceTest");
        register.setEmail("test");
        register.setPassword("ServiceTest");
        register.setNickname("test");
        register.setImgFile("test.img");
        register.setPhrase("test");
        registerRepository.insert(register);
        Mockito.verify(registerRepository).insert(register);
    }
}

package com.example.gourmet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Register;
import com.example.gourmet.Repository.RegisterRepository;

@Service
@Transactional
public class RegisterService {

  @Autowired
  private RegisterRepository registerRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * ユーザー情報を登録します
   * 
   * @param register
   * @return
   */
  public Register insert(Register register) {
    register.setPassword(passwordEncoder.encode(register.getPassword()));
    return registerRepository.insert(register);
  }

  /**
   * パスワードを更新します
   * @param register
   * @return
   */
 // public Register executePasswordReset(Register register) {
 //   return registerRepository.executePasswordReset(register.getPassword());
 // }
}

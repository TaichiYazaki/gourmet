package com.example.gourmet.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Register;
import com.example.gourmet.Repository.RegisterRepository;

@Service
@Transactional
public class RegisterService {
    
    @Autowired
    private RegisterRepository registerRepository;

   // @Autowired
   // private PasswordEncoder passwordEncoder;

 
    public void insert(Register register) {
       //register.setPassword(passwordEncoder.encode(register.getPassword()));
         registerRepository.insert(register);
    }
     

   
}

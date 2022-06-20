package com.example.gourmet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Domain.Register;
import com.example.gourmet.Repository.MypageRepository;

@Service
@Transactional
public class MypageService {

    @Autowired
    private MypageRepository mypageRepository;

    public Register loadId(Integer id) {
        return mypageRepository.loadId(id);
    }

    public Register update(Register register) {
      return mypageRepository.update(register);
    }
}

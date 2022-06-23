package com.example.gourmet.Service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

  public Register imgFileUpdate(Register name) {
    return mypageRepository.imgFileUpdate(name);
     
  }
}

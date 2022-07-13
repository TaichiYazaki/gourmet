package com.example.gourmet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gourmet.Repository.ReserveRepository;

@Service
@Transactional
public class ReserveService {
    
    @Autowired
    private ReserveRepository reserveRepository;

    public void insert(String reserveDate, Integer reserveTime, Integer reservePeople, Integer registerId){
        reserveRepository.insert(reserveDate, reserveTime, reservePeople, registerId);
    }
}

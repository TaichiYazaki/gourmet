package com.example.gourmet.Service;

import java.util.ArrayList;
import java.util.Collection;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gourmet.Domain.LoginUser;
import com.example.gourmet.Domain.Register;
import com.example.gourmet.Repository.RegisterRepository;

@Service
public class RegisterDetailsService implements UserDetailsService {

    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Register register = registerRepository.findByMailAddress(email);
        if (register == null) {
            throw new UsernameNotFoundException("そのemailは登録されていません。");
        }
        Collection<GrantedAuthority> authorityList = new ArrayList<>();
			authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new LoginUser(register, authorityList);
    }
}

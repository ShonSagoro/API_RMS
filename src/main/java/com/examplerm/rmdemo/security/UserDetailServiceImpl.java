package com.examplerm.rmdemo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examplerm.rmdemo.entities.User;
import com.examplerm.rmdemo.repositories.IUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findOneByEmail(email)
        .orElseThrow(()->new UsernameNotFoundException("The user with email: "+email+" does not exist."));
        
        return new UserDetailsImpl(user);
    }
    
}

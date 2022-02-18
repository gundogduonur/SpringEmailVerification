package com.onurgundogdu.emailverify.service;

import com.onurgundogdu.emailverify.entity.User;
import com.onurgundogdu.emailverify.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND_MSG="User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder cryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }
    public String signUpUser(User user){
        boolean userExist=userRepository.findByEmail(user.getEmail())
                .isPresent();
        if(userExist)
        {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword=cryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "works";
    }
    
}
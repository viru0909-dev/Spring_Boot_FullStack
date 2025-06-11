package org.studyeasy.SpringStarter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.studyeasy.SpringStarter.models.Account;
import org.studyeasy.SpringStarter.repositories.AccountRepository;
import org.studyeasy.SpringStarter.util.constants.Roles;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Roles.USER.getRole());
        return accountRepository.save(account);    
    }   


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Account> optionalAccount = accountRepository.findByEmailIgnoreCase(email);
        if(!optionalAccount.isPresent()){
            throw  new  UsernameNotFoundException("Account not found");
        }
        Account account = optionalAccount.get();



        List<GrantedAuthority> grantedAuthoritys =new ArrayList<>();
        grantedAuthoritys.add(new SimpleGrantedAuthority(account.getRole()));

        return new User(account.getEmail(),account.getPassword(),grantedAuthoritys);
    }

}

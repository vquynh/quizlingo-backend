package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.model.Role;
import de.oncampus.quizlingo.domain.model.user.Account;
import de.oncampus.quizlingo.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(username);
        if (account == null) {
            throw new UsernameNotFoundException("No account found with username: " + username);
        }
        List<GrantedAuthority> authorities = getGrantedAuthorities(account);

        return User.builder()
                .username(account.getUserName())
                .password(account.getPasswordHash())
                .authorities(authorities)
                .build();
    }

    private List<GrantedAuthority> getGrantedAuthorities(Account account) {
        List<GrantedAuthority> authorities;
        if(account.isAdmin()){
            authorities = List.of(new SimpleGrantedAuthority(Role.ADMIN));
        }else{
            authorities = List.of(new SimpleGrantedAuthority(Role.USER));
        }
        return authorities;
    }

}

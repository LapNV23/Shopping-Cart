//package com.example.assignment167.service;
//
//
//import com.example.assignment167.entiy.Account;
//import com.example.assignment167.entiy.dto.AccountDto;
//import com.example.assignment167.entiy.dto.CredentialDto;
//import com.example.assignment167.repository.AccountRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.Collection;
//
//@Service
//@Transactional//đánh dấu đây là 1 transactional
//@RequiredArgsConstructor//làm code ngắn hơn
//public class AccountService implements UserDetailsService {
//    final AccountRepository accountRepository;
//    final PasswordEncoder passwordEncoder;
//    public Account register(AccountDto accountDto){
//        Account account = Account.builder()
//                .username(accountDto.getUsername())
//                .passwordHash(passwordEncoder.encode(accountDto.getPasswordHash()))//mã hóa mật khẩu
//                .build();
//        return accountRepository.save((account));
//    }
//    public Account login(){
//        return null;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username);
//        if (account == null){
//            throw new UsernameNotFoundException("User not found in database");
//        }
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("USER"));
//        return new User(account.getUsername(), account.getPassword(), authorities);
//    }
//
//    public boolean matchPassword(String rawPassword, String hashPassword){
//        return passwordEncoder.matches(rawPassword, hashPassword);
//    }
//
//    public CredentialDto generateCredential(UserDetails userDetails, HttpServletRequest request){
//        String accessToken = JWTUtil.generateToken(userDetails.getUsername(),
//                userDetails.getAuthorities().iterator().next().getAuthority(),
//                request.getRequestURI().toString(),
//                JWTUtil.ONE_DAY * 7);
//        String refreshToken = JWTUtil.generateToken(userDetails.getUsername(),
//                userDetails.getAuthorities().iterator().next().getAuthority(),
//                request.getRequestURI().toString(),
//                JWTUtil.ONE_DAY * 14);
//        return new CredentialDto(accessToken, refreshToken);
//    }
//}

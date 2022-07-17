package com.example.assignment167.service;

import com.example.assignment167.entiy.Account;
import com.example.assignment167.entiy.dto.AccountDto;
import com.example.assignment167.entiy.dto.CredentialDto;
import com.example.assignment167.repository.AccountRepository;
import com.example.assignment167.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor//làm code ngắn hơn
public class MyUserDetailService implements UserDetailsService {
    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;

    public Account register(AccountDto accountDto){
        Account account = Account.builder()
                .username(accountDto.getUsername())
                .passwordHash(passwordEncoder.encode(accountDto.getPasswordHash()))//mã hóa mật khẩu
                .role(accountDto.getRole())
                .build();
        return accountRepository.save((account));
    }
    public Account login(){
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // tim account theo user name trong account
        Account account = accountRepository.findAccountByUsername(username);
        // tao danh sach quyen.(1 nguoi dung co the nhieu quyen
        //co the tao ra table quyen rieng mapping  n - n vs account

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (account.getRole() == 1){
            //them quyen theo truong role trong account
            authorities.add(new SimpleGrantedAuthority("user"));
        } else if (account.getRole() == 2) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        }else if (account.getRole() ==0) {
            authorities.add(new SimpleGrantedAuthority("guest"));
        }
        /*tạo đối tượng user detail theo  thông tin username ,pass và quyền được lấy ra ở trên
        * trong đó pass là pass đc mã háo
        * */
        return new User(account.getUsername(),account.getPasswordHash(),authorities);
    }

    public boolean matchPassword(String rawPassword, String hashPassword){
        return passwordEncoder.matches(rawPassword, hashPassword);
    }

    public CredentialDto generateCredential(UserDetails userDetails, HttpServletRequest request){
        String accessToken = JwtUtil.generateToken(userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURI().toString(),
                JwtUtil.ONE_DAY * 7);
        String refreshToken = JwtUtil.generateToken(userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURI().toString(),
                JwtUtil.ONE_DAY * 14);
        return new CredentialDto(accessToken, refreshToken);
    }
}

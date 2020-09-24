package com.example.webDemo.service;

import com.example.webDemo.db.UserRepository;
import com.example.webDemo.db.dbo.DemoUser;
import com.example.webDemo.db.dbo.Role;
import com.example.webDemo.security.DemoWebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

  private final BCryptPasswordEncoder bCryptPasswordEncoder = DemoWebSecurityConfig.passwordEncoder();

  @Autowired
  private UserRepository userRepository;

  public List<DemoUser> getAll() {
    return userRepository.findAll();
  }


  public DemoUser getOne(Long id) {
    // getOne has no optional but throws an error which you can't define so I prefer findById so I can throw a 400 error
    return userRepository.findById(id)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "This user does not exist"));
  }

  public void createUser(DemoUser user) {

    if (userRepository.findAll().isEmpty()) {
      user.setRole(Role.ADMIN);
    } else {
      user.setRole(Role.USER);
    }

    validateAndEncode(user);

    userRepository.save(user);
  }

  public void deleteUserById(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional
  public void editUser(DemoUser user) {
    userRepository.findById(user.getId())
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST,
            "There is no user with following id: " + user.getId()));

    validateAndEncode(user);

    userRepository.save(user);
  }

  private void validateAndEncode(DemoUser user) {

    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username already exists");
    }

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    DemoUser user = userRepository.findByUsername(username)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "There is no user with following username: " + username));

    List<GrantedAuthority> roles = new ArrayList();
    roles.add(new SimpleGrantedAuthority(user.getRole().name()));

    return new User(user.getUsername(), user.getPassword(), roles);
  }
}

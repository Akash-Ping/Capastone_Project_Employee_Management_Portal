package com.employee.Employee.Management.Portal.controller;

import com.employee.Employee.Management.Portal.config.JwtProvider;
import com.employee.Employee.Management.Portal.dto.LoginInDto;
import com.employee.Employee.Management.Portal.dto.LoginOutDto;
import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.User;
import com.employee.Employee.Management.Portal.repository.UserRepository;
import com.employee.Employee.Management.Portal.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetails customUserDetails;

    @Autowired
    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtProvider jwtProvider,
                          CustomUserDetails customUserDetails) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.customUserDetails = customUserDetails;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutDto> signin(@RequestBody LoginInDto loginRequest) {

        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username + " ----- " + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        LoginOutDto authResponse = new LoginOutDto();

        authResponse.setMessage("Login successful");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        Optional<User> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            authResponse.setEmpId(user.get().getEmpId());
            authResponse.setRole(Role.valueOf(roleName));
        } else {
            throw new BadCredentialsException("Invalid username or password");
        }



        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");

        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
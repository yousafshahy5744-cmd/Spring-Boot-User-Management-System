package com.example.crud_application.service.implementation;

import com.example.crud_application.dto.UserResponseDto;
import com.example.crud_application.security.CustomUserDetailsService;
import com.example.crud_application.security.JwtUtil;
import com.example.crud_application.dto.LoginRequestDto;
import com.example.crud_application.dto.UserSignUpDto;
import com.example.crud_application.model.User;
import com.example.crud_application.repository.UserRepository;
import com.example.crud_application.service.userinterfaces.IAuthorizationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class AuthorizationService implements IAuthorizationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthorizationService(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                CustomUserDetailsService customUserDetailsService,
                                AuthenticationManager authenticationManager,
                                JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    private final JwtUtil jwtUtil;

    @Override
    public String signupUser(UserSignUpDto userSignUpDto) {
        List<User> existingUser =  userRepository.alreadyExists(userSignUpDto.getEmail(), userSignUpDto.getUserCNIC(), userSignUpDto.getMobileNumber());
        for(User u : existingUser){
            if(u.getEmail() != null && u.getEmail().equals(userSignUpDto.getEmail()))
                return "Already in use. Kindly try different email...";
            if (u.getMobileNumber() != null && u.getMobileNumber().equals(userSignUpDto.getMobileNumber()))
                return "Already in use. kindly try different number...";
            if (u.getUserCNIC() != null && u.getUserCNIC().equals(userSignUpDto.getUserCNIC()))
                return "Already in use. kindly try different CNIC...";
        }
        User user = new User();
        user.setUserName(userSignUpDto.getUserName());
        user.setEmail(userSignUpDto.getEmail());
        user.setUserCNIC(userSignUpDto.getUserCNIC());
        user.setPassword(passwordEncoder.encode(userSignUpDto.getPassword()));
        user.setDepartment(userSignUpDto.getDepartment());
        user.setCountry(userSignUpDto.getCountry());
        user.setMobileNumber(userSignUpDto.getMobileNumber());
        user.setUserAge(userSignUpDto.getUserAge());
        user.setCreatedAt(now());
        user.setIsActive(true);
        user.setStatus(User.AccountStatus.valueOf("Pending"));
        user.setRole(User.Role.USER);

        userRepository.save(user);

        return "SignUp Successfully......";
    }


    public ResponseEntity<UserResponseDto> loginUser(LoginRequestDto loginDto) {
       try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                   loginDto.getEmail(),loginDto.getPassword()));
       }
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(()-> new RuntimeException("User not found.."));
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok().body(new UserResponseDto(token,user.getUserName(),user.getUserCNIC(),user.getEmail(),user.getRole()));
    }
}

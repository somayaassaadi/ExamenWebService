package com.example.SomayaAssaadiExam.service;

import com.example.SomayaAssaadiExam.security.JwtService;
import com.example.SomayaAssaadiExam.serviceImpl.AuthImpl;
import com.example.SomayaAssaadiExam.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    UserService userService;
    @Mock
    JwtService jwtService;

    @InjectMocks
    AuthImpl authImplem;


}

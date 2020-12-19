package com.example.pmcourse;

import com.example.pmcourse.model.dto.UserCreateDto;
import com.example.pmcourse.model.entities.User;
import com.example.pmcourse.repository.UserRepository;
import com.example.pmcourse.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.Test;

public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void testCreate() {
        String encodedPassword = "jf2w0jisdlfnweijdf0qjiedsolkfmwepofdjmsd";
        UserCreateDto dto = UserCreateDto.builder()
                .login("user")
                .password("Qwerty123!")
                .build();

        UserServiceImpl userServiceImpl = PowerMockito.spy(new UserServiceImpl());

        PowerMockito.doReturn(encodedPassword).when(passwordEncoder)
                .encode(dto.getPassword());

        User newUser = userServiceImpl.createUser(dto);
        Mockito.verify(userRepository).save(newUser);
        Assertions.assertEquals(encodedPassword, newUser.getPassword());

    }
}

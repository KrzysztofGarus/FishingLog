package pl.someday.FishingApp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.someday.FishingApp.service.UserDetailsServiceImpl;
import pl.someday.FishingApp.service.UserService;

@WebMvcTest(RegisterController.class)
public class RegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    UserService userService;

    @Test
    void showRegistrationPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register")
                .with(SecurityMockMvcRequestPostProcessors.anonymous()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("register"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("userDto"));
    }
}

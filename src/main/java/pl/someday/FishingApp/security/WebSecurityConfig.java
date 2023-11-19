package pl.someday.FishingApp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import pl.someday.FishingApp.service.UserDetailsServiceImpl;


/**
 * Konfiguracja zabezpieczeń aplikacji przy użyciu Spring Security.
 * Ta klasa rozszerza konfigurację domyślną klasy `WebSecurityConfigurerAdapter` i dostarcza
 * ustawienia dotyczące autentykacji, uprawnień dostępu i formularza logowania.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Konstruktor klasy `WebSecurityConfig`.
     *
     * @param userDetailsService Serwis obsługujący szczegóły użytkowników.
     */
    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    /**
     * Konfiguracja szyfrowania hasła.
     *
     * @return Implementacja `PasswordEncoder`.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    /**
     * Konfiguracja menedżera autentykacji.
     *
     * @param auth Menedżer autentykacji Spring Security.
     * @throws Exception Wyrzucane w przypadku błędów konfiguracji autentykacji.
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Konfiguracja zabezpieczeń HTTP.
     *
     * @param http Konfigurator obiektu HttpSecurity.
     * @throws Exception Wyrzucane w przypadku błędów konfiguracji zabezpieczeń HTTP.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    if (AuthorityAuthorizationManager.hasRole("ADMIN").check(() -> authentication, null).isGranted()) {
                        response.sendRedirect("/admin/dashboard");
                    } else {
                        response.sendRedirect("/user/dashboard");
                    }
                })
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}

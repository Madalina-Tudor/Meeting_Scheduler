package com.siemens.meetingschedulersystem.security;


/*public class WebSecurityConfig implements WebSecurityConfigurer<WebSecurity> {


@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("##################################################################################");
        http
                .authorizeHttpRequests(requests->requests
                        .requestMatchers("/signup").hasAnyRole("ROLE_USER",
                                "ROLE_MODERATOR",
                                "ROLE_ADMIN")
                        .anyRequest().authenticated()
                )

                        .csrf().disable();

        return http.build();

}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("ROLE_USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ROLE_USER", "ROLE_USER");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers(request -> "/signup".equals(request.getRequestURI())).hasAnyRole("USER", "MODERATOR", "ADMIN")
//                .anyRequest().authenticated()
//                .and().formLogin();
//    }

    @Override
    public void init(WebSecurity web) throws Exception{

    }

    @Override
    public void configure(WebSecurity builder) throws Exception {
    }

}*/
/*
CLASA CARE MERGEEEEEEEEEEEE
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurtiyFilterChain securityFilterChain(HttpSecurity http) throws Exception{
                http
                        .csrf().disable()
                        .authorizeRequests(authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(req -> req.getRequestURI().startsWith("/signup"))
                                        .authenticated()
                                       // .hasAnyRole("ROLE_USER", "ROLE_MODERATOR", "ROLE_ADMIN")
                                       // .anyRequest().authenticated()
                        );

                return http.build();
        }
}

*/

/*


@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {


        protected void  configure(HttpSecurity http) throws Exception {
                http
                        .csrf().disable()
                        .authorizeRequests()
                        .anyRequest().permitAll()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .and()
                        .logout()
                        .permitAll();
        }

}*/


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
        public class WebSecurityConfig      {
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
                http
                        .csrf().disable()
                        .authorizeRequests()
                        .anyRequest().permitAll()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .and()
                        .logout()
                        .permitAll();

                return http.build();
        }
}





        // http.authorizeRequests().andMatchers(HttpMethod.POST, "/api/v1/users")
        //        .permitAll();

    /*    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers(req -> req.getRequestURI().startsWith("/users/*")).permitAll()
                                    .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer(oauth2ResourceServer ->
                            oauth2ResourceServer
                                    .jwt(jwt -> {})
                    );*/

        //http.csrf().ignoringRequestMatchers("/api/users/*");
        //http.authorizeHttpRequests().requestMatchers("/**").hasRole("ROLE_USER");
   //     http.httpBasic(Customizer.withDefaults());
 //      http
//                .authorizeHttpRequests(requests->requests
//                        .requestMatchers("/signup").hasAnyRole("ROLE_USER",
//                                "ROLE_MODERATOR",
//                                "ROLE_ADMIN")
//                        .anyRequest().authenticated()
//                )
//
//                        .csrf().disable();


//        return http.build();

//}




package pl.com.bbzoftware.sbi.base.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManagerConfiguration {

  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    SbUser sbUser = SbUser.tempUser();
    manager.createUser(
        User.withUsername(sbUser.getName())
            .password(sbUser.getPassword())
            .roles(sbUser.getRole())
            .build()
    );
    SbUser sbAdmin = SbUser.tempAdmin();
    manager.createUser(
        User.withUsername(sbAdmin.getName())
            .password(sbAdmin.getPassword())
            .roles(sbAdmin.getRole())
            .build()
    );
    return manager;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService())
        .passwordEncoder(bCryptPasswordEncoder());
  }
}

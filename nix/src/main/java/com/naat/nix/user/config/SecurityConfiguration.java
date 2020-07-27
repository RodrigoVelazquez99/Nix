package com.naat.nix.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuraciones no por omisión de la seguridad de Spring
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled  = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

  /* Manejar conversión entre usuarios de Spring y usuarios de la aplicación */
  @Autowired
  private UserDetailsService userConfiguration;


  /**
   * Desabilitar indentificación para la consola de la base de datos de prueba.
   * Tiene su propio sistema de usuarios que conflictua con el de Spring.
   * @param web Configuración web
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers("/h2-console/**");
  }

  /**
   * Forzar identificació para todas las URL excepto para el login, sign up y logout.
   * En caso de login, forzar ingreso de credenciales.
   * @param http Configuración HTTP
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/resources/**", "/signup", "/js/addAddress.js", "/js/checkPassword.js").permitAll()
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .loginPage("/login")
        .usernameParameter("email")
        .permitAll()
        .defaultSuccessUrl("/")
        .and()
      .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .permitAll()
        .logoutSuccessUrl("/login?logout");
  }

  /**
   * Registrar nuestro servicio para manejar usuarios y un encriptador para constraseñas
   * @return Proveedor de credenciales usando base de datos.
   */
  @Bean
  public AuthenticationProvider daoAuthenticationProvider() {
    var provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(encoder());
    provider.setUserDetailsService(userConfiguration);
    return provider;
  }

  /**
   * Seleccionar un cifrado de BCrypt como el que se va a usar
   * @return Encriptador a usar
   */
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Selecciona manejador de credenciales
   * @return Manejador de credenciales
   */
  @Bean
  public AuthenticationManager customAuthenticationManager() throws Exception {
    return authenticationManager();
  }


  /**
   * Registrar nuestro servicio para manejar usuarios y un encriptador para constraseñas
   * @param auth Registro para configuraciones de autentificación
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userConfiguration).passwordEncoder(encoder());
  }

}

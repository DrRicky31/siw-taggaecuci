package it.uniroma3.siw.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;
//import static it.uniroma3.siw.model.Credentials.CLIENT_ROLE;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * The datasource is automatically injected into the AuthConfiguration (using its getters and setters)
     * and it is used to access the DB to get the Credentials to perform authentiation and authorization
     */
    @Autowired
    DataSource datasource;

    /**
     * This method provides the whole authentication and authorization configuration to use.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/", "/index", "/login", "/register", "/elencoAccessorio", "/accessorio/**", "/elencoOrdini", "/ordine", "/css/**", "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/startup", "/elencoCollezioni", "/collezione", "/elencoMagliette", "/maglietta/**", "/elencoMateriali", "/materiale/**" ).permitAll() 
                .antMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                
                //aggiungere autorizzazioni personali con CLIENT_ROLE
                
                .antMatchers(HttpMethod.GET, "").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "").hasAnyAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()

                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/default")
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")        
                .invalidateHttpSession(true)
                .clearAuthentication(true).permitAll();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                //use the autowired datasource to access the saved credentials
                .dataSource(this.datasource)
                //retrieve username and role
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                //retrieve username, password and a boolean flag specifying whether the user is enabled or not (always enabled in our case)
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

    /**
     * This method defines a "passwordEncoder" Bean.
     * The passwordEncoder Bean is used to encrypt and decrpyt the Credentials passwords.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
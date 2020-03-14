package br.com.unip.localizacao.security

import br.com.unip.localizacao.security.filter.AuthenticationFilter
import br.com.unip.localizacao.security.filter.CorsFilterCustom
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration(val messageSource: MessageSource) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http.cors()
                .and()
                .csrf().disable()

        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(CorsFilterCustom(), UsernamePasswordAuthenticationFilter::class.java)
                .addFilterBefore(AuthenticationFilter(messageSource), UsernamePasswordAuthenticationFilter::class.java)
    }
}
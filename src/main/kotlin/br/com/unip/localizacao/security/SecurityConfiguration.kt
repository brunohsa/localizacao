package br.com.unip.localizacao.security

import br.com.unip.localizacao.security.filter.CorsFilterCustom
import br.com.unip.localizacao.security.filter.JWTAuthenticationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/v1/enderecos/**")
    }

    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {
        http.cors()
                .and()
                .csrf().disable()

        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(CorsFilterCustom(), UsernamePasswordAuthenticationFilter::class.java)

                .addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}
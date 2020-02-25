package br.com.unip.localizacao.security.filter


import br.com.unip.autenticacaolib.exception.TokenExpiradoException
import br.com.unip.autenticacaolib.exception.TokenInvalidoException
import br.com.unip.autenticacaolib.util.TokenUtil
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JWTAuthenticationFilter : GenericFilterBean() {

    @Throws(TokenExpiradoException::class, TokenInvalidoException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        try {
            val authentication = TokenUtil().getAuthentication(request as HttpServletRequest)
            SecurityContextHolder.getContext().authentication = authentication
            filterChain.doFilter(request, response)
        } catch (e: TokenExpiradoException) {
        } catch (e: TokenInvalidoException) {
        }
    }
}
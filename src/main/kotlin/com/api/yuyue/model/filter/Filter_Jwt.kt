package com.api.yuyue.model.filter

import com.api.yuyue.model.entity.EntityUser
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class Filter_Jwt(
    val jwtDecoder: JwtDecoder,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header: String? = request.getHeader(HttpHeaders.AUTHORIZATION)
//        println("JWT Filter")
        if (header == null || header.isEmpty() || !header.startsWith("Bearer ", true)) {
            filterChain.doFilter(request, response)
            return
        }
//        println("JWT Filter")

        // get token body
        val token = header.split(" ")[1].trim()
        if(token.isEmpty()) {
            filterChain.doFilter(request, response)
            return
        }
//        println("JWT Filter")

        // get token obj
        val jwt: Jwt = jwtDecoder.decode(token)
//        println(jwt.claims)
        val name = jwt.claims["name"].toString()
        val email = jwt.claims["email"].toString()

        val clientRolesMap = jwt.claims["resource_access"] as? Map<*, *>
        val rolesMap = clientRolesMap?.get("yuyue-blog-GvmpapiMKmvnIonNOnva") as? Map<*, *>
        val roles = rolesMap?.get("roles") as? List<String>

        if(roles == null) {
            filterChain.doFilter(request, response)
            return
        }
//        println("JWT Filter")


//        println(roles)
        val user = EntityUser(name, email, roles)

        val authentication = UsernamePasswordAuthenticationToken(
            user, null,
            user.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
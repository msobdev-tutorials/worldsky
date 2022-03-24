package com.msobdev.worldsky.application.config.security

import com.msobdev.worldsky.application.config.security.model.UserDetails
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@ConfigurationProperties(prefix = "security")
@Configuration
class UserDetailsConfig {

    val users: Map<String, UserDetails> = mutableMapOf()

    @Bean
    fun userDetails(): MapReactiveUserDetailsService {
        val userDetails = users.values.stream()
            .map { user ->
                User.withUsername(user.username)
                    .password(passwordEncoder().encode(user.password))
                    .roles(user.role?.name)
                    .build()
            }.toList()

        return MapReactiveUserDetailsService(userDetails)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
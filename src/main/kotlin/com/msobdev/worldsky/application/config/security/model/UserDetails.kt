package com.msobdev.worldsky.application.config.security.model

data class UserDetails(var username: String? = null, var password: String? = null, var role: UserRole? = null)

package com.nibm.healthcare2

data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    // Avoid storing passwords in plaintext in real apps due to Security Reasons
)


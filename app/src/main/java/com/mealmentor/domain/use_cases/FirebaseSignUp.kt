package com.mealmentor.domain.use_cases

import com.mealmentor.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String, name: String)
    = repository.firebaseSignUp(email, password, name)
}
package com.cook_and_share.domain.repository

interface TranslateRepository {
    suspend fun detectLanguage(text: String): String
    suspend fun translateText(text: String, sourceLanguage: String, targetLanguage: String, callback: (String) -> Unit)
}
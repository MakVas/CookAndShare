package com.cook_and_share.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.cook_and_share.domain.repository.TranslateRepository
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TranslateRepositoryImpl @Inject constructor() : TranslateRepository {
    override suspend fun detectLanguage(text: String): String {
        val languageIdentifier = LanguageIdentification.getClient()
        return try {
            val languageCode = languageIdentifier.identifyLanguage(text).await()
            if (languageCode == "und") {
                Log.i(TAG, "Can't identify language.")
                ""
            } else {
                Log.i(TAG, "Language: $languageCode")
                languageCode
            }
        } catch (e: Exception) {
            Log.i(TAG, "Error.")
            ""
        }
    }

    override suspend fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String,
        callback: (String) -> Unit
    ) {
        val translatorOptions = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguage)
            .setTargetLanguage(targetLanguage)
            .build()
        val translator = Translation.getClient(translatorOptions)

        try {
            translator.downloadModelIfNeeded().await()
            val translatedText = translator.translate(text).await()
            callback(translatedText)
        } catch (e: Exception) {
            Log.i(TAG, "Error in translation: ${e.message}")
            callback("")
        }
    }

}
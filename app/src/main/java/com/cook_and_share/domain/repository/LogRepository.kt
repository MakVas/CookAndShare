package com.cook_and_share.domain.repository

interface LogRepository {
    fun logNonFatalCrash(throwable: Throwable)
}
package com.iamnaran.firefly.utils.helper

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

data class Language(
    val code: String,
    val displayLanguage: String
)

val appLanguages = listOf(
    Language("en", "English"), // default language
    Language("ne", "नेपाली"),
    Language("hi", "हिन्दी")
)

class AppLanguageHelper {

    fun changeLanguage(context: Context, languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    fun getLanguageCode(context: Context,): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales[0]?.toLanguageTag()?.split("-")?.first() ?: appLanguages.first().code
        } else {
            AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag()?.split("-")?.first() ?: appLanguages.first().code
        }
    }
}
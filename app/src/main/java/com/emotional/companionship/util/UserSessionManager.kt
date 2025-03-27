package com.emotional.companionship.util

import android.content.Context
import android.content.SharedPreferences
import com.emotional.companionship.data.model.User
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSessionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    
    fun saveUser(user: User) {
        val editor = prefs.edit()
        editor.putString(KEY_USER, gson.toJson(user))
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.apply()
    }
    
    fun getUser(): User? {
        val userJson = prefs.getString(KEY_USER, null) ?: return null
        return try {
            gson.fromJson(userJson, User::class.java)
        } catch (e: Exception) {
            null
        }
    }
    
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    fun logout() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }
    
    companion object {
        private const val PREF_NAME = "user_session"
        private const val KEY_USER = "user"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
} 
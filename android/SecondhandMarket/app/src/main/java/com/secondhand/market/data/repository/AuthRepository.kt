package com.secondhand.market.data.respository

import com.yourname.secondhandmarket.data.model.*
import com.yourname.secondhandmarket.data.network.RetrofitClient
import com.yourname.secondhandmarket.utils.SharedPrefHelper

class AuthRepository(private val sharedPrefHelper: SharedPrefHelper) {

    private val api = RetrofitClient.instance

    suspend fun login(username: String, password: String): Result<User> {
        return try {
            val response = api.login(LoginRequest(username, password))
            if (response.code == 200 && response.data != null) {
                // 登录成功，保存token
                response.data.token?.let { sharedPrefHelper.saveToken(it) }
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(username: String, email: String, password: String): Result<User> {
        return try {
            val response = api.register(RegisterRequest(username, email, password))
            if (response.code == 200 && response.data != null) {
                Result.success(response.data)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPrefHelper.getToken() != null
    }

    fun logout() {
        sharedPrefHelper.clearToken()
    }
}
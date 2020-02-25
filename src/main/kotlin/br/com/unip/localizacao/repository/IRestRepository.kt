package br.com.unip.localizacao.repository

import kotlin.reflect.KClass

interface IRestRepository {

    fun <T : Any> get(uri: String, response: KClass<T>): T

    fun <T : Any> get(uri: String, response: KClass<T>, parametros: Map<String, Any>): T

    fun <T : Any> post(uri: String, request: Any, response: KClass<T>): T

    fun post(uri: String, request: Any): String
}
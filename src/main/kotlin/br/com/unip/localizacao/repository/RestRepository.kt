package br.com.unip.localizacao.repository

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import kotlin.reflect.KClass
import org.springframework.http.HttpMethod
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.http.RequestEntity.post


@Repository
class RestRepository(val mapper: ObjectMapper) : IRestRepository {

    private val restTemplate = RestTemplate()

    override fun <T : Any> post(uri: String, request: Any, response: KClass<T>): T {
        val res = post(uri, request)
        return mapper.readValue(res, response.java)
    }

    override fun post(uri: String, request: Any): String {
        val requestJson = mapper.writeValueAsString(request)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity(requestJson, headers)

        val res = restTemplate.postForEntity<String>(uri, entity, String::class)
        return res.body!!
    }

    override fun <T : Any> get(uri: String, response: KClass<T>): T {
        return restTemplate.getForEntity(uri, response.java).body!!
    }


    override fun <T : Any> get(uri: String, response: KClass<T>, parametros: Map<String, Any>): T {
        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE)

        val builder = UriComponentsBuilder.fromHttpUrl(uri)

        parametros.forEach { (key, value) -> builder.queryParam(key, value) }

        val entity = HttpEntity<Any>(headers)
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, response.java).body!!
    }
}
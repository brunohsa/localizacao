package br.com.unip.localizacao.dto

class EnderecoDTO(val cep: String,
                  val bairro: String,
                  val cidade: String,
                  val estado: String,
                  val latitude: Double,
                  val longitude: Double)
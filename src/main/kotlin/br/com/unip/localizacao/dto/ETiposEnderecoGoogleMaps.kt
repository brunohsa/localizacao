package br.com.unip.localizacao.dto

enum class ETiposEnderecoGoogleMaps(val valor: String) {

    CEP("postal_code"),
    BAIRRO("sublocality"),
    CIDADE("administrative_area_level_2"),
    ESTADO("administrative_area_level_1"),
    PAIS("country")
}
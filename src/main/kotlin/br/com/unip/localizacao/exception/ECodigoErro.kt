package br.com.unip.localizacao.exception

enum class ECodigoErro {

    ERRO_INESPERADO("000"),
    TOKEN_EXPIRADO("001"),
    TOKEN_INVALIDO("002"),
    CONEXAO_RECUSADA("003"),
    ACESSO_NEGADO("004"),
    CAMPO_OBRIGATORIO("005"),
    PARAMETRO_INVALIDO("006"),
    CAMPO_DEVE_SER_NUMERICO("007"),
    CEP_INVALIDO("008");

    val codigo: String

    constructor(codigo: String) {
        this.codigo = codigo
    }
}
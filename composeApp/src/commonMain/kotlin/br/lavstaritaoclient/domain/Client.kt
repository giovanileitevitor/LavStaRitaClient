package br.lavstaritaoclient.domain

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    var client_id: Int? = 0,
    var name: String,
    var phone: String,
    var cpf: String,
    var created_at: String? = null
)

fun emptyClient(): Client {
    return Client(name = "", phone = "", cpf = "")
}
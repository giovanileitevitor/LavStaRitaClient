package br.lavstaritaoclient.data.remoteRepository

import br.lavstaritaoclient.domain.Client
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class RemoteRepositoryImpl(
    private val httpClient: HttpClient
) {

    private val baseUrlClient = "https://vsayvsqrfwxnncpatamg.supabase.co/rest/v1/TB_CLIENT"
    private val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZzYXl2c3FyZnd4bm5jcGF0YW1nIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjYwODQ5NTIsImV4cCI6MjA4MTY2MDk1Mn0.HA7-1m3E1Qi3hMYG6ObSyYKS2-MGikb3wf6zYDqRw8I"

    /**
     * Consulta a API do Supabase para verificar se o documento (CPF/CNPJ) existe.
     * Retorna o objeto Client caso exista, ou uma falha caso contrário.
     */
    suspend fun checkDocumentExists(cpfORcnpj: String): Result<Client> {
        return try {
            val response: HttpResponse = httpClient.get(baseUrlClient) {
                parameter("cpf", "eq.$cpfORcnpj")
                header("apikey", apiKey)
            }

            if (response.status.value == 200) {
                val clients: List<Client> = response.body()
                if (clients.isNotEmpty()) {
                    Result.success(clients.first())
                } else {
                    Result.failure(Exception("Cliente não encontrado"))
                }
            } else {
                Result.failure(Exception("Erro na API: ${response.status.value}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

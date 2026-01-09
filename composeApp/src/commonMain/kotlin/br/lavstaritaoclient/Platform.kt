package br.lavstaritaoclient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
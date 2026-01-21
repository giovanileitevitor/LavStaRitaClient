package br.lavstaritaoclient

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

const val APP_VERSION = "1.0.0"

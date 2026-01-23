package br.lavstaritaoclient.data.localRepository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalRepository(private val dataStore: DataStore<Preferences>) {
    private val nameKey = stringPreferencesKey("user_name")
    private val cpfCnpjKey = stringPreferencesKey("user_cpf_cnpj")

    suspend fun saveUserData(name: String, cpfCnpj: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
            preferences[cpfCnpjKey] = cpfCnpj
        }
    }

    val userName: Flow<String?> = dataStore.data.map { preferences ->
        preferences[nameKey]
    }

    val userCpfCnpj: Flow<String?> = dataStore.data.map { preferences ->
        preferences[cpfCnpjKey]
    }

    suspend fun clearData() {
        dataStore.edit { it.clear() }
    }
}

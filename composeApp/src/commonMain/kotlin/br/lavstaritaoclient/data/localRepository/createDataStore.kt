package br.lavstaritaoclient.data.localRepository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun createDataStore(context: Any? = null): DataStore<Preferences>

const val DATA_STORE_FILE_NAME = "lavstarita.preferences_pb"

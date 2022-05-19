package com.example.wallet.ui.util.preferences

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object ApplicationPreferencesSerializer : Serializer<ApplicationPreferencesModel> {

    override val defaultValue = ApplicationPreferencesModel()

    override suspend fun readFrom(input: InputStream) = try {
        Json.decodeFromString(
            deserializer = ApplicationPreferencesModel.serializer(),
            string = input.readBytes().decodeToString()
        )
    } catch (e: SerializationException) {
        e.printStackTrace()
        defaultValue
    }

    override suspend fun writeTo(t: ApplicationPreferencesModel, output: OutputStream) = output.write(
        Json.encodeToString(
            serializer = ApplicationPreferencesModel.serializer(),
            value = t
        ).encodeToByteArray()
    )
}
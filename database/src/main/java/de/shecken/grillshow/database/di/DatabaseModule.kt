package de.shecken.grillshow.database.di

import android.content.SharedPreferences
import android.util.Base64
import android.util.Base64.DEFAULT
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV
import androidx.security.crypto.EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
import androidx.security.crypto.MasterKey
import de.shecken.grillshow.database.AppDatabase
import de.shecken.grillshow.database.BuildConfig.DEBUG
import de.shecken.grillshow.database.DatabaseConstants.DATABASE_NAME
import de.shecken.grillshow.database.DatabaseConstants.SECURE_RANDOM_SIZE
import de.shecken.grillshow.database.DatabaseConstants.SHARED_PREFERENCES_NAME
import de.shecken.grillshow.database.DatabaseConstants.SP_ROOM_PASS_PHRASE
import net.sqlcipher.database.SupportFactory
import org.koin.dsl.module
import java.security.SecureRandom

val databaseModule = module {

    single { MasterKey(get()) }

    single {
        EncryptedSharedPreferences.create(
            get(),
            SHARED_PREFERENCES_NAME,
            get<MasterKey>(),
            AES256_SIV,
            AES256_GCM
        )
    }

    single { SupportFactory(getOrCreateRoomPassphrase(get())) }

    single {
        Room
            .databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME)
            .apply { if (!DEBUG) openHelperFactory(get<SupportFactory>()) }
            .build()
    }

    factory { get<AppDatabase>().recipeDao() }
    factory { get<AppDatabase>().categoryDao() }
}

private fun getOrCreateRoomPassphrase(sharedPreferences: SharedPreferences): ByteArray {
    val passPhrase = sharedPreferences
        .getString(SP_ROOM_PASS_PHRASE, null)
        ?: createRoomPassphrase()
            .also { sharedPreferences.edit().putString(SP_ROOM_PASS_PHRASE, it).apply() }
    return Base64.decode(passPhrase, DEFAULT)
}

private fun createRoomPassphrase(): String {
    val bytes = ByteArray(SECURE_RANDOM_SIZE)
    SecureRandom().nextBytes(bytes)
    return Base64.encodeToString(bytes, DEFAULT)
}

package de.shecken.grillshow.database

internal object DatabaseConstants {

    const val DATABASE_NAME = "app_database"
    const val DATABASE_VERSION = 5

    const val SHARED_PREFERENCES_NAME = "preferences"
    const val SP_ROOM_PASS_PHRASE = "room_pass_phrase"
    const val SECURE_RANDOM_SIZE = 256

    const val TABLE_RECIPE_ENTITY = "RecipeEntity"
    const val TABLE_CATEGORY_ENTITY = "CategoryEntity"

    const val COLUMN_UPLOADED_AT = "uploadedAt"
    const val COLUMN_ID = "id"
}

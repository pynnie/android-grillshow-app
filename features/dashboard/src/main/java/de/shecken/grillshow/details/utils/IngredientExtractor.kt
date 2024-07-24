package de.shecken.grillshow.details.utils

interface IngredientExtractor {

    /**
     * Extracts all ingredients from a text
     *
     * @param text to extract the ingredients from
     * @return list of ingredients
     */
    fun extractIngredientsFromText(text: String): List<String>
}
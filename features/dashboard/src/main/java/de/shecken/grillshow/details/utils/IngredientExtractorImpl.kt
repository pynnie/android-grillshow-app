package de.shecken.grillshow.details.utils


class IngredientExtractorImpl : IngredientExtractor {

    override fun extractIngredientsFromText(text: String): List<String> =
        text.lines()
            .filter { it.startsWith(INGREDIENT_PREFIX, ignoreCase = true) }
            .map { ingredient ->
                ingredient.replaceFirst(INGREDIENT_PREFIX, "").trim()
            }

    companion object {
        private const val INGREDIENT_PREFIX = "-"
    }
}
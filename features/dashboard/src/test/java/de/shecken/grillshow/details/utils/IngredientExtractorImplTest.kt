package de.shecken.grillshow.details.utils

import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

internal class IngredientExtractorImplTest {

    private var underTest = IngredientExtractorImpl()

    private val realText =
        "Die Burger-Presse, der Klappo, der Striker und noch viel mehr heißer BBQ-Stuff!\n" +
                "\n" +
                "Freunde - es wird mal wieder Zeit für feinste Spieße vom Klappo! Wir machen schöne Köfte aus Rind- und Lammhackfleisch und basteln uns daraus etwas, das wir einfach mal „Köfte Dogs“ nennen. Der türkische Hot Dog, wenn Ihr so wollt … Auf jeden Fall mehr als reinballernswert!\n" +
                "\n" +
                "Das braucht Ihr:\n" +
                "- ca. 400 g Rinderhack\n" +
                "- ca. 400 g Lammhack\n" +
                "- 2 Eier\n" +
                "- Johnny’s Greek Spice\n" +
                "- Johnny’s Pommes-Salz\n" +
                "- Johnny’s geschrotete Habanero\n" +
                "\n" +
                "zum Belegen:\n" +
                "- türkisches Fladenbrot\n" +
                "- Grill-Paprika\n" +
                "- Zwiebeln(fein gewürfelt)\n" +
                "\n" +
                "\n" +
                "Amazon-Affiliate-Link - die Grillshow supporten \uD83D\uDC4A\uD83C\uDFFB\uD83D\uDC4A\uD83C\uDFFB"

    @Test
    fun extractIngredientsFromText() {
        // when
        val result = underTest.extractIngredientsFromText(realText)
        // then
        assert(result.isNotEmpty())
        assertTrue(result.contains("ca. 400 g Rinderhack"))
        assert(result.contains("ca. 400 g Lammhack"))
        assert(result.contains("2 Eier"))
        assert(result.contains("Johnny’s Greek Spice"))
        assert(result.contains("Johnny’s Pommes-Salz"))
        assert(result.contains("Johnny’s geschrotete Habanero"))
        assert(result.contains("türkisches Fladenbrot"))
        assert(result.contains("Grill-Paprika"))
        assert(result.contains("Zwiebeln(fein gewürfelt)"))
    }
}
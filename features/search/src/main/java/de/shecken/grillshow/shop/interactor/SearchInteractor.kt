package de.shecken.grillshow.shop.interactor

import de.shecken.grillshow.shop.vo.SearchResultVo
import kotlinx.coroutines.flow.Flow

interface SearchInteractor {

    /**
     * Search for recipes
     *
     * @param query the search query
     */
    fun searchForRecipes(query: String): Flow<List<SearchResultVo>>
}
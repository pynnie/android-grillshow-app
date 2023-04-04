package de.shecken.grillshow.details.interactor

import de.shecken.grillshow.repository.details.DetailsRepository

class DetailsInteractorImpl(private val detailsRepository: DetailsRepository) :
    DetailsInteractor {

    override fun getRecipeDetails(id: String) = detailsRepository.recipeDetailsById(id)
}
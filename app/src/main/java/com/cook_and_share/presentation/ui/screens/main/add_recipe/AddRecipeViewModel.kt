package com.cook_and_share.presentation.ui.screens.main.add_recipe

//@HiltViewModel
//class AddRecipeViewModel @Inject constructor(
//    private val repository: FirestoreRepository
//): ViewModel(){
//
//    private val _createRecipeFlow = MutableStateFlow<Resource<Void>?>(null)
//    val createRecipeFlow: StateFlow<Resource<Void>?> = _createRecipeFlow
//    fun createRecipe(
//        title: String,
//        imageUrl: String,
//        tags: List<String>,
//        ingredients: List<String>,
//        recipe: String
//    ) = viewModelScope.launch {
//        _createRecipeFlow.value = Resource.Loading
//        val result = repository.createRecipe(title, imageUrl, tags, ingredients, recipe)
//        _createRecipeFlow.value = result
//    }
//}
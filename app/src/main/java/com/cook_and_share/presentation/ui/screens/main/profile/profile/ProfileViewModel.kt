package com.cook_and_share.presentation.ui.screens.main.profile.profile

//@HiltViewModel
//class ProfileViewModel @Inject constructor(
//    private val repository: FirestoreRepository,
//): ViewModel(){
//
//    private val currentUser: FirebaseUser?
//        get() = repository.currentUser
//
//    val user = mutableStateOf(User())
//
//    private fun getUserInfo() = viewModelScope.launch {
//        user.value = repository.getUserInfo()
//    }
//    init{
//        if(currentUser != null){
//            getUserInfo()
//        }
//    }
//}
package com.cook_and_share.data.repository

import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.domain.repository.AuthRepository
import com.cook_and_share.domain.repository.StorageRepository
import com.cook_and_share.presentation.util.Constants.COLLECTION_NAME_RECIPES
import com.cook_and_share.presentation.util.Constants.COLLECTION_NAME_USERS
import com.cook_and_share.presentation.util.Constants.IS_DAILY_FIELD
import com.cook_and_share.presentation.util.Constants.USER_ID_FIELD
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: AuthRepository
) : StorageRepository {

    override val myRecipes: Flow<List<Recipe>>
        get() = callbackFlow {
            val listener = database.reference.child(COLLECTION_NAME_RECIPES)
                .orderByChild(USER_ID_FIELD)
                .equalTo(auth.currentUserId)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recipes = snapshot.children.mapNotNull { it.getValue<Recipe>() }
                        trySend(recipes)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })

            awaitClose {
                database.reference.child(COLLECTION_NAME_RECIPES).removeEventListener(listener)
            }
        }

    override val recipes: Flow<List<Recipe>>
        get() = callbackFlow {
            val listener = database.reference.child(COLLECTION_NAME_RECIPES)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recipes = snapshot.children.mapNotNull { it.getValue<Recipe>() }
                        trySend(recipes)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })
            awaitClose {
                database.reference.child(COLLECTION_NAME_RECIPES).removeEventListener(listener)
            }
        }

    override val dailyRecipes: Flow<List<Recipe>>
        get() = callbackFlow {
            val listener = database.reference.child(COLLECTION_NAME_RECIPES)
                .orderByChild(IS_DAILY_FIELD)
                .equalTo(true)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recipes = snapshot.children.mapNotNull { it.getValue<Recipe>() }
                        trySend(recipes)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })

            awaitClose {
                database.reference.child(COLLECTION_NAME_RECIPES).removeEventListener(listener)
            }
        }

    override val likedRecipes: Flow<List<Recipe>>
        get() = callbackFlow {
            val listener = database.reference.child(COLLECTION_NAME_RECIPES)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recipes = snapshot.children.mapNotNull { it.getValue<Recipe>() }
                            .filter { it.likes.contains(auth.currentUserId) }
                        trySend(recipes)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })

            awaitClose {
                database.reference.child(COLLECTION_NAME_RECIPES).removeEventListener(listener)
            }
        }

    override suspend fun searchProfiles(query: String, fieldName: String): Flow<List<Profile>> {
        return callbackFlow {
            val listener = database.reference.child(COLLECTION_NAME_USERS)
                .orderByChild(fieldName)
                .startAt(query)
                .endAt(query + "\uf8ff")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val profiles = snapshot.children.mapNotNull { it.getValue<Profile>() }
                        trySend(profiles)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })

            awaitClose {
                database.reference.child(COLLECTION_NAME_USERS).removeEventListener(listener)
            }
        }
    }

    override suspend fun searchRecipes(query: String, fieldName: String): Flow<List<Recipe>> {
        return callbackFlow {
            val listener = database.reference.child(COLLECTION_NAME_RECIPES)
                .orderByChild(fieldName)
                .startAt(query)
                .endAt(query + "\uf8ff")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val recipes = snapshot.children.mapNotNull { it.getValue<Recipe>() }
                        trySend(recipes)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        close(error.toException())
                    }
                })

            awaitClose {
                database.reference.child(COLLECTION_NAME_RECIPES).removeEventListener(listener)
            }
        }
    }

    override suspend fun getRecipe(recipeId: String): Recipe? =
        database.reference.child(COLLECTION_NAME_RECIPES).child(recipeId).get().await()
            .getValue<Recipe>()

    override suspend fun save(recipe: Recipe) {
        val newRecipeRef = database.reference.child(COLLECTION_NAME_RECIPES).push()
        val updatedTask = recipe.copy(id = newRecipeRef.key ?: "", userID = auth.currentUserId)
        newRecipeRef.setValue(updatedTask).await()
    }

    override suspend fun update(recipe: Recipe) {
        database.reference.child(COLLECTION_NAME_RECIPES).child(recipe.id).setValue(recipe).await()
    }

    override suspend fun delete(recipeId: String) {
        database.reference.child(COLLECTION_NAME_RECIPES).child(recipeId).removeValue().await()
    }
}
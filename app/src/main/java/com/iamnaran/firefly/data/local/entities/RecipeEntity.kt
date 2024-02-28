package com.iamnaran.firefly.data.local.entities

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("recipe")
@Immutable
data class RecipeEntity(
    @PrimaryKey
    @ColumnInfo("id")
    @SerializedName("id")
    val id: Long,

    @ColumnInfo("name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo("ingredients")
    @SerializedName("ingredients")
    val ingredients: List<String>,

    @ColumnInfo("instructions")
    @SerializedName("instructions")
    val instructions: List<String>,

    @ColumnInfo("prepTimeMinutes")
    @SerializedName("prepTimeMinutes")
    val prepTimeMinutes: Long,

    @ColumnInfo("cookTimeMinutes")
    @SerializedName("cookTimeMinutes")
    val cookTimeMinutes: Long,

    @ColumnInfo("servings")
    @SerializedName("servings")
    val servings: Long,

    @ColumnInfo("difficulty")
    @SerializedName("difficulty")
    val difficulty: String,

    @ColumnInfo("cuisine")
    @SerializedName("cuisine")
    val cuisine: String,

    @ColumnInfo("caloriesPerServing")
    @SerializedName("caloriesPerServing")
    val caloriesPerServing: Long,

    @ColumnInfo("tags")
    @SerializedName("tags")
    val tags: List<String>,

    @ColumnInfo("userId")
    @SerializedName("userId")
    val userId: Long,

    @ColumnInfo("image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo("rating")
    @SerializedName("rating")
    val rating: Double,

    @ColumnInfo("reviewCount")
    @SerializedName("reviewCount")
    val reviewCount: Long,

    @ColumnInfo("mealType")
    @SerializedName("mealType")
    val mealType: List<String>,
)
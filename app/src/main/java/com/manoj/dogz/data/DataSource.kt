package com.manoj.dogz.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.manoj.dogz.R


data class Dog(
    @DrawableRes
    val imageResId: Int,
    @StringRes
    val name: Int,
    val age: Int,
    @StringRes
    val hobbies: Int
)

val dogs = listOf(
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_1, 2, R.string.dog_description_1),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_2, 3, R.string.dog_description_2),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_3, 4, R.string.dog_description_3),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_4, 5, R.string.dog_description_4),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_5, 6, R.string.dog_description_5),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_6, 7, R.string.dog_description_6),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_7, 8, R.string.dog_description_7),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_8, 9, R.string.dog_description_8),
    Dog(R.drawable.ic_launcher_foreground, R.string.dog_name_9, 10, R.string.dog_description_9)
)
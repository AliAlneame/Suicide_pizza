//package com.example.pizza
//
//import android.annotation.SuppressLint
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.animateColorAsState
//import androidx.compose.animation.animateContentSize
//import androidx.compose.animation.core.animateDp
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.animation.core.updateTransition
//import androidx.compose.animation.expandIn
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.shrinkOut
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Arrangement.Center
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.zIndex
//import com.example.pizza.ui.theme.Green
//import kotlin.random.Random
//
//@SuppressLint("RememberReturnType")
//@Composable
//fun PizzaScreen() {
//    val drawableList = listOf(
//        R.drawable.broccoli_1,
//        R.drawable.basil_2,
//        R.drawable.mushroom_1,
//        R.drawable.onion_1,
//        R.drawable.sausage_1
//    )
//
//    val ingredientsOnPizza = remember { mutableStateListOf<Int>() }
//    val selectedDrawables = remember { mutableStateListOf(*Array(drawableList.size) { false }) }
//
//    Column(Modifier.background(Color.LightGray)) {
//        // Pizza and ingredients
//        Box(modifier = Modifier
//            .weight(1f)
//            .fillMaxWidth()
//        ) {
//            // Your pizza image here
//
//            ingredientsOnPizza.forEach { ingredient ->
//                Ingredient(
//                    ingredient = ingredient,
//                    total = ingredientsOnPizza.size,
//                    index = ingredientsOnPizza.indexOf(ingredient),
//                    zIndex = 1f,
//                    selected = selectedDrawables[drawableList.indexOf(ingredient)],
//                    pizzaWidth = 600f, // replace with actual pizza width
//                    pizzaHeight = 600f // replace with actual pizza height
//                )
//            }
//        }
//
//        LazyRow(
//            modifier = Modifier
//                .padding(horizontal = 16.dp)
//                .padding(bottom = 60.dp),
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            itemsIndexed(drawableList) { index, drawableId ->
//                val isSelected = selectedDrawables[index]
//                val backgroundColor by animateColorAsState(
//                    targetValue = if (isSelected) Color.Green else Color.White
//                )
//
//                Box(
//                    modifier = Modifier
//                        .size(if (isSelected) 100.dp else 89.dp)
//                        .animateContentSize()
//                        .background(
//                            color = backgroundColor,
//                            shape = CircleShape
//                        )
//                        .clickable(
//                            onClick = {
//                                selectedDrawables[index] = !selectedDrawables[index]
//                                if (selectedDrawables[index]) {
//                                    // Add ingredient onto the pizza
//                                    ingredientsOnPizza.add(drawableId)
//                                } else {
//                                    // Remove ingredient from the pizza
//                                    ingredientsOnPizza.remove(drawableId)
//                                }
//                            }
//                        )
//                ) {
//                    Image(
//                        painter = painterResource(id = drawableId),
//                        contentDescription = "My Image",
//                        modifier = Modifier
//                            .size(48.dp)
//                            .align(Alignment.Center),
//                    )
//                }
//            }
//        }
//    }
//}
//@Composable
//fun Ingredient(ingredient: Int, total: Int, index: Int, zIndex: Float, selected: Boolean, pizzaWidth: Float, pizzaHeight: Float) {
//    val transition = updateTransition(targetState = selected, label = "ingredientTransition")
//    val size by transition.animateDp(label = "ingredientSizeTransition") { isSelected ->
//        if (isSelected) 100.dp else 89.dp
//    }
//    val randomX by animateFloatAsState(
//        targetValue = if (selected) Random.nextFloat() * pizzaWidth else 0f,
//        animationSpec = tween(durationMillis = 500)
//    )
//    val randomY by animateFloatAsState(
//        targetValue = if (selected) Random.nextFloat() * (pizzaHeight / 2) else -200f,
//        animationSpec = tween(durationMillis = 500)
//    )
//
//    Image(
//        painter = painterResource(id = ingredient),
//        contentDescription = "Ingredient",
//        modifier = Modifier
//            .zIndex(zIndex) // modify as needed
//            .offset(x = randomX.dp, y = randomY.dp)
//            .size(size)
//        // additional modifiers to position/rotate the ingredient
//    )
//}
//@Preview
//@Composable
//fun PizzaScreenPreview() {
//    PizzaScreen()
//}
package com.example.pizza

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.offset


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizza.R
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Your code...

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun MyPager(
    modifier: Modifier = Modifier,
    plateImages: List<Int>,
    pizzaToppings: SnapshotStateList<MutableList<Int>>,
    pagerState: PagerState,
    pizzaSizes: List<MutableState<Dp>>,
    recomposeTrigger: MutableState<Boolean>
) {
    val A = recomposeTrigger.value

    HorizontalPager(
        modifier = modifier, state = pagerState, pageCount = plateImages.size
    ) { page ->
        val pizzaSize by animateDpAsState(targetValue = pizzaSizes[page].value)

        Image(
            modifier = Modifier.size(pizzaSize),
            painter = painterResource(id = plateImages[page]),
            contentDescription = "plate"
        )

        pizzaToppings[page].forEach { topping ->
            // We use a remembered Animatable to manage each topping's animation
            val toppingScale = remember { Animatable(3f) }
            val toppingOffsetY = remember { Animatable(0f) }

            // Trigger the animation whenever a topping is selected
            if (pizzaToppings[page].contains(topping)) {
                LaunchedEffect(topping) { // Effect depends on the topping
                    // Animate the scale from 3 to 1 over 500 milliseconds
                    toppingScale.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 500)
                    )
                    // Simultaneously animate the Y offset from -100 to 0 to give a dropping effect
                    toppingOffsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            }

            Image(
                modifier = Modifier
                    .size(pizzaSize - 60.dp)
                    .scale(toppingScale.value) // Apply the scale
                    .offset(y = toppingOffsetY.value.dp), // Apply the Y offset
                painter = painterResource(id = topping),
                contentDescription = "topping"
            )
        }
    }
}
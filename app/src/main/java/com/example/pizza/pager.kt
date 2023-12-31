package com.example.pizza


import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp



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
             val toppingScale = remember { Animatable(3f) }
            val toppingOffsetY = remember { Animatable(0f) }

             if (pizzaToppings[page].contains(topping)) {
                LaunchedEffect(topping) {
                     toppingScale.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 500)
                    )
                     toppingOffsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 500)
                    )
                }
            }

            Image(
                modifier = Modifier
                    .size(pizzaSize - 60.dp)
                    .scale(toppingScale.value)
                    .offset(y = toppingOffsetY.value.dp),
                painter = painterResource(id = topping),
                contentDescription = "topping"
            )
        }
    }
}
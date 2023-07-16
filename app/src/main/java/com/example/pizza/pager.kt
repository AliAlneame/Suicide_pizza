package com.example.pizza

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues


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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.Dp


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
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
        modifier = modifier,
        state = pagerState,
        pageCount = plateImages.size
    ) { page ->
        Image(
            modifier = Modifier.size(pizzaSizes[page].value),
            painter = painterResource(id = plateImages[page]),
            contentDescription = "plate"
        )
        pizzaToppings[page].forEach { topping ->
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = topping),
                contentDescription = "topping"
            )
        }
    }
}

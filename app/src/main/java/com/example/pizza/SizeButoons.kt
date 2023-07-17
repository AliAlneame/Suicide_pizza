package com.example.pizza

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SizeButtons(
    modifier: Modifier, pagerState: PagerState, pizzaSizes: List<MutableState<Dp>>
) {
    var selectedButtonIndex by remember { mutableStateOf(2) } // Initially set to index 2 (L button)

    Box(modifier = Modifier.animateContentSize()) {

        CircleCard(modifier = modifier, selectedButtonIndex = selectedButtonIndex)

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "S",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 12.dp)
                    .align(CenterVertically)
                    .clickable {
                        pizzaSizes[pagerState.currentPage].value = 180.dp
                        selectedButtonIndex = 0
                    })
            Text(
                text = "M",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(48.dp)
                    .align(CenterVertically)
                    .padding(top = 12.dp)
                    .clickable {
                        pizzaSizes[pagerState.currentPage].value = 200.dp
                        selectedButtonIndex = 1
                    })


            Text(
                text = "L",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 12.dp)
                    .align(CenterVertically)
                    .clickable {
                        pizzaSizes[pagerState.currentPage].value = 230.dp
                        selectedButtonIndex = 2

                    }
            )

        }
    }
}

@Composable
fun CircleCard(selectedButtonIndex: Int, modifier: Modifier) {
    val circleOffset by animateDpAsState(
        targetValue = when (selectedButtonIndex) {
            0 -> 0.dp
            1 -> 72.dp
            else -> 144.dp
        }, animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = modifier
            .size(48.dp)
            .offset(x = circleOffset)
            .shadow(elevation = 1.dp, shape = CircleShape)
    ) {

    }
}

package com.example.pizza

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Row


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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SizeButtons(
    modifier: Modifier,
    pagerState: PagerState,
    pizzaSizes: List<MutableState<Dp>>
) {
    Row(
        modifier = modifier,

        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(color = Color.White, shape = CircleShape)
                .clickable { pizzaSizes[pagerState.currentPage].value = 180.dp }
        ) {
            Text(text = "S")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(color = Color.White, shape = CircleShape)
                .clickable { pizzaSizes[pagerState.currentPage].value = 200.dp }
        ) {
            Text(text = "M")
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(color = Color.White, shape = CircleShape)
                .clickable { pizzaSizes[pagerState.currentPage].value = 230.dp }
        ) {
            Text(text = "L")
        }
    }

}
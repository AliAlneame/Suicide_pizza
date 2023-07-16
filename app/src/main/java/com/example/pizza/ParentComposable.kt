package com.example.pizza

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

//fun ParentComposable(modifier: Modifier = Modifier,pagerState: PagerState, plateImages: List<Int>,  pizzaSizes: List<MutableState<Dp>>) {
//
//
//    Column() {
//
//        MyPager(modifier, plateImages, pagerState, pizzaSizes)
//        SizeButtons(modifier,pagerState, pizzaSizes)
//
//    }
//
//}

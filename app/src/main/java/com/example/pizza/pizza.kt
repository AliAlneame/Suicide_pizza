package com.example.pizza

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza.ui.theme.Brown
import com.example.pizza.ui.theme.Green

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun pizzaContent() {
    val plateImages = listOf(
        R.drawable.bread_1,
        R.drawable.bread_2,
        R.drawable.bread_3,
        R.drawable.bread_4,
        R.drawable.bread_5,

        )
    val toppings = remember {
        mutableStateListOf(
            R.drawable.basil_group,
            R.drawable.brocoli_group,
            R.drawable.mushroom_group,
            R.drawable.onion_group,
            R.drawable.susage_group
        )
    }

    val pizzaSizes =
        remember { mutableStateListOf(*Array(plateImages.size) { mutableStateOf(230.dp) }) }
    val pagerState = rememberPagerState()

    val pizzaToppings =
        remember { mutableStateListOf(*Array(plateImages.size) { mutableListOf<Int>() }) }

    val selectedDrawables =
        remember { mutableStateListOf(*Array(plateImages.size) { mutableStateListOf(*Array(toppings.size) { false }) }) }
    var interactionSource = remember { MutableInteractionSource() }
    val recomposeTrigger = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

            ) {
                IconButton(modifier = Modifier.padding(top = 45.dp, start = 32.dp), onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = "back button",
                    )

                }
                Text(
                    modifier = Modifier
                        .padding(top = 45.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    text = "Pizza",
                )
                IconButton(modifier = Modifier.padding(top = 45.dp, start = 32.dp), onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_favorite_24),
                        contentDescription = "fav",
                    )

                }

            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(270.dp)
                        .align(Center),
                    painter = painterResource(id = R.drawable.plate),
                    contentDescription = "plate"
                )

                MyPager(
                    Modifier.align(Center),
                    plateImages,
                    pizzaToppings,
                    pagerState,
                    pizzaSizes,
                    recomposeTrigger
                )
            }
            SizeButtons(
                Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                pagerState,
                pizzaSizes
            )



            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(toppings) { index, drawableId ->
                    val isSelected = selectedDrawables[pagerState.currentPage][index]

                    Box(
                        modifier = Modifier
                            .size(89.dp)
                            .background(
                                color = if (isSelected) Green else Color.White, shape = CircleShape
                            )
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    selectedDrawables[pagerState.currentPage][index] = !isSelected
                                    if (!isSelected) {
                                        pizzaToppings[pagerState.currentPage].add(drawableId)
                                    } else {
                                        pizzaToppings[pagerState.currentPage].remove(drawableId)
                                    }
                                    recomposeTrigger.value = !recomposeTrigger.value
                                })
                    ) {
                        Image(
                            painter = painterResource(id = drawableId),
                            contentDescription = "My Image",
                            modifier = Modifier
                                .size(48.dp)
                                .align(Center),
                        )
                    }
                }
            }


        }
        Spacer(modifier = Modifier.padding(top = 30.dp))
        Button(
            onClick = { }, colors = ButtonDefaults.buttonColors(
                containerColor = Brown, contentColor = White
            ), shape = RoundedCornerShape(24.dp), modifier = Modifier

                .padding(bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_credit_card_24),
                contentDescription = null,
                tint = Color(0xFFf5ede4),
                modifier = Modifier
                    .size(28.dp)
                    .padding(horizontal = 4.dp)
            )
            Text(text = "Add to cart")
        }
    }

}
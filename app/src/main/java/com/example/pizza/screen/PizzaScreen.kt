package com.example.pizza.screen


import android.annotation.SuppressLint
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pizza.R
import com.example.pizza.ui.theme.Brown
import com.example.pizza.ui.theme.Green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PizzaScreen(
    viewModel: PizzaScreenViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    PizzaContent(state, viewModel::toggleTopping, viewModel::changePizzaSize)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PizzaContent(
    state: PizzaScreenState,
    onToppingToggled: (Int, Int) -> Unit,
    onPizzaSizeSelected: (Int, Dp) -> Unit
) {
    val plateImages = state.plateImages
    val toppings = state.toppings
    val toppingssingle = state.toppingssingle
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

                PizzaPager(
                    Modifier.align(Center),
                    plateImages,
                    state,
                    pagerState,
                    pizzaSizes

                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                SizeButtons(
                    Modifier
                        .align(Center)
                        .padding(top = 20.dp),
                    pagerState,
                    pizzaSizes
                )
            }


            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 60.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(toppingssingle) { index, drawableId ->
                    val isSelected = state.selectedToppings[pagerState.currentPage][index]


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
                                    onToppingToggled(pagerState.currentPage, index)
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
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { }, colors = ButtonDefaults.buttonColors(
                    containerColor = Brown, contentColor = White
                ), shape = RoundedCornerShape(24.dp), modifier = Modifier
                    .align(CenterHorizontally)
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

}

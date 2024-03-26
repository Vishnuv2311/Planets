package dev.vishnuv.planets.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import dev.vishnuv.planets.R
import dev.vishnuv.planets.model.PlanetInfo
import dev.vishnuv.planets.model.planets
import dev.vishnuv.planets.ui.theme.gradientEndColor
import dev.vishnuv.planets.ui.theme.gradientStartColor
import dev.vishnuv.planets.ui.theme.primaryTextColor
import dev.vishnuv.planets.ui.theme.secondaryTextColor
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage(
    goToDetails: (PlanetInfo) -> Unit
) {
    val planets = planets

    val pagerState = rememberPagerState(pageCount = { planets.size })

    Scaffold(Modifier.background(color = gradientEndColor)) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(gradientStartColor, gradientEndColor)
                    )
                )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(
                    text = "Explore",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Solar System",
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Color(0x7cdbf1ff),
                            fontWeight = FontWeight.W500
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.drop_down_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(12.dp)
                    )
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                ) { page ->
                    val planet = planets[page]
                    Box(Modifier
                        .padding(horizontal = 32.dp)
                        .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }) {
                        Column {
                            Spacer(modifier = Modifier.height(100.dp))
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(32.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                elevation = CardDefaults.cardElevation(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(32.dp),
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Spacer(modifier = Modifier.height(100.dp))
                                    Text(
                                        text = planet.name,
                                        style = TextStyle(
                                            fontSize = 40.sp,
                                            fontWeight = FontWeight.W900,
                                            textAlign = TextAlign.Left,
                                            color = Color(0xff47455f),
                                        )
                                    )
                                    Text(
                                        text = "Solar System",
                                        style = TextStyle(
                                            fontSize = 23.sp,
                                            color = primaryTextColor,
                                            fontWeight = FontWeight.W400,
                                            textAlign = TextAlign.Left
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(32.dp))
                                    Row {
                                        Text(
                                            text = "Know more",
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                color = secondaryTextColor,
                                                fontWeight = FontWeight.W400,
                                                textAlign = TextAlign.Left
                                            )
                                        )
                                        Icon(
                                            Icons.Rounded.ArrowForward,
                                            contentDescription = null,
                                            tint = secondaryTextColor,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Image(
                            painter = painterResource(id = planet.iconImage),
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(300.dp)
                                .clickable { goToDetails(planet) },
                            contentDescription = null,
                        )
                    }
                }

                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.Yellow else Color.White
                        Box(
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(10.dp)
                        )
                    }
                }

            }
        }
    }
}


@Preview
@Composable
private fun HomePagePreview() {
    HomePage {}
}

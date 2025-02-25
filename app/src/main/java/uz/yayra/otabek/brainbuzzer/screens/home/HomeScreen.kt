package uz.yayra.otabek.brainbuzzer.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.brainbuzzer.R
import uz.yayra.otabek.brainbuzzer.data.model.GenreData
import uz.yayra.otabek.brainbuzzer.screens.game.AlertDialogExample1
import uz.yayra.otabek.brainbuzzer.ui.theme.MainMagenta


object HomeScreen : Screen {
    private fun readResolve(): Any = HomeScreen

    @Composable
    override fun Content() {
        val viewModel: HomeContracts.ViewModel = getViewModel<HomeViewModel>()
        val uiState = viewModel.collectAsState()
        HomeScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContent(
    uiState: State<HomeContracts.UiState>, onEventDispatcher: (HomeContracts.Intent) -> Unit,
) {
    val openAlertDialog = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        onEventDispatcher(HomeContracts.Intent.Init)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainMagenta)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Hello, ${uiState.value.name}!",
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.outfit_regular)),
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .combinedClickable(onLongClick = {
                        openAlertDialog.value = true
                    }, onClick = {})
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.White)
                    .padding(4.dp)
            ) {
                Text(
                    text = uiState.value.money,
                    color = Color.Black,
                    modifier = Modifier.padding(4.dp),
                    fontFamily = FontFamily(Font(R.font.outfit_light))
                )
                Image(
                    painter = painterResource(id = R.drawable.diamond),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MainMagenta)
                        .padding(4.dp)
                )
            }
        }
        Text(
            text = "What would you like to play\ntoday?",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 10.dp),
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.outfit_medium))
        )

        LazyRow {
            items(uiState.value.quizGenres) {
                QuizGenre(it, HomeContracts.Intent.MoveToGame(it.genreEnum), onEventDispatcher)
            }
        }
        Text(text = "New sections are coming soon...", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White)
    }
    if (openAlertDialog.value) {
        AlertDialogExample1(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
            },
            dialogTitle = "BrainBuzzer",
            dialogText = "I made this application as homework when I studied at GITA Academy. Knowledge I used: Compose, Hilt, Navigation. The application is under development. Wait for the update!",
            icon = R.drawable.info
        )
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MainMagenta)
    systemUiController.setNavigationBarColor(MainMagenta)
}

@Composable
fun QuizGenre(data: GenreData, intent: HomeContracts.Intent, onEventDispatcher: (HomeContracts.Intent) -> Unit) {
    var isClickable by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier
        .size(240.dp)
        .padding(20.dp)
        .clip(shape = RoundedCornerShape(16.dp))
        .background(color = Color.White)
        .clickable {
            if (isClickable) {
                isClickable = false
                coroutineScope.launch {
                    onEventDispatcher(intent)
                    delay(500)
                    isClickable = true
                }
            }
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = data.image), contentDescription = null, contentScale = ContentScale.Inside
            )
        }
        Text(
            text = data.name,
            fontFamily = FontFamily(Font(R.font.outfit_regular)),
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 10.dp, bottom = 4.dp)
        )
        Text(
            text = "${data.questionCount} Questions",
            fontFamily = FontFamily(Font(R.font.outfit_regular)),
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
        )
    }
}
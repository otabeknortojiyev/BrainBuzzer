package uz.yayra.otabek.brainbuzzer.screens.game

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import uz.yayra.otabek.brainbuzzer.ui.theme.MainMagenta
import uz.yayra.otabek.brainbuzzer.utils.GenreEnum


class GameScreen(private val genre: GenreEnum) : Screen {

    @Composable
    override fun Content() {
        val viewModel: GameContract.ViewModel = getViewModel<GameViewModel>()
        val uiState = viewModel.collectAsState()
        viewModel::onEventDispatcher.invoke(GameContract.Intent.Init(genre))
        GameScreenContent(uiState, viewModel::onEventDispatcher, genre)
    }
}

@SuppressLint("ResourceAsColor")
@Composable
private fun GameScreenContent(
    uiState: State<GameContract.UiState>, onEventDispatcher: (GameContract.Intent) -> Unit, genre: GenreEnum,
) {
    var isClickable by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val openAlertDialog1 = remember {
        mutableStateOf(false)
    }
    val openAlertDialog2 = remember {
        mutableStateOf(false)
    }
    var selected = remember {
        mutableIntStateOf(-1)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainMagenta)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = painterResource(id = R.drawable.close),
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp))
                    .clickable {
                        openAlertDialog2.value = true
                    })
            LinearProgressIndicator(
                progress = (((uiState.value.index + 1).toFloat() / uiState.value.quizzes.size)),
                color = Color.White,
                modifier = Modifier
                    .height(8.dp)
                    .clip(RoundedCornerShape(16.dp)),
                backgroundColor = Color.LightGray
            )
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
        Image(
            painter = if (uiState.value.quizzes.isNotEmpty()) {
                painterResource(id = uiState.value.quizzes[uiState.value.index].image)
            } else {
                painterResource(id = R.drawable.ic_launcher_background)
            },
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 36.dp, bottom = 10.dp)
                .height(200.dp)
                .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(20.dp))
                .padding(4.dp),
            contentScale = ContentScale.Inside
        )
        Text(
            text = "Question ${uiState.value.index + 1} of ${uiState.value.quizzes.size}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = Color.LightGray,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
        Text(
            text = if (uiState.value.quizzes.isNotEmpty()) {
                uiState.value.quizzes[uiState.value.index].question
            } else {
                ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = Color.White,
            fontSize = 24.sp,
            lineHeight = 40.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium))
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = if (uiState.value.quizzes.isNotEmpty()) {
            uiState.value.quizzes[uiState.value.index].variant1
        } else {
            ""
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(
                color = when (uiState.value.var1) {
                    1 -> {
                        Color.White
                    }

                    0 -> {
                        Color.Green
                    }

                    else -> {
                        Color.Red
                    }
                }
            )
            .clickable {
                if (isClickable) {
                    isClickable = false
                    coroutineScope.launch {
                        if (uiState.value.index == uiState.value.quizzes.size - 1) openAlertDialog1.value = true
                        onEventDispatcher(GameContract.Intent.Check(1))
                        delay(500)
                        isClickable = true
                    }
                }
            }
            .padding(vertical = 20.dp), textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.montserrat_semibold)))
        Text(text = if (uiState.value.quizzes.isNotEmpty()) {
            uiState.value.quizzes[uiState.value.index].variant2
        } else {
            ""
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(
                color = when (uiState.value.var2) {
                    2 -> {
                        Color.White
                    }

                    0 -> {
                        Color.Green
                    }

                    else -> {
                        Color.Red
                    }
                }
            )
            .clickable {
                if (isClickable) {
                    isClickable = false
                    coroutineScope.launch {
                        if (uiState.value.index == uiState.value.quizzes.size - 1) openAlertDialog1.value = true
                        onEventDispatcher(GameContract.Intent.Check(2))
                        delay(500)
                        isClickable = true
                    }
                }
            }
            .padding(vertical = 20.dp), textAlign = TextAlign.Center, fontFamily = FontFamily(Font(R.font.montserrat_semibold)))
        Text(text = if (uiState.value.quizzes.isNotEmpty()) {
            uiState.value.quizzes[uiState.value.index].variant3
        } else {
            ""
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 36.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .background(
                    color = when (uiState.value.var3) {
                        3 -> {
                            Color.White
                        }

                        0 -> {
                            Color.Green
                        }

                        else -> {
                            Color.Red
                        }
                    }
                )
                .clickable {
                    if (isClickable) {
                        isClickable = false
                        coroutineScope.launch {
                            if (uiState.value.index == uiState.value.quizzes.size - 1) openAlertDialog1.value = true
                            onEventDispatcher(GameContract.Intent.Check(3))
                            delay(500)
                            isClickable = true
                        }
                    }
                }
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)))
    }
    if (openAlertDialog1.value) {
        AlertDialogExample1(onDismissRequest = {}, onConfirmation = {
            onEventDispatcher(GameContract.Intent.Back)
            openAlertDialog1.value = false
        }, dialogTitle = "BrainBuzzer", dialogText = "Correct answers: ${uiState.value.correct}", icon = R.drawable.leaderboard
        )
    }
    if (openAlertDialog2.value) {
        AlertDialogExample2(onDismissRequest = { openAlertDialog2.value = false }, onConfirmation = {
            openAlertDialog2.value = false
            openAlertDialog1.value = true
        }, dialogTitle = "BrainBuzzer", dialogText = "Do you really want to finish the quiz?", icon = R.drawable.info
        )
    }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    DisposableEffect(key1 = Unit) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                openAlertDialog2.value = true
            }
        }

        backDispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MainMagenta)
    systemUiController.setNavigationBarColor(MainMagenta)
}

@Composable
fun AlertDialogExample1(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: Int,
) {
    AlertDialog(icon = {
        Image(painter = painterResource(id = icon), contentDescription = null)
    }, title = {
        Text(text = dialogTitle, fontFamily = FontFamily(Font(R.font.montserrat_semibold)))
    }, text = {
        Text(text = dialogText, fontFamily = FontFamily(Font(R.font.montserrat_medium)))
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text("OK")
        }
    })
}

@Composable
fun AlertDialogExample2(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: Int,
) {
    AlertDialog(icon = {
        Image(painter = painterResource(id = icon), contentDescription = null)
    }, title = {
        Text(text = dialogTitle, fontFamily = FontFamily(Font(R.font.montserrat_semibold)))
    }, text = {
        Text(text = dialogText, fontFamily = FontFamily(Font(R.font.montserrat_medium)))
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text("OK")
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text("NO")
        }
    })
}


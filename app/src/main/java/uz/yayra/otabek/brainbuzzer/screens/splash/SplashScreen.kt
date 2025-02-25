package uz.yayra.otabek.brainbuzzer.screens.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.yayra.otabek.brainbuzzer.R
import uz.yayra.otabek.brainbuzzer.ui.theme.MainMagenta
import uz.yayra.otabek.brainbuzzer.utils.AppTextField


object SplashScreen : Screen {
    private fun readResolve(): Any = SplashScreen

    @Composable
    override fun Content() {
        val viewModel: SplashContract.ViewModel = getViewModel<SplashViewModel>()
        val uiState = viewModel.collectAsState()
        SplashScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@SuppressLint("ResourceAsColor")
@Composable
private fun SplashScreenContent(
    uiState: State<SplashContract.UiState>, onEventDispatcher: (SplashContract.Intent) -> Unit,
) {
    val name = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainMagenta)
            .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(modifier = Modifier.padding(start = 20.dp, bottom = 48.dp)) {
            Text(
                text = "Welcome",
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = Color.White,
                fontSize = 48.sp,
            )
            Text(
                text = "to BrainBuzzer !", fontFamily = FontFamily(Font(R.font.montserrat_semibold)), color = Color.White, fontSize = 24.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "What is your name?", textAlign = TextAlign.Start, color = Color.White
            )
            Image(painter = painterResource(id = R.drawable.right),
                contentDescription = null,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        if (name.value.isNotEmpty()) {
                            onEventDispatcher(SplashContract.Intent.SaveName(name.value))
                        } else {
                            onEventDispatcher(SplashContract.Intent.IsError(true))
                        }
                    })
        }
        AppTextField(
            value = name.value,
            errorText = if (uiState.value.error) {
                "Field must not be empty"
            } else {
                ""
            },
            onValueChange = {
                if (!it.contains(' ') && it.length < 20) {
                    name.value = it
                    onEventDispatcher(SplashContract.Intent.IsError(false))
                }
            },
            trailingIcon = {},
            textStyle = TextStyle(color = Color.Black, fontSize = 24.sp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 4.dp),
        )
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MainMagenta)
    systemUiController.setNavigationBarColor(MainMagenta)
}
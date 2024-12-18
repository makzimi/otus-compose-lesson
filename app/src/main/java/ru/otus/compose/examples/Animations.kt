package ru.otus.compose.examples

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun AnimationExample() {
    var switch by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(onClick = { switch = !switch }) {
            Text(text = "Press Here!")
        }
        val bgColor: Color by animateColorAsState(if (switch) Color.Red else Color.Green,
            animationSpec = tween(1000, easing = LinearEasing)
        )
        Box(modifier = Modifier.size(100.dp).background(color = bgColor))
    }
}

@Preview
@Composable
fun MainScreen() {

    var boxVisible by remember { mutableStateOf(true) }

    val onClick = { newState: Boolean ->
        boxVisible = newState
    }

    Column(
        Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "Show", targetState = true, onClick = onClick)
            CustomButton(text = "Hide", targetState = false, onClick = onClick)
        }

        Spacer(modifier = Modifier.height(20.dp))


        AnimatedVisibility(
            visible = boxVisible,
            enter = fadeIn() + slideInHorizontally() + slideInVertically(),
            exit = slideOutVertically() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .size(height = 200.dp, width = 200.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
fun CustomButton(
    text: String, targetState: Boolean,
    onClick: (Boolean) -> Unit, bgColor: Color = Color.Blue
) {

    Button(
        onClick = { onClick(targetState) },
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color.White
        )
    ) {
        Text(text)
    }
}
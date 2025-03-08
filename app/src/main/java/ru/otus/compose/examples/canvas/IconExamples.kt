package ru.otus.compose.examples.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun InstagramIcon() {
    val instaColors = listOf(Color.Yellow, Color.Red, Color.Magenta)
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        drawRoundRect(
            brush = Brush.linearGradient(colors = instaColors),
            cornerRadius = CornerRadius(60f, 60f),
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )
        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = 45f,
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )
        drawCircle(
            brush = Brush.linearGradient(colors = instaColors),
            radius = 13f,
            center = Offset(this.size.width * .80f, this.size.height * 0.20f),
        )
    }
}

@Preview
@Composable
fun InstagramIconPreview() {
    Surface {
        InstagramIcon()
    }
}

@Composable
fun MessengerIcon() {
    val colors = listOf(Color(0xFF02b8f9), Color(0xFF0277fe))
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {

        val trianglePath = Path().let {
            it.moveTo(this.size.width * .20f, this.size.height * .77f)
            it.lineTo(this.size.width * .20f, this.size.height * 0.95f)
            it.lineTo(this.size.width * .37f, this.size.height * 0.86f)
            it.close()
            it
        }

        val electricPath = Path().let {
            it.moveTo(this.size.width * .20f, this.size.height * 0.60f)
            it.lineTo(this.size.width * .45f, this.size.height * 0.35f)
            it.lineTo(this.size.width * 0.56f, this.size.height * 0.46f)
            it.lineTo(this.size.width * 0.78f, this.size.height * 0.35f)
            it.lineTo(this.size.width * 0.54f, this.size.height * 0.60f)
            it.lineTo(this.size.width * 0.43f, this.size.height * 0.45f)
            it.close()
            it
        }

        drawOval(
            Brush.verticalGradient(colors = colors),
            size = Size(this.size.width, this.size.height * 0.95f)
        )

        drawPath(
            path = trianglePath,
            brush = Brush.verticalGradient(colors = colors),
            style = Stroke(width = 15f, cap = StrokeCap.Round)
        )

        drawPath(path = electricPath, color = Color.White)

    }
}

@Preview
@Composable
fun FacebookIconPreview() {
    Surface {
        MessengerIcon()
    }
}

@Composable
fun GetWeatherApp() {
    val backgroundColor = listOf(Color(0xFF2078EE), Color(0xFF74E6FE))
    val sunColor = listOf(Color(0xFFFFC200), Color(0xFFFFE100))
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .padding(16.dp)
    ) {
        val width = size.width
        val height = size.height
        val path = Path().apply {
            moveTo(width.times(.76f), height.times(.72f))
            cubicTo(
                width.times(.93f),
                height.times(.72f),
                width.times(.98f),
                height.times(.41f),
                width.times(.76f),
                height.times(.40f)
            )
            cubicTo(
                width.times(.75f),
                height.times(.21f),
                width.times(.35f),
                height.times(.21f),
                width.times(.38f),
                height.times(.50f)
            )
            cubicTo(
                width.times(.25f),
                height.times(.50f),
                width.times(.20f),
                height.times(.69f),
                width.times(.41f),
                height.times(.72f)
            )
            close()
        }
        drawRoundRect(
            brush = Brush.verticalGradient(backgroundColor),
            cornerRadius = CornerRadius(50f, 50f),

            )
        drawCircle(
            brush = Brush.verticalGradient(sunColor),
            radius = width.times(.17f),
            center = Offset(width.times(.35f), height.times(.35f))
        )
        drawPath(path = path, color = Color.White.copy(alpha = .90f))
    }
}

@Preview
@Composable
fun GetWeatherAppPreview() {
    Surface {
        GetWeatherApp()
    }
}

@Composable
fun DrawTulip() {
    val petal1 = Brush.verticalGradient(
        listOf(Color(0xFFef4d67), Color(0xFFd92e50))
    )

    val petal2 = Brush.verticalGradient(
        listOf(Color(0xffcd324b), Color(0xffbc203c))
    )

    val petal3 = Brush.verticalGradient(
        listOf(Color(0xffae182d), Color(0xffae182d))
    )

    val stemColor = Color(0xFF4CAF50)
    val leafColor = Brush.verticalGradient(
        listOf(Color(0xFF66BB6A), Color(0xFF56AB5A))
    )
    val leafShadowColor = Color(0xFF2C8F30)

    Canvas(
        modifier = Modifier
            .size(150.dp)
            .padding(16.dp)
    ) {
        val width = size.width
        val height = size.height

        val stemPath = Path().apply {
            moveTo(width * 0.49f, height * 0.4f)
            lineTo(width * 0.51f, height * 0.4f)
            lineTo(width * 0.54f, height * 0.96f)
            lineTo(width * 0.46f, height * 0.96f)
            close()
        }
        drawPath(
            path = stemPath,
            color = stemColor
        )

        val leftLeafPath = Path().apply {
            moveTo(width * 0.5f, height * 0.9f)
            quadraticTo(
                width * 0.1f, height * 0.4f,
                width * 0.2f, height * 0.65f
            )
            quadraticTo(
                width * 0.3f, height * 0.9f,
                width * 0.5f, height * 0.95f
            )
            close()
        }
        drawPath(path = leftLeafPath, brush = leafColor)

        val leftLeafShadow = Path().apply {
            moveTo(width * 0.44f, height * 0.88f)
            lineTo(width * 0.24f, height * 0.65f)
            quadraticTo(
                width * 0.3f, height * 0.8f,
                width * 0.44f, height * 0.88f
            )
            close()
        }
        drawPath(path = leftLeafShadow, color = leafShadowColor)

        withTransform(
            transformBlock = { scale(-1f, 1f, Offset(width * 0.5f, 0f)) },
            drawBlock = {
                drawPath(path = leftLeafPath, brush = leafColor)
                drawPath(path = leftLeafShadow, color = leafShadowColor)
            }
        )

        val centerPetalPath = Path().apply {
            moveTo(width * 0.38f, height * 0.3f)
            cubicTo(
                width * 0.50f, height * 0.1f,
                width * 0.50f, height * 0.1f,
                width * 0.60f, height * 0.3f
            )
            close()
        }
        drawPath(path = centerPetalPath, brush = petal3)

        val leftPetalPath = Path().apply {
            moveTo(width * 0.36f, height * 0.4f)
            cubicTo(
                width * 0.26f, height * 0.1f,
                width * 0.50f, height * 0.1f,
                width * 0.57f, height * 0.4f
            )
            cubicTo(
                width * 0.57f, height * 0.55f,
                width * 0.43f, height * 0.55f,
                width * 0.36f, height * 0.4f
            )
            close()
        }
        drawPath(path = leftPetalPath, brush = petal2)

        withTransform(
            transformBlock = { scale(-1f, 1f, Offset(width * 0.5f, 0f)) },
            drawBlock = {
                drawPath(path = leftPetalPath, brush = petal1)
            }
        )
    }
}

@Preview
@Composable
fun DrawTulipPreview() {
    Surface {
        DrawTulip()
    }
}

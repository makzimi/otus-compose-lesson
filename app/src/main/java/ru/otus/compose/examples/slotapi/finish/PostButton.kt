package ru.otus.compose.examples.slotapi.finish

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun PostButton(
    icon: Int,
    onClick: () -> Unit = { },
    contentDescription: String? = null,
){
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

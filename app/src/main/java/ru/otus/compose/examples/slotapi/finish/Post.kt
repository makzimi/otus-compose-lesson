package ru.otus.compose.examples.slotapi.finish

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Immutable
sealed class PostState(
    val profileState: ProfileState,
)

@Composable
fun Post(
    modifier: Modifier = Modifier,
    userSection: @Composable () -> Unit,
    postSection: @Composable () -> Unit,
    buttonsSection: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 8.dp)
        ) {
            userSection()
            postSection()
            buttonsSection()
        }
    }
}

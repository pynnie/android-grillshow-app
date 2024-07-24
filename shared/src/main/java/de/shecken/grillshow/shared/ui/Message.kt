package de.shecken.grillshow.shared.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.shecken.grillshow.shared.GrillshowTheme
import de.shecken.grillshow.shared.R


@Composable
fun Message(
    title: String,
    message: String,
    buttonText: String,
    @DrawableRes imageRes: Int,
    onButtonClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = imageRes),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier.padding(16.dp),
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClick,
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
private fun MessagePreview() {
    GrillshowTheme {
        Message(title = "A message title", message = "very important message", buttonText = "Okay", imageRes = R.drawable.johnny) {

        }
    }
}
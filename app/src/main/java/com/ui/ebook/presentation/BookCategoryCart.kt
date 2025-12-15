package com.ui.ebook.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.ui.ebook.presentation.navigation.Routes

@Composable
fun BookCategoryCart(
    image: String,
    category: String,
    navHostController: NavHostController
) {
    Card(
        Modifier
            .padding(8.dp)
            .height(150.dp)
            .width(150.dp)
            .clickable{
                navHostController.navigate(Routes.BookByCategory(category))
            }

        ) {
        Column(
            Modifier.fillMaxWidth().padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .size(80.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(8.dp)),
                loading = {},
                error = {
                    Text("ERROR in loading image")
                },
            )
            Spacer(Modifier.height(8.dp))
            Text(text = category,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic)
        }
    }
}


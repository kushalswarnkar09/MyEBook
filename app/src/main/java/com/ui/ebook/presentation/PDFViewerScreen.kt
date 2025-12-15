package com.ui.ebook.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun PDFViewerScreen(
    bookUrl: String
) {
    var isDarkMode by remember { mutableStateOf(false) }
    val pdfState = rememberVerticalPdfReaderState( resource = ResourceType.Remote(bookUrl), isZoomEnable = true)
    VerticalPDFReader(
        state = pdfState,
        Modifier.fillMaxSize().background(Color.Gray)
    )
}



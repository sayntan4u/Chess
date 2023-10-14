package com.example.chess.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.chess.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val autourOne = FontFamily(
    Font(
        googleFont = GoogleFont("Autour One"),
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)
val archivo = FontFamily(
    Font(
        googleFont = GoogleFont("Archivo"),
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)

val artifika = FontFamily(
    Font(
        googleFont = GoogleFont("Artifika"),
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)

val astloch = FontFamily(
    Font(
        googleFont = GoogleFont("Astloch"),
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)




// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
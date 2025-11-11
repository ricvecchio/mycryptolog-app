package com.blimas.mycryptolog.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale

class CurrencyVisualTransformation(private val locale: Locale = Locale("pt", "BR")) :
    VisualTransformation {
    private val currencyFormat = NumberFormat.getCurrencyInstance(locale)

    override fun filter(text: AnnotatedString): TransformedText {
        val digitsOnly = text.text.filter { it.isDigit() }
        val amount = digitsOnly.toLongOrNull() ?: 0L
        val formattedAmount = currencyFormat.format(amount / 100.0)

        val originalTextLength = digitsOnly.length
        val formattedTextLength = formattedAmount.length

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return 0
                return formattedTextLength
            }

            override fun transformedToOriginal(offset: Int): Int {
                return originalTextLength
            }
        }

        return TransformedText(AnnotatedString(formattedAmount), offsetMapping)
    }
}
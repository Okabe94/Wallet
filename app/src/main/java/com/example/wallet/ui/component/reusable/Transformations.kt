package com.example.wallet.ui.component.reusable

import android.icu.number.NumberFormatter
import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.NumberFormat

private const val TAG = "CurrencyTransformation"

class CurrencyTransformation : VisualTransformation {

    private val formatter: NumberFormat = DecimalFormat("#,###")

    override fun filter(text: AnnotatedString) = TransformedText(
        AnnotatedString(formatter.toCurrency(text.text)),
        object : OffsetMapping {
            override fun originalToTransformed(offset: Int) =
                formatter.toCurrency(text.text).length

            override fun transformedToOriginal(offset: Int) = text.length
        }
    )

    private fun NumberFormat.toCurrency(value: String) = try {
        format(value.toDouble())
    } catch (e: Exception) {
        Log.e(TAG, e.message, e.cause)
        value
    }
}
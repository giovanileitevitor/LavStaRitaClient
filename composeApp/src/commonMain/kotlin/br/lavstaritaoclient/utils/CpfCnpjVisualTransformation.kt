package br.lavstaritaoclient.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * VisualTransformation para formatar CPF (11 dígitos) ou CNPJ (14 dígitos) dinamicamente.
 */
class CpfCnpjVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val rawText = text.text
        val out = StringBuilder()

        if (rawText.length <= 11) {
            // Formato CPF: XXX.XXX.XXX-XX
            for (i in rawText.indices) {
                out.append(rawText[i])
                if (i == 2 || i == 5) out.append('.')
                if (i == 8) out.append('-')
            }
        } else {
            // Formato CNPJ: XX.XXX.XXX/XXXX-YY
            for (i in rawText.indices) {
                out.append(rawText[i])
                if (i == 1 || i == 4) out.append('.')
                if (i == 7) out.append('/')
                if (i == 11) out.append('-')
            }
        }

        val transformedText = AnnotatedString(out.toString())

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (rawText.length <= 11) {
                    if (offset <= 2) return offset
                    if (offset <= 5) return offset + 1
                    if (offset <= 8) return offset + 2
                    if (offset <= 11) return offset + 3
                    return offset + 3
                } else {
                    if (offset <= 1) return offset
                    if (offset <= 4) return offset + 1
                    if (offset <= 7) return offset + 2
                    if (offset <= 11) return offset + 3
                    if (offset <= 14) return offset + 4
                    return offset + 4
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (rawText.length <= 11) {
                    if (offset <= 3) return offset
                    if (offset <= 7) return offset - 1
                    if (offset <= 11) return offset - 2
                    if (offset <= 14) return offset - 3
                    return rawText.length
                } else {
                    if (offset <= 2) return offset
                    if (offset <= 6) return offset - 1
                    if (offset <= 10) return offset - 2
                    if (offset <= 15) return offset - 3
                    if (offset <= 18) return offset - 4
                    return rawText.length
                }
            }
        }

        return TransformedText(transformedText, offsetMapping)
    }
}
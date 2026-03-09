package com.antony.hybridai.intent

class IntentClassifier {

    fun classify(text: String): IntentResult {

        val lower = text.lowercase()

        val complexKeywords = listOf(
            "explain",
            "research",
            "analyze",
            "how",
            "why",
            "compare"
        )

        val isComplex =
            complexKeywords.any { lower.contains(it) }

        val complexity =
            if (isComplex) 0.9f else 0.2f

        val isPrivate =
            lower.contains("password") ||
                    lower.contains("bank")

        return IntentResult(
            complexity = complexity,
            isPrivate = isPrivate
        )
    }
}
package common.lang

import java.text.Normalizer


fun String.isStringNull() = this.equals("null", true)

fun String.isBlankOrNullString() = this.isBlank() || isStringNull()

fun String.toEmptyString() = if (this.isBlankOrNullString()) "" else this

fun String?.trimNull() = if (this != null && !this.isBlankOrNullString()) this else null

inline fun String.checkNotBlank(lazyMessage: () -> Any) {
    check(this.isNotBlank(), lazyMessage)
}

// https://stackoverflow.com/questions/57298658/how-do-i-create-a-url-slug-extension
fun String.slug(lowerCase: Boolean = true): String {
    val str = Normalizer
        .normalize(this, Normalizer.Form.NFD)
        .replace("[^\\w\\s-]".toRegex(), "") // Remove all non-word, non-space or non-dash characters
        .replace('-', ' ') // Replace dashes with spaces
        .trim() // Trim leading/trailing whitespace (including what used to be leading/trailing dashes)
        .replace("\\s+".toRegex(), "-") // Replace whitespace (including newlines and repetitions) with single dashes

    return if (lowerCase) str.lowercase() else str
}

// UID

const val RANDOM_CHARS: String = "12346789ABCDEFGHJKLMNPQRTUVWXYZabcdefghjklmnpqrtuvwxyz"

fun randomString(length: Int) = (1..length).map { RANDOM_CHARS.random() }.joinToString("")

fun id() = randomString(10)

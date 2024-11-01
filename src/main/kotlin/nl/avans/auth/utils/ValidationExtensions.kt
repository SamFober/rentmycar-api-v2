package nl.avans.auth.utils

import kotlinx.datetime.*

val nameRegex = Regex("\\b([A-ZÀ-ÿ][-,a-z. ']+ *)+")
val emailRegex =
    Regex("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")
val postalCodeRegex = Regex("[1-9]{1}[0-9]{3}[a-zA-Z]{2}")
val phoneNumberRegex =
    Regex("^\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}\$")


fun String.isValidEmail(): Boolean {
    return emailRegex.matches(this)
}

fun String.isValidPostalCode(): Boolean {
    return postalCodeRegex.matches(this)
}

fun String.isValidName(): Boolean {
    return nameRegex.matches(this)
}

fun String.isValidPhoneNumber(): Boolean {
    return phoneNumberRegex.matches(this)
}

fun LocalDate.isOver18(): Boolean {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val age = this.yearsUntil(today)
    return age >= 18
}


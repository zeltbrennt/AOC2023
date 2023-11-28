package util

import java.io.FileNotFoundException

fun loadAsList(day: Int): List<String> {
    val day = "../day${day.toString().padStart(2, '0')}.txt"
    return object {}.javaClass.getResourceAsStream(day)?.bufferedReader()?.readLines()
        ?: throw FileNotFoundException("Input file not found. Did you forget to download it?")
}
fun loadAsString(day: Int) : String {
    val day = "../day${day.toString().padStart(2, '0')}.txt"
    return object {}.javaClass.getResourceAsStream(day)?.bufferedReader()?.readText()
        ?: throw FileNotFoundException("Input file not found. Did you forget to download it?")
}

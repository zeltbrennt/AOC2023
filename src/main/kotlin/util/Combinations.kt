package util

fun generateCombinations(variableRanges: List<IntRange>): Iterator<List<Int>> = iterator {
    val sizes = variableRanges.map { it.last - it.first + 1 }
    val totalCombinations = sizes.reduce { acc, size -> acc * size }

    for (i in 0..<totalCombinations) {
        val combination = mutableListOf<Int>()
        var remaining = i
        for (size in sizes) {
            combination.add(remaining % size + variableRanges[combination.size].first)
            remaining /= size
        }
        yield(combination)
    }
}
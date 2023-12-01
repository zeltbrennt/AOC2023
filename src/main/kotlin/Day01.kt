import util.loadAsList

class Day01(
    private val input: List<String> = loadAsList(day = 1)
) {

    fun part1(): Int =
        input.map { line -> line.filter { it.isDigit() } }
            .sumOf { it.first().digitToInt() * 10 + it.last().digitToInt() }


    fun part2(): Int {
        val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val firstNumberRegex = Regex("\\d|${numbers.joinToString("|")}")
        val lastNumberRegex = Regex("\\d|${numbers.joinToString("|").reversed()}")

        return input.sumOf { line ->
            val firstNumber = firstNumberRegex.find(line)?.groupValues?.get(0)
            val lastNumber = lastNumberRegex.find(line.reversed())?.groupValues?.get(0)?.reversed()
            val firstValue = firstNumber?.toIntOrNull() ?: (numbers.indexOf(firstNumber) + 1)
            val lastValue = lastNumber?.toIntOrNull() ?: (numbers.indexOf(lastNumber) + 1)
            firstValue * 10 + lastValue
        }
    }
}

fun main() {
    val solver = Day01()
    println(solver.part1())
    println(solver.part2())
}

import util.loadAsList

class Day03(
    private val input: List<String> = loadAsList(day = 3)
) {

    private val schematic = Grid(input)
    private val numbers = mutableMapOf<Pair<Int, Int>, String>()
    private val gears = mutableMapOf<Pair<Int, Int>, String>()

    fun part1(): Int {
        var currentNumber = ""
        var isEnginePart = false
        var solution = 0
        for (y in input.indices) {
            solution += if (isEnginePart) currentNumber.toInt() else 0
            isEnginePart = false
            currentNumber = ""
            for (x in input[y].indices) {
                val cell = input[y][x]
                if (cell.isDigit()) {
                    currentNumber += cell
                    isEnginePart = if (!isEnginePart) hasPartAttached(x, y) else true
                } else if (currentNumber != "") {
                    solution += if (isEnginePart) currentNumber.toInt() else 0
                    isEnginePart = false
                    currentNumber = ""
                }
            }
        }
        return solution
    }

    private fun hasPartAttached(x: Int, y: Int): Boolean {
        return listOf(
            x to y + 1,
            x to y - 1,
            x + 1 to y,
            x - 1 to y,
            x + 1 to y + 1,
            x - 1 to y + 1,
            x + 1 to y - 1,
            x - 1 to y - 1
        ).any {
            val n = input.getOrNull(it.second)?.getOrNull(it.first)
            !".0123456789".contains(n ?: '.')
        }
    }

    fun part2(): Int {
        for (y in input.indices) {
            for (x in input[y].indices) {
                val cell = input[y][x]
                if (cell == '*') {

                }
            }
        }
    }

    private fun getPartCoords(): List<Cell> {
        val parts = mutableListOf<Cell>()
        for (y in input.indices) {
            for (x in input[y].indices)
                parts.add(Cell(x, y))
        }
        return parts
    }

}

fun main() {
    val solver = Day03()
    println(solver.part1())
    println(solver.part2())
}


data class Cell(val x: Int, val y: Int)
data class Grid(val content: List<String>) {
    operator fun get(cell: Cell) = get(cell.y, cell.x)
    operator fun get(x: Int, y: Int) = content.getOrNull(y)?.getOrNull(x)
    operator fun iterator() = content.iterator()
}
import util.loadAsList

typealias Game = String

class Day02 {

    private val input = loadAsList(day = 2)

    fun part1(): Int = input.filter { game -> game.validate() }.sumOf { it.getId() }

    fun part2(): Int = input.sumOf { game -> game.getPower() }

    private fun Game.validate(): Boolean =
        this.getNumberOfCubesWithColor("red").all { it <= 12 } &&
                this.getNumberOfCubesWithColor("green").all { it <= 13 } &&
                this.getNumberOfCubesWithColor("blue").all { it <= 14 }

    private fun Game.getPower(): Int =
        this.getNumberOfCubesWithColor("red").max() *
                this.getNumberOfCubesWithColor("green").max() *
                this.getNumberOfCubesWithColor("blue").max()

    private fun Game.getId(): Int =
        Regex("""\d+""").find(this)!!.groupValues[0].toInt()

    private fun Game.getNumberOfCubesWithColor(color: String): Sequence<Int> =
        Regex("""\d+(?= ${color})""")
            .findAll(this)
            .map { it.groupValues[0].toInt() }
}

fun main() {
    val solver = Day02()
    println(solver.part1())
    println(solver.part2())
}


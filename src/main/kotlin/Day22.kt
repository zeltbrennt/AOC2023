import util.Point
import util.loadAsList
import kotlin.math.abs
import kotlin.time.measureTime

class Day22(private val input: List<String> = loadAsList(day = 22)) {

    private val bricks = buildList {
        input.forEachIndexed { i, line ->
            val temp = line.split(",", "~").map { it.toInt() }
            val a = Point(temp[0], temp[1], temp[2])
            val b = Point(temp[3], temp[4], temp[5])
            add(Brick(i, a, b))
        }
    }.sorted()


    data class Brick(val id: Int, var a: Point, var b: Point) : Comparable<Brick> {
        val cubes get() = a.pointsOnLine(b)
        val width get() = 1 + abs(a.x - b.x)
        val length get() = 1 + abs(a.y - b.y)
        val height get() = 1 + abs(a.z - b.z)
        val x get() = minOf(a.x, b.x)
        val y get() = minOf(a.y, b.y)
        val z get() = minOf(a.z, b.z)
        var support = listOf<Int>()

        override fun compareTo(other: Brick): Int = this.z.compareTo(other.z)

        fun fallDown() {
            a = a.down
            b = b.down
        }

        fun canFallDown(otherBricks: List<Brick>): Boolean {
            if (z == 1) return false
            getSupport(otherBricks)
            return support.isEmpty()
        }

        fun getSupport(otherBricks: List<Brick>) {
            val target = this.copy(a = a.down, b = b.down).cubes subtract this.cubes.toSet()
            val obstacles = otherBricks.associate { it.id to it.cubes }
            support = obstacles
                .filter { obstacle -> obstacle.value.any { it in target } }
                .map { it.key }
        }
    }

    fun printBricks() {
        val maxX = bricks.maxOf { it.x + it.width - 1 }
        val maxY = bricks.maxOf { it.y + it.length - 1 }
        val maxZ = bricks.maxOf { it.z + it.height - 1 }
        val cubeNames = bricks.map { b -> b.cubes.map { it to b.id } }.flatten().toMap()
        println("x/z\t\ty/z")
        for (z in maxZ downTo 0) {
            for (x in 0..maxX) {
                val b = cubeNames.filter { it.key.z == z && it.key.x == x }.map { it.value }.distinct()
                if (b.isEmpty()) print(".")
                else if (b.size == 1) print(b.first() % 10)
                else print("?")
            }
            print("\t\t")
            for (y in 0..maxY) {
                val b = cubeNames.filter { it.key.z == z && it.key.y == y }.map { it.value }.distinct()
                if (b.isEmpty()) print(".")
                else if (b.size == 1) print(b.first() % 10)
                else print("?")
            }
            println()
        }
    }

    fun part1(): Int {
        //bricks.forEach(::println)
        printBricks()
        bricks.forEach { brick ->
            val obstacles = bricks.filter { it.z < brick.z }//.takeLast(50)
            while (brick.canFallDown(obstacles)) brick.fallDown()
        }
        println()
        printBricks()
        return bricks.size - bricks.filter { it.support.size == 1 }.flatMap { it.support }.distinct().size
        //436 too high
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    var solver: Day22
    measureTime { solver = Day22() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        

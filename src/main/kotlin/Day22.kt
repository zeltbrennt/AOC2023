import util.Point
import util.loadAsList
import kotlin.time.measureTime

class Day22(private val input: List<String> = loadAsList(day = 22)) {

    private val bricks: List<Brick>


    init {
        val bricksFalling = buildList {
            input.forEachIndexed { i, line ->
                val temp = line.split(",", "~").map { it.toInt() }
                val a = Point(temp[0], temp[1], temp[2])
                val b = Point(temp[3], temp[4], temp[5])
                add(Brick(i, a, b))
            }
        }
        val occupied = mutableSetOf<Point>()
        bricks = buildList {
            for (falling in bricksFalling) {
                var current = falling.cubes
                var next = current.map { it.down }.toSet()
                var intersect = next intersect occupied
                while (intersect.isEmpty() && next.none { it.z == 0 }) {
                    current = next
                    next = current.map { it.down }.toSet()
                    intersect = next intersect occupied
                }
                occupied.addAll(current)
                add(falling.copy(cubes = current))
            }
        }
    }


    data class Brick(val id: Int, val cubes: Set<Point>) : Comparable<Brick> {
        constructor(id: Int, a: Point, b: Point) : this(id, a.pointsOnLine(b).toSet())

        var supportedBy = listOf<Brick>()
        var supports = listOf<Brick>()

        override fun compareTo(other: Brick): Int = this.cubes.minOf { it.z }.compareTo(other.cubes.minOf { it.z })

        fun getPassiveSupport(otherBricks: List<Brick>) {
            val target = this.cubes.map { it.down } subtract this.cubes
            supportedBy = otherBricks
                .filter { obstacle -> obstacle.cubes.any { it in target } }
        }

        fun getActiveSupport(otherBricks: List<Brick>) {
            val target = this.cubes.map { it.up } subtract this.cubes
            supports = otherBricks
                .filter { obstacle -> obstacle.cubes.any { it in target } }
        }
    }


    fun part1(): Int {
        bricks.forEach { brick ->
            brick.getActiveSupport(bricks)
            brick.getPassiveSupport(bricks)
        }
        return bricks.filter { brick -> brick.supports.all { it.supportedBy.size > 1 } }.size
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
        

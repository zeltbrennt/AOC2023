import util.loadAsString
import kotlin.time.measureTime

class Day15(input: String = loadAsString(day = 15)) {

    private val initSequence = input.trim().split(",")

    fun part1(): Int = initSequence.sumOf { it.hash() }

    fun part2(): Int {
        val boxes = List(256) { mutableListOf<Lens>() }
        for (step in initSequence) {
            val (label, focalLength) = step.split("-", "=")
            if (focalLength.isBlank()) {
                boxes[label.hash()].removeIf { it.label == label }
            } else {
                val lens = Lens(label, focalLength.toInt())
                val id = boxes[label.hash()].indexOfFirst { it.label == label }
                if (id != -1) {
                    boxes[label.hash()][id] = lens
                } else {
                    boxes[label.hash()].add(lens)
                }
            }
            /*println("After ${step}")
            boxes.mapIndexed { i, it -> i to it }.filter { it.second.isNotEmpty() }
                .onEach { println("Box ${it.first}: ${it.second}") }*/
        }
        return boxes.mapIndexed { index, box ->
            box.mapIndexed { slot, lens ->
                lens.focalLength * (1 + slot) * (1 + index)
            }
        }
            .flatten().sum()
    }

    data class Lens(val label: String, val focalLength: Int) {
        override fun toString(): String {
            return "[$label $focalLength]"
        }
    }

    private fun String.hash() = this.fold(0) { acc, el -> ((acc + el.code) * 17) % 256 }

}


fun main() {
    var solver: Day15
    measureTime { solver = Day15() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        

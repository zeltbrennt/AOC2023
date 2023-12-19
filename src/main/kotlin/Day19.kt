import util.loadAsList
import kotlin.time.measureTime

class Day19(private val input: List<String> = loadAsList(day = 19)) {

    private val functions = buildMap<String, (Present) -> Boolean?> {
        put("A") { _ -> true }
        put("R") { _ -> false }
        input.forEach {
            if (it == "") return@buildMap
            this += createFunction(it)
        }
    }

    private val presents = buildList {
        val regex = """\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)\}""".toRegex()
        input.forEach {
            val match = regex.matchEntire(it)
            if (match != null) {
                val (x, m, a, s) = match.destructured
                add(mapOf("x" to x.toInt(), "m" to m.toInt(), "a" to a.toInt(), "s" to s.toInt()))
            }
        }
    }

    private fun createFunction(input: String): Pair<String, (Present) -> Boolean?> {
        val name = input.substringBefore("{")
        val params = mutableListOf<String>()
        val ops = mutableListOf<Int>()
        val vals = mutableListOf<Int>()
        val funs = mutableListOf<String>()
        for (item in input.substringAfter("{").substringBefore("}").split(",")) {
            val matches = """([xmas])([<>])(\d+):(\D+)""".toRegex().matchEntire(item) ?: break
            val (p, o, v, f) = matches.destructured
            params.add(p)
            ops.add(if (o == "<") -1 else 1)
            vals.add(v.toInt())
            funs.add(f)
        }
        val default = input.substringAfterLast(",").substringBefore("}")
        return name to funk@{ present: Present ->
            for (i in 0..params.lastIndex) {
                if (present[params[i]]?.compareTo(vals[i]) == ops[i]) {
                    return@funk functions[funs[i]]?.invoke(present)
                }
            }
            functions[default]?.invoke(present)
        }
    }

    fun part1(): Int {
        return presents.filter { functions["in"]?.invoke(it) == true }.flatMap { it.values }.sum()
    }

    fun part2(): Long {
        /*Idee:
            something something k-d-tree??
        * */

    }
}

typealias Present = Map<String, Int>

fun main() {
    var solver: Day19
    measureTime { solver = Day19() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}


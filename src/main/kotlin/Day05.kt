import util.loadAsString
import kotlin.time.measureTime

data class GardenMapEntry(val target: Long, val source: Long, val range: Long) {
    operator fun contains(value: Long): Boolean = value in source..<source + range
    fun translate(value: Long): Long = value - source + target
}

class GardenMap(private val mappingRules: List<GardenMapEntry>) {

    fun mapThis(value: Long): Long {
        val key = mappingRules.filter { value in it }
            .run { this.ifEmpty { return value } }
            .first()
        return key.translate(value)
    }
}

class Day05(
    private val input: String = loadAsString(day = 5)
) {

    private val seeds =
        input.substringAfter("seeds:").substringBefore("seed-to-soil").trim().split(" ").map { it.toLong() }

    private val seedToSoil =
        buildGardenMap("seed-to-soil map:", "soil-to-fertilizer map:")
    private val soilToFertilizer =
        buildGardenMap("soil-to-fertilizer map:", "fertilizer-to-water map:")
    private val fertilizerToWater =
        buildGardenMap("fertilizer-to-water map:", "water-to-light map:")
    private val waterToLight =
        buildGardenMap("water-to-light map:", "light-to-temperature map:")
    private val lightToTemperature =
        buildGardenMap("light-to-temperature map:", "temperature-to-humidity map:")

    private val temperatureToHumidity =
        buildGardenMap("temperature-to-humidity map:", "humidity-to-location map:")

    private val humidityToLocation =
        buildGardenMap("humidity-to-location map:", "")

    private fun buildGardenMap(start: String, finish: String): GardenMap {
        val rawMap = input
            .substringAfter(start)
            .let { if (finish != "") it.substringBefore(finish) else it }
            .trim()
            .split("\r\n")
        val entries = rawMap.map { def ->
            val (target, source, range) = def.split(" ").map { it.toLong() }
            GardenMapEntry(target, source, range)

        }
        return GardenMap(entries)
    }

    fun part1(): Long {
        return seeds
            .map { seedToSoil.mapThis(it) }
            .map { soilToFertilizer.mapThis(it) }
            .map { fertilizerToWater.mapThis(it) }
            .map { waterToLight.mapThis(it) }
            .map { lightToTemperature.mapThis(it) }
            .map { temperatureToHumidity.mapThis(it) }
            .map { humidityToLocation.mapThis(it) }
            .min()
    }

    fun part2(): Int {
        TODO("Not yet implemented")
    }
}

fun main() {
    val solver: Day05
    measureTime { solver = Day05() }.also { println("${"init".padEnd(40, ' ')}$it") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        

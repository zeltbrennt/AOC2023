import util.loadAsString
import kotlin.time.measureTime

data class GardenMapEntry(val target: Long, val source: Long, val range: Long) {
    private val targetRange = target..<target + range
    private val sourceRange = source..<source + range
    fun containsInSource(value: Long): Boolean = value in sourceRange
    fun translateForward(value: Long): Long = value - source + target
    fun containsInTarget(value: Long): Boolean = value in targetRange
    fun translateBackwards(value: Long): Long = value - target + source
}

class GardenMap(private val mappingRules: List<GardenMapEntry>) {

    fun searchForward(value: Long): Long {
        val key = mappingRules.filter { it.containsInSource(value) }
            .run { this.ifEmpty { return value } }
            .first()
        return key.translateForward(value)
    }

    fun searchBackward(value: Long): Long {
        val key = mappingRules.filter { it.containsInTarget(value) }
            .run { this.ifEmpty { return value } }
            .first()
        return key.translateBackwards(value)
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

    private val seedsRanges = seeds.windowed(2, 2).map { it[0]..<it[0] + it[1] }

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
            .map { seedToSoil.searchForward(it) }
            .map { soilToFertilizer.searchForward(it) }
            .map { fertilizerToWater.searchForward(it) }
            .map { waterToLight.searchForward(it) }
            .map { lightToTemperature.searchForward(it) }
            .map { temperatureToHumidity.searchForward(it) }
            .map { humidityToLocation.searchForward(it) }
            .min()
    }

    fun part2(): Long {
        var target = 0L
        while (true) {
            val hum = humidityToLocation.searchBackward(target)
            val temp = temperatureToHumidity.searchBackward(hum)
            val light = lightToTemperature.searchBackward(temp)
            val wat = waterToLight.searchBackward(light)
            val fert = fertilizerToWater.searchBackward(wat)
            val soil = soilToFertilizer.searchBackward(fert)
            val seed = seedToSoil.searchBackward(soil)
            if (seedsRanges.any { seed in it }) {
                return target
            }
            target++
        }
    }
}

fun main() {
    val solver: Day05
    measureTime { solver = Day05() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { solver.part1().also { print("Part 1: $it".padEnd(40, ' ')) } }.also { println("$it") }
    measureTime { solver.part2().also { print("Part 2: $it".padEnd(40, ' ')) } }.also { println("$it") }
}
        

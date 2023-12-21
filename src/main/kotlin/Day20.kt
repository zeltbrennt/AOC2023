import util.loadAsList
import kotlin.time.measureTime

class Day20(input: List<String> = loadAsList(day = 20)) {

    enum class Value { HIGH, LOW }
    data class Pulse(val sender: String, val value: Value)

    abstract class Module(internal val name: String, internal val outputs: List<String>) {
        constructor(name: String) : this(name, emptyList())

        private val buffer = mutableListOf<Pulse>()


        fun receive(pulse: Pulse) {
            //println("${pulse.sender} -> ${pulse.value} -> $name")
            buffer.add(pulse)
        }

        fun isProcessing(): Boolean {
            return buffer.isNotEmpty()
        }

        fun tick() {
            if (buffer.isEmpty()) return
            val pulse = buffer.removeFirst()
            if (pulse.value == Value.HIGH) high++
            else low++
            handle(pulse)
        }

        abstract fun handle(pulse: Pulse)
        abstract fun reset()

        companion object {
            val modules = mutableMapOf<String, Module>()
            var high = 0L
            var low = 0L
            val relevant: MutableMap<String, Long> = mutableMapOf()
            var counter = 0L
        }
    }

    class FlipFlop(name: String, outputs: List<String>) : Module(name, outputs) {
        private var on = false

        init {
            modules[name] = this
        }

        override fun handle(pulse: Pulse) {
            if (pulse.value == Value.HIGH) return
            outputs.forEach {
                if (on) modules[it]?.receive(Pulse(name, Value.LOW))
                else modules[it]?.receive(Pulse(name, Value.HIGH))
            }
            on = !on
        }

        override fun reset() {
            on = false
        }

        override fun toString(): String {
            return "$name=${if (on) "on" else "off"}"
        }
    }

    class Broadcast(name: String, outputs: List<String>) : Module(name, outputs) {
        init {
            modules[name] = this
        }

        override fun handle(pulse: Pulse) {
            outputs.forEach {
                modules[it]?.receive(pulse.copy(sender = name))
            }
        }

        override fun reset() {

        }

        override fun toString(): String {
            return "$name=$outputs"
        }
    }

    class Conjunction(name: String, outputs: List<String>) : Module(name, outputs) {
        init {
            modules[name] = this
        }

        private val inputs = mutableMapOf<String, Value>()
        private var hasReported = false

        fun addInput(name: String) {
            inputs[name] = Value.LOW
        }

        override fun handle(pulse: Pulse) {
            inputs[pulse.sender] = pulse.value
            if (inputs.any { it.value == Value.LOW }) {
                outputs.forEach { modules[it]?.receive(Pulse(name, Value.HIGH)) }
            } else {
                outputs.forEach { modules[it]?.receive(Pulse(name, Value.LOW)) }
            }
            if (inputs.all { it.value == Value.LOW } && name in relevant && !hasReported) {
                //println("$name: $counter")
                relevant[name] = counter
                hasReported = true
            }
        }

        override fun reset() {
            inputs.forEach {
                inputs[it.key] = Value.LOW
            }
        }

        override fun toString(): String {
            return "$name=${inputs}"
        }
    }

    class Output(name: String) : Module(name) {

        init {
            modules[name] = this
        }

        override fun handle(pulse: Pulse) {
        }

        override fun reset() {
        }

    }

    init {
        /*initialize first*/
        val conjunctions = mutableListOf<String>()
        for (line in input) {
            val (name, out) = line.split(" -> ")
            val outputs = out.split(", ")
            if (name.first() == '%') FlipFlop(name.substringAfter("%"), outputs)
            else if (name.first() == '&') {
                conjunctions.add(name.substringAfter("&"))
                Conjunction(conjunctions.last(), outputs)
            } else Broadcast(name, outputs)
        }
        /*check again for conjunctions and outputs */
        for (line in input) {
            val (name, out) = line.split(" -> ")
            val cleanName = name.removePrefix("&").removePrefix("%")
            for (con in conjunctions) {
                if (out.contains(con)) {
                    (Module.modules[con] as Conjunction).addInput(cleanName)
                }
            }
            for (output in out.split(", ")) {
                if (output !in Module.modules) {
                    Output(output)
                }
            }
        }
        val (x) = Module.modules.values.filter { "rx" in it.outputs }.map { it.name }
        Module.modules.values.filter { x in it.outputs }.map { it.name }
            .forEach { Module.relevant[it] = 0 }

    }

    private fun pushButton() {
        Module.modules["broadcaster"]?.receive(Pulse("button", Value.LOW))
        while (Module.modules.values.any { it.isProcessing() }) {
            Module.modules.values.forEach(Module::tick)
            //println(Module.modules.values.toString())
            //println("high = ${Module.high}, low = ${Module.low}")
        }
    }

    fun part1(): Long {
        repeat(1000) {
            pushButton()
        }
        return Module.high * Module.low
    }

    //4330938921000 low
    fun part2(): Long {
        Module.modules.values.forEach(Module::reset)
        while (Module.relevant.any { it.value == 0L }) {
            Module.counter++
            pushButton()
        }
        return Module.relevant.values.reduce { acc, l -> util.lcm(acc, l) }
    }
}

fun main() {
    var solver: Day20
    measureTime { solver = Day20() }.also { println("${"init".padEnd(40, ' ')}${it}") }
    measureTime { print("Part 1: ${solver.part1()}".padEnd(40, ' ')) }.also { println("$it") }
    measureTime { print("Part 2: ${solver.part2()}".padEnd(40, ' ')) }.also { println("$it") }
}
        

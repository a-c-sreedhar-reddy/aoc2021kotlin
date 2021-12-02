fun main() {
    fun part1(input: List<String>): Int {
        val measurements = input.map { measurement -> measurement.toInt() }
        return measurements.foldIndexed(0) { index, acc, current ->
            if (index === 0) {
                acc
            } else if (measurements[index - 1] <= current) {
                acc + 1
            } else {
                acc
            }
        }
    }

    fun part2(input: List<String>): Int {
        val measurements = input.map { measurement -> measurement.toInt() }

        fun getLargeMeasurement(measurements: List<Int>, start: Int): Int {
            return if (start + 3 >= measurements.size) {
                0
            } else {
                val firstSum = measurements[start] + measurements[start + 1] + measurements[start + 2]
                val secondSum = measurements[start + 1] + measurements[start + 2] + measurements[start + 3]
                if (firstSum < secondSum) {
                    1 + getLargeMeasurement(measurements, start + 1)
                } else {
                    getLargeMeasurement(measurements, start + 1)
                }
            }

        }
        return getLargeMeasurement(measurements, 0)
    }


    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

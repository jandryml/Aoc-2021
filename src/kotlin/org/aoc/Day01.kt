fun main() {
    fun part1(input: List<String>): Int {
        var count = 1
        for (i in (0 until input.lastIndex)) {
            if (input[i] < input[i + 1]) {
                count++
            }
        }
        return count
    }

    fun addValueToIndices(indices: List<Int>, value: Int, array: IntArray) {
        indices.forEach {
            array[it] += value
        }
    }

    fun areValuesIncreasing(indexA: Int, indexB: Int, array: IntArray): Boolean {
        return (array[indexA] < array[indexB])
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val windows = IntArray(4)

        for (i in (0..input.lastIndex)) {
            val value = input[i].toInt()

            when (i % 4) {
                0 -> {
                    windows[0] = 0
                    addValueToIndices(listOf(0, 2, 3), value, windows)
                }
                1 -> {
                    windows[1] = 0
                    addValueToIndices(listOf(0, 1, 3), value, windows)
                }
                2 -> {
                    windows[2] = 0
                    addValueToIndices(listOf(0, 1, 2), value, windows)
                }
                3 -> {
                    windows[3] = 0
                    addValueToIndices(listOf(1, 2, 3), value, windows)
                }
            }

            if (i > 2) {
                when (i % 4) {
                    3 -> if (areValuesIncreasing(0, 1, windows)) count++
                    0 -> if (areValuesIncreasing(1, 2, windows)) count++
                    1 -> if (areValuesIncreasing(2, 3, windows)) count++
                    2 -> if (areValuesIncreasing(3, 0, windows)) count++
                }
            }
        }
        return count
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

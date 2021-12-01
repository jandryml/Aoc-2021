fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }
            .filterIndexed { i, value -> if (i != input.lastIndex) value < input[i + 1].toInt() else false }
            .count()
    }

    fun handleSlidingWindows(indexA: Int, indexB: Int, inputValue: Int, actualIndex: Int, array: IntArray): Boolean {
        listOf(0, 1, 2, 3).filterNot { it == indexA }.onEach { array[it] += inputValue }
        val returnValue = (actualIndex > 2 && array[indexA] < array[indexB])
        array[indexA] = 0
        return returnValue
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val slidingWindows = IntArray(4)

        for (i in (0..input.lastIndex)) {
            when (i % 4) {
                0 -> if (handleSlidingWindows(1, 2, input[i].toInt(), i, slidingWindows)) count++
                1 -> if (handleSlidingWindows(2, 3, input[i].toInt(), i, slidingWindows)) count++
                2 -> if (handleSlidingWindows(3, 0, input[i].toInt(), i, slidingWindows)) count++
                3 -> if (handleSlidingWindows(0, 1, input[i].toInt(), i, slidingWindows)) count++
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

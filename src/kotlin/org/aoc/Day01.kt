package org.aoc

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map(String::toInt)
            .filterIndexed { i, value -> if (i != input.lastIndex) value < input[i + 1].toInt() else false }
            .count()
    }

    fun handleSlidingWindows(indexA: Int, indexB: Int, inputValue: Int, actualIndex: Int, array: IntArray): Boolean {
        listOf(0, 1, 2, 3).filterNot { it == indexA }.onEach { array[it] += inputValue }
        return (actualIndex > 2 && array[indexA] < array[indexB]).apply { array[indexA] = 0 }
    }

    fun part2(input: List<String>): Int {
        var count = 0
        val slidingWindows = IntArray(4)
        var index = 0

        for (i in (0..input.lastIndex)) {
            if (handleSlidingWindows(index, ++index % 4, input[i].toInt(), i, slidingWindows).apply {
                    if (index == 4) index = 0
                }) count++
        }
        return count
    }


    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

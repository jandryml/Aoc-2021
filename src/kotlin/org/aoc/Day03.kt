package org.aoc

import readInput

fun main() {
    fun countOneBits(input: List<String>): IntArray {
        return IntArray(input[0].length).also { array ->
            input.forEach { it.forEachIndexed { i, value -> if (value == '1') array[i]++ } }
        }
    }

    fun part1(input: List<String>): Int {
        val bitCounts = countOneBits(input)

        val gammaRate = bitCounts.joinToString("") { if (it > input.size / 2) "1" else "0" }
        val epsilonRate = gammaRate.toCharArray().joinToString("") { if (it == '1') "0" else "1" }

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }


    fun getRating(inputData: List<String>, evaluate: (value: Int, inputData: List<String>) -> String): Int {
        var operateList = inputData
        var index = 0

        while (operateList.size > 1) {
            val prefixData = countOneBits(operateList).joinToString("") { evaluate(it, operateList) }
            operateList = operateList.filter { it[index] == (prefixData[index]) }.also { index++ }
        }

        return operateList[0].toInt(2)
    }

    fun part2(inputData: List<String>): Int {
        val o2Rating =
            getRating(inputData) { value, input -> if (value >= (input.size / 2 + input.size % 2)) "1" else "0" }
        val co2Rating =
            getRating(inputData) { value, input -> if (value < (input.size / 2 + input.size % 2)) "1" else "0" }

        return o2Rating * co2Rating
    }


    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

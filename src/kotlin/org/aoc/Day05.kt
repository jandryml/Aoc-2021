package org.aoc

import readInput
import kotlin.math.abs

fun main() {

    fun getRange(firstValue: Int, secondValue: Int): IntRange {
        return (Integer.min(firstValue, secondValue)..Integer.max(firstValue, secondValue))
    }

    fun part1(input: List<String>): Int {
        val ventsMap = Array(1000) { IntArray(1000) }

        input.forEach {
            val parsedValues = it.replace(" -> ", ",").split(",").map { it.toInt() }
            if (parsedValues[0] == parsedValues[2]) {
                (getRange(parsedValues[1], parsedValues[3])).forEach {
                    (ventsMap[it][parsedValues[0]])++
                }
            } else if (parsedValues[1] == parsedValues[3]) {
                (getRange(parsedValues[0], parsedValues[2])).forEach {
                    (ventsMap[parsedValues[1]][it])++
                }
            }
        }
        return ventsMap.toList().flatMap { it.toList() }.filter { it > 1 }.size
    }


    fun part2(input: List<String>): Int {
        val ventsMap = Array(1000) { IntArray(1000) }

        input.forEach {
            val parsedValues = it.replace(" -> ", ",").split(",").map { it.toInt() }
            val first = Pair(parsedValues[0], parsedValues[1])
            val second = Pair(parsedValues[2], parsedValues[3])

            if (first.first == second.first) {
                (getRange(first.second, second.second)).forEach {
                    (ventsMap[it][first.first])++
                }
            } else if (first.second == second.second) {
                (getRange(first.first, second.first)).forEach {
                    (ventsMap[first.second][it])++
                }
            } else if (abs(first.first - second.first) == abs(first.second - second.second)) {
                val range1 = getRange(first.first, second.first).toList()
                    .let { if (first.first > second.first) it.reversed() else it }
                val range2 = getRange(first.second, second.second).toList()
                    .let { if (first.second > second.second) it.reversed() else it }

                for (i in range1.indices) {
                    (ventsMap[range2[i]][range1[i]])++
                }
            } else {
                println("heh")
            }
        }
        return ventsMap.toList().flatMap { it.toList() }.filter { it > 1 }.size
    }


    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

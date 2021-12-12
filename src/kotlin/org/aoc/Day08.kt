package org.aoc

import readInput

fun main() {

    fun part1(input: List<String>): Int {
        val values = input.map { it.split('|')[1] }.map { it.trim().split(' ') }.flatten()
        return values.filter { it.length != 5 && it.length != 6 }.size
    }

    fun check(value: String, containsValue: String): Boolean {
        containsValue.forEach {
            if (!value.contains(it)) return false
        }
        return true
    }


    fun getKeyNumbers(keys: List<String>, values: List<String>): MutableMap<String, Int> {
        val mutableKeys = keys.toMutableList()
        val map = mutableMapOf<Int, String>()
        map[1] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 2 })
        map[4] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 4 })
        map[7] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 3 })
        map[8] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 7 })

        map[3] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 5 && check(it, map[1]!!) })
        map[9] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 6 && check(it, map[3]!!) })
        map[0] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 6 && check(it, map[7]!!) })
        map[6] = mutableKeys.removeAt(mutableKeys.indexOfFirst { it.length == 6 })
        map[5] = mutableKeys.removeAt(mutableKeys.indexOfFirst { check(map[6]!!, it) })
        map[2] = mutableKeys.first()

        return map.map { it.value to it.key }.toMap().toMutableMap()
    }

    fun part2(input: List<String>): Int {
        val splitted = input.map { it.split('|') }

        var sum = 0

        for (i in splitted.indices) {
            val keys = splitted[i][0].trim().split(' ').map {
                it.toCharArray().sorted().joinToString("") { it.toString() }
            }
            val values = splitted[i][1].trim().split(' ').map {
                it.toCharArray().sorted().joinToString("") { it.toString() }
            }
            val keyNumbers = getKeyNumbers(keys, values)
            sum += values.joinToString("") { keyNumbers[it].toString() }.toInt()
        }

        return sum
    }


    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

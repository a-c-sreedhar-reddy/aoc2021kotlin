fun main() {
    fun convertToBinary(binaryArray: List<Int>): Int {
        return binaryArray.fold(0) { acc, cur ->
            if (cur == 1) {
                acc * 2 + 1
            } else {
                acc * 2
            }
        }
    }

    val input = readInput("Day03")
    val inputLinesDigits = input.map { line -> line.split("").filter { it != "" }.map { digit -> digit.toInt() } }
    val numberOfDigits = input[0].length

    val onesCount = IntArray(numberOfDigits) { 0 }
    inputLinesDigits.forEach { inputLineDigit ->
        inputLineDigit.forEachIndexed { index, digit ->
            if (digit === 1) onesCount[index] = onesCount[index] + 1
        }
    }
    val gammaRateBinary = onesCount.map { count ->
        if (count >= input.size / 2) {
            1
        } else {
            0
        }
    }
    val gammaRate = convertToBinary(gammaRateBinary)
    val epsilonRate = convertToBinary(gammaRateBinary.map { digit -> if (digit === 1) 0 else 1 })


    fun part2(): Int {
        fun getOnesCountAtIndex(list: List<List<Int>>, index: Int): Int {
            return list.filter { cur -> cur[index] === 1 }.size
        }

        val oxygen =
            gammaRateBinary.foldIndexed(inputLinesDigits)
            { index, acc, _ ->

                if (acc.size === 1) {
                    acc
                } else {
                    val onesCount = getOnesCountAtIndex(acc, index)
                    if (onesCount >= acc.size - onesCount) {
                        acc.filter { digits -> digits[index] === 1 }
                    } else {
                        acc.filter { digits -> digits[index] === 0 }
                    }
                }


            }

        val co = gammaRateBinary.foldIndexed(inputLinesDigits) { index, acc, _ ->

            if (acc.size === 1) {
                acc
            } else {
                val onesCount = getOnesCountAtIndex(acc, index)
                if (onesCount >= acc.size - onesCount) {
                    acc.filter { digits -> digits[index] === 0 }
                } else {
                    acc.filter { digits -> digits[index] === 1 }
                }
            }

        }
        return convertToBinary(oxygen[0]) * convertToBinary(co[0])
    }
    println(gammaRate * epsilonRate)
    println(part2())
}
import kotlin.random.Random

class TwiceEvenChecker {
    fun isTwiceEven(number: Int): Boolean {
        val sum = number.toString().map { it.toString().toInt() }.sum()
        val product = number.toString().map { it.toString().toInt() }.reduce { acc, i -> acc * i }
        return sum % 2 == 0 && product % 2 == 0
    }
}

fun calculateAlternatingSum(numbers: List<Int>): Int {
    var sum = 0
    for ((index, number) in numbers.withIndex()) {
        if (index % 2 == 0) {
            sum += number
        } else {
            sum -= number
        }
    }
    return sum
}

fun findMaxTunnelHeight(tunnelCount: Int): Int {
    var roadMaxHeight = Int.MAX_VALUE

    repeat(tunnelCount) {
        val tunnelHeight = readln().toInt()
        roadMaxHeight = minOf(roadMaxHeight, tunnelHeight)
    }

    return roadMaxHeight
}

fun longestSubstringWithoutRepeatingChars(s: String): String {
    val charIndexMap = mutableMapOf<Char, Int>()
    var start = 0
    var maxLength = 0
    var maxStart = 0

    for (end in s.indices) {
        val char = s[end]
        if (charIndexMap.containsKey(char)) {
            start = maxOf(start, charIndexMap[char]!! + 1)
        }
        charIndexMap[char] = end
        if (end - start + 1 > maxLength) {
            maxLength = end - start + 1
            maxStart = start
        }
    }

    return s.substring(maxStart, maxStart + maxLength)
}

fun findMaxElements(matrix: Array<IntArray>): IntArray {
    return matrix.map { it.maxOrNull() ?: 0 }.toIntArray()
}

fun runTask1() {
    val n = readlnOrNull()?.toIntOrNull() ?: 0
    if (n <= 0) {
        println("Введите натуральное число больше 0.")
        return
    }

    val numbers = mutableListOf<Int>()
    repeat(n) {
        val number = readlnOrNull()?.toIntOrNull() ?: 0
        numbers.add(number)
    }

    val alternatingSum = calculateAlternatingSum(numbers)
    println("Знакочередующаяся сумма ряда: $alternatingSum")
}

fun runTask2() {
    val roadCount = readln().toInt()
    var maxHeight = 0
    var bestRoad = 0

    repeat(roadCount) { roadNumber ->
        val tunnelCount = readln().toInt()
        val roadMaxHeight = findMaxTunnelHeight(tunnelCount)

        if (roadMaxHeight > maxHeight) {
            maxHeight = roadMaxHeight
            bestRoad = roadNumber + 1
        }
    }

    println("$bestRoad $maxHeight")
}

fun runTask3() {
    val inputNumber = readlnOrNull()?.toIntOrNull()

    if (inputNumber == null || inputNumber !in 100..999) {
        println("Введите положительное трехзначное число.")
        return
    }

    val checker = TwiceEvenChecker()
    if (checker.isTwiceEven(inputNumber)) {
        println("Число $inputNumber является дважды четным.")
    } else {
        println("Число $inputNumber не является дважды четным.")
    }
}

fun runTask4() {
    val input = "abcadabcbb"
    val result = longestSubstringWithoutRepeatingChars(input)
    println("Наибольшая подстрока без повторяющихся символов: $result")
}

fun runTask5() {
    val rows = readlnOrNull()?.toIntOrNull() ?: return
    val columns = readlnOrNull()?.toIntOrNull() ?: return

    val randomArray = Array(rows) { IntArray(columns) { Random.nextInt(100) } }

    val maxElements = findMaxElements(randomArray)

    println("Максимальные элементы в каждой строке:")
    println(maxElements.joinToString())
}

fun main() {
    runTask1()
    runTask2()
    runTask3()
    runTask4()
    runTask5()
}
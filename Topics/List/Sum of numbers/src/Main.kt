fun solution(numbers: List<Int>): Int {
    var count = 0
    for (number in numbers) {
        count += number
    }
    return count
}
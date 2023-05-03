fun main() {
    val answer = readln()
    val s = "I can('t)? do my homework on time!".toRegex()
    println(s.matches(answer))

}

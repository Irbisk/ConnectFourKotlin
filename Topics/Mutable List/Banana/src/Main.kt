fun solution(strings: MutableList<String>, str: String): MutableList<String> {
    for (i in strings.indices) {
        strings[i] = strings[i].replace(str, "Banana")
    }
    return strings
}

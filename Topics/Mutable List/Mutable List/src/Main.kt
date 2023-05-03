fun names(namesList: List<String>): List<String> {
    var count = 0
    val nameCount = mutableListOf<String>()
    //
    // add your code here
    //
    for (name in namesList) {
        if (!nameCount.contains(name)) {
            nameCount.add(name)
        }
    }
    for (i in nameCount.indices) {
        count = namesList.count { it == nameCount[i] }
        nameCount[i] = "The name ${nameCount[i]} is repeated $count times"
    }

    return nameCount
}
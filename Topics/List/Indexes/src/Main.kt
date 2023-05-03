fun solution(products: List<String>, product: String) {
    for (i in 0..products.lastIndex) {
        if (products.get(i) == product) print("$i ")
    }
}
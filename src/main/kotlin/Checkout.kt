class Checkout(
    private val pricing: (List<Item>) -> Double
) {
    private val items = mutableListOf<Item>()

    fun scan(item: Item) {
        items.add(item)
    }

    fun total(): Double = pricing(items)
}
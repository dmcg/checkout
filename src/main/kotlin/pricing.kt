
typealias Pricing = (items: List<Item>) -> Double
typealias DiscountPolicy = (base: Pricing, items: List<Item>) -> Double

infix fun DiscountPolicy.on(base: Pricing): Pricing = { items ->
    this(base, items)
}

fun justAdd(items: List<Item>): Double = items.sumByDouble { it.price.toDouble() }


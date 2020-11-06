
fun travelCardDiscount(
    base: (List<Item>) -> Double,
    items: List<Item>
): Double {
    val travelCardCount = items.count { it.code == "001" }
    val travelCardDiscount = if (travelCardCount > 1) travelCardCount * 0.75 else 0.0
    return base(items) - travelCardDiscount
}

fun basketTotalDiscount(
    base: (List<Item>) -> Double,
    items: List<Item>
): Double {
    val base = base(items)
    return if (base > 60) base * 0.9 else base
}

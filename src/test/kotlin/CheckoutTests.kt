import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CheckoutTests {

    private val item1 = Item("001", "Travel Card Holder", 9.95)
    private val item2 = Item("002", "Personalised cufflinks", 45.00)
    private val item3 = Item("003", "Kids T-shirt", 19.95)

    @Test
    fun `just add calculates the plain sum`() {
        val co = Checkout(Unit)
        co.scan(item1)
        co.scan(item2)
        co.scan(item3)
        assertEquals(74.90, co.total())
    }
}

data class Item(
    val code: String,
    val name: String,
    val price: Number
) {

}

class Checkout(pricing: Any) {
    private val items = mutableListOf<Item>()

    fun scan(item: Item) {
        items.add(item)
    }

    fun total(): Double = items.sumByDouble { it.price.toDouble() }
}

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CheckoutTests {

    private val item1 = Item("001", "Travel Card Holder", 9.95)
    private val item2 = Item("002", "Personalised cufflinks", 45.00)
    private val item3 = Item("003", "Kids T-shirt", 19.95)

    @Test
    fun `JustAdd calculates the plain sum`() {
        val co = Checkout(JustAdd)
        co.scan(item1)
        co.scan(item2)
        co.scan(item3)
        assertEquals(74.90, co.total())
    }

    @Test
    fun `10% off over £60`() {
        val co = Checkout(BasketTotalDiscount)
        co.scan(item2)
        assertEquals(45.00, co.total())

        co.scan(item2)
        assertEquals(81.00, co.total())
    }
}

object BasketTotalDiscount : (List<Item>) -> Double {
    override fun invoke(items: List<Item>): Double {
        val base = items.sumByDouble { it.price.toDouble() }
        return if (base > 60) base * 0.9 else base
    }
}

object JustAdd : (List<Item>) -> Double {
    override fun invoke(items: List<Item>): Double = items.sumByDouble { it.price.toDouble() }
}

data class Item(
    val code: String,
    val name: String,
    val price: Number
)

class Checkout(
    private val pricing: (List<Item>) -> Double
) {
    private val items = mutableListOf<Item>()

    fun scan(item: Item) {
        items.add(item)
    }

    fun total(): Double = pricing(items)
}

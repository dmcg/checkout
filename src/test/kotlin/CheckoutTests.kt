import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CheckoutTests {

    private val item1 = Item("001", "Travel Card Holder", 9.25)
    private val item2 = Item("002", "Personalised cufflinks", 45.00)
    private val item3 = Item("003", "Kids T-shirt", 19.95)

    private val combinedPricing = ::basketTotalDiscount on
        (::travelCardDiscount on ::justAdd)

    @Test
    fun `JustAdd calculates the plain sum`() {
        val co = Checkout(::justAdd)
        co.scan(item1)
        co.scan(item2)
        co.scan(item3)
        assertEquals(74.20, co.total())
    }

    @Test
    fun `10% off over £60`() {
        val co = Checkout(::basketTotalDiscount on ::justAdd)
        co.scan(item2)
        assertEquals(45.00, co.total())

        co.scan(item2)
        assertEquals(81.00, co.total())
    }

    @Test
    fun `cut price travel card holder`() {
        val co = Checkout(::travelCardDiscount on ::justAdd)
        co.scan(item1)
        assertEquals(9.25, co.total())

        co.scan(item1)
        assertEquals(17.00, co.total())
    }

    @Test
    fun `acceptance test 1`() {
        val co = Checkout(combinedPricing)
        co.scan(item1)
        co.scan(item2)
        co.scan(item3)
        assertEquals(66.78, co.total())
    }

    @Test
    fun `acceptance test 2`() {
        val co = Checkout(combinedPricing)
        co.scan(item1)
        co.scan(item3)
        co.scan(item1)
        assertEquals(36.95, co.total())
    }

    @Test
    fun `acceptance test 3`() {
        val co = Checkout(combinedPricing)
        co.scan(item1)
        co.scan(item2)
        co.scan(item1)
        co.scan(item3)
        assertEquals(73.76, co.total(), 0.01)
    }
}


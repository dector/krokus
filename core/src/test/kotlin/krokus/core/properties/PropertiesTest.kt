package krokus.core.properties

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PropertiesTest {

    @Test
    @DisplayName("[ScalarProducer] Create from value")
    fun scalarProducer_createFromValue() {
        val producer = ScalarProducer(10)

        assertEquals(10, producer.value)
        assertEquals(10, producer())
    }

    @Test
    @DisplayName("[ScalarProducer] Create from another producer")
    fun scalarProducer_createFromAnother() {
        val producer = ScalarProducer(ScalarProducer(10))

        assertEquals(10, producer.value)
        assertEquals(10, producer())
    }

    @Test
    @DisplayName("[ScalarProducer] Unpack and replace existing value")
    fun scalarProducer_unpackExisting() {
        val property = Property.from(10)

        assertDoesNotThrow {
            val value = property.unpackOrThrow()

            assertEquals(10, value)
        }
    }

    @Test
    @DisplayName("[ScalarProducer] Catch exception when unpacking non-scalar value")
    fun scalarProducer_unpackExistingThrows() {
        val property = Property.from { 10 }

        assertThrows(IllegalStateException::class.java) {
            property.unpackOrThrow()
        }
    }

    @Test
    @DisplayName("[ScalarProducer] Try to unpack but set new value")
    fun scalarProducer_replaceExisting() {
        val property = Property.from { 15 }

        assertDoesNotThrow {
            val value = property.unpackOrSet { 10 }

            assertEquals(10, value)
        }
    }
}
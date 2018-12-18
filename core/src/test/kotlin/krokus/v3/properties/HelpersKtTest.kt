package krokus.v3.properties

import assertk.assert
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.Test

internal class HelpersKtTest {

    @Test
    fun zeroAng3() {
        val value = MutableAng3(
            x = 0.0,
            y = 0.0,
            z = 0.0
        )

        assert(value.isNotZero()).isFalse()
    }

    @Test
    fun nonAng3WithX() {
        val value = MutableAng3(
            x = 90.0
        )

        assert(value.isNotZero()).isTrue()
    }

    @Test
    fun nonAng3WithY() {
        val value = MutableAng3(
            y = 90.0
        )

        assert(value.isNotZero()).isTrue()
    }

    @Test
    fun nonAng3WithZ() {
        val value = MutableAng3(
            z = 90.0
        )

        assert(value.isNotZero()).isTrue()
    }
}
package krokus.v3

import assertk.all
import assertk.assert
import assertk.assertions.isNotNull
import assertk.catch
import krokus.v3.ext.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class V3Tests {

    @Test
    fun createSimpleCube() {
        val cube = CubeImpl()

        assert(cube).all {
            hasWidth(1)
            hasDepth(1)
            hasHeight(1)

            hasPositionX(0)
            hasPositionY(0)
            hasPositionZ(0)

            hasRotationX(0)
            hasRotationY(0)
            hasRotationZ(0)

            hasColor(Color.Black)
        }
    }

    @Test
    @DisplayName("Modifying base (immutable) color should throw Error")
    fun modifyingBaseColorException() {
        val color = Color.Black

        val exception = catch {
            color.r = 1.0
        }
        assert(exception).isNotNull()
    }

    @Test
    fun mutateBaseColor() {
        val color = Color.Black
        val newColor = color.asMutable().apply {
            r = 1.0
            g = 1.0
            b = 1.0
        }

        assert(newColor).all {
            hasRedComponent(1.0)
            hasGreenComponent(1.0)
            hasBlueComponent(1.0)
        }
    }
}
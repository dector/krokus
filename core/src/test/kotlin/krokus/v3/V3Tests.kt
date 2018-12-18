package krokus.v3

import assertk.all
import assertk.assert
import krokus.v3.bodies.Cube.Origin.Center
import krokus.v3.bodies.MutableCube
import krokus.v3.ext.*
import krokus.v3.properties.Color
import krokus.v3.properties.MutableDimensions
import krokus.v3.properties.mutate
import org.junit.jupiter.api.Test

internal class V3Tests {

    @Test
    fun createSimpleCube() {
        val cube = MutableCube()

        assert(cube).all {
            hasSize(1)

            hasWidth(1)
            hasDepth(1)
            hasHeight(1)

            hasOrigin(Center)

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
    fun createSimpleCubeWithCustomSize() {
        val cube = MutableCube().apply {
            size = MutableDimensions(10.0, 20.0, 30.0)
        }

        assert(cube).all {
            hasWidth(10)
            hasDepth(20)
            hasHeight(30)
        }
    }

    @Test
    fun mutateBaseColor() {
        val color = Color.Black
        val newColor = color.mutate().apply {
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
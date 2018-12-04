package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.geometry.data.primitiveCubeCentered
import io.github.dector.krokus.core.geometry.data.primitiveCubeDeclarative
import io.github.dector.krokus.core.geometry.data.primitiveCubeDeclarativeReSetSize
import io.github.dector.krokus.core.geometry.data.wheels
import io.github.dector.krokus.core.geometry.shape.Cube
import io.github.dector.krokus.core.geometry.shape.Cylinder
import io.github.dector.krokus.core.operation.Union
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CubeBuildersTest {

    @Test
    @DisplayName("Simple cube")
    fun simpleCube() {
        val geom = primitiveCubeCentered()

        assertEquals(10.0, geom.shape().size.x())
        assertEquals(10.0, geom.shape().size.y())
        assertEquals(10.0, geom.shape().size.z())
    }

    @Test
    @DisplayName("Simple cube (Builder)")
    fun simpleCubeWithBuilder() {
        val geom = primitiveCubeDeclarative()

        assertEquals(10.0, geom.shape().size.x())
        assertEquals(10.0, geom.shape().size.y())
        assertEquals(10.0, geom.shape().size.z())
    }

    @Test
    @DisplayName("Simple cube (Builder with updated values)")
    fun simpleCubeWithBuilderUpdated() {
        val geom = primitiveCubeDeclarativeReSetSize()

        assertEquals(10.0, geom.shape().size.x())
        assertEquals(10.0, geom.shape().size.y())
        assertEquals(10.0, geom.shape().size.z())
    }

    @Test
    @DisplayName("Simple union with reactive properties")
    fun simpleUnion() {
        val geom1 = wheels() as Union

        val children = geom1.children()
        assertEquals(3, children.size)

        (children[0] as ShapeGeometry<*>).let { axis ->
            (axis.shape() as Cylinder).let { shape ->
                assertEquals(30.0, shape.height)
                assertEquals(5.0, shape.radius)
            }
        }

        (children[1] as ShapeGeometry<*>).let { wheel ->
            (wheel.shape() as Cube).let { shape ->
                assertEquals(10.0, shape.size.x())
                assertEquals(10.0, shape.size.y())
                assertEquals(10.0, shape.size.z())

                val position = wheel.translation().position()
                assertEquals(0.0, position.x())
                assertEquals(0.0, position.y())
                assertEquals(30.0/2 + 10.0/2, position.z())
            }
        }

        (children[2] as ShapeGeometry<*>).let { wheel ->
            (wheel.shape() as Cube).let { shape ->
                assertEquals(10.0, shape.size.x())
                assertEquals(10.0, shape.size.y())
                assertEquals(10.0, shape.size.z())

                val position = wheel.translation().position()
                assertEquals(-0.0, position.x())
                assertEquals(-0.0, position.y())
                assertEquals(-(30.0/2 + 10.0/2), position.z())
            }
        }
    }
}
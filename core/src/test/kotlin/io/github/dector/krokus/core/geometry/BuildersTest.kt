package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.geometry.data.primitiveCubeCentered
import io.github.dector.krokus.core.geometry.data.primitiveCubeDeclarative
import io.github.dector.krokus.core.geometry.data.primitiveCubeDeclarativeReSetSize
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
}
package io.github.dector.krokus.core.geometry.data

import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.geometry.shape.Cube
import io.github.dector.krokus.core.geometry.size
import io.github.dector.krokus.core.space.v


fun primitiveCubeCentered() =
    cube(10)

fun primitiveCubeDeclarative() =
    cube {
        size(10)
    }

fun primitiveCubeDeclarativeReSetSize() =
    cube {
        // For test purposes only. Use API instead
        shape.set { Cube(size = v(15)) }

        size(10)
    }
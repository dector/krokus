package io.github.dector.krokus.core.geometry.data

import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.geometry.cylinder
import io.github.dector.krokus.core.geometry.shape.Cube
import io.github.dector.krokus.core.geometry.size
import io.github.dector.krokus.core.operation.union
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.unaryMinus
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

fun wheels() = union {
    val axis = cylinder(30, 5)
        .appendHere()

    fun wheelsPositionDiff(shape: Cube) = Vector3().apply {
        z.set { axis.shape().height / 2 + shape.size.z() / 2 }
    }

    +cube {
        size(10)
        translation()
            .position.set(wheelsPositionDiff(shape()))
    }

    +cube {
        size(10)
        translation()
            .position.set(-wheelsPositionDiff(shape()))
    }
}
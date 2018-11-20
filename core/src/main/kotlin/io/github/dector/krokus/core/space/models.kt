package io.github.dector.krokus.core.space

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.properties.Scalar
import io.github.dector.krokus.core.properties.isZero

data class Vector2(val x: Float = 0f, val y: Float = 0f)

data class Vector3(
    var x: Property<Double> = Scalar(0.0),
    var y: Property<Double> = Scalar(0.0),
    var z: Property<Double> = Scalar(0.0)
) {

    companion object {

        val Origin = Vector3()
    }
}

val Vector3.isZero: Boolean get() = x.isZero && y.isZero && z.isZero
val Vector3.isNotZero: Boolean get() = !isZero
val Vector3.allAreEqual: Boolean get() = x == y && x == z

data class Angle3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {

    val isZero = x == 0.0 && y == 0.0 && z == 0.0
    val isNotZero = !isZero
}

enum class Plane {
    None, XY, XZ, YZ
}
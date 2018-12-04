package io.github.dector.krokus.core.space

import io.github.dector.krokus.core.properties.Property


data class Vector2(val x: Float = 0f, val y: Float = 0f)

data class Vector3(
    var x: Property<Double> = Property.from(0.0),
    var y: Property<Double> = Property.from(0.0),
    var z: Property<Double> = Property.from(0.0)
) {

    companion object {

        val Origin = Vector3()
        val Empty = Vector3()
    }
}

// TODO move
val Double.isZero: Boolean get() = this == 0.0

val Vector3.isZero: Boolean get() = x.ref().isZero && y.ref().isZero && z.ref().isZero
val Vector3.isNotZero: Boolean get() = !isZero
val Vector3.allAreEqual: Boolean get() = x.ref() == y.ref() && x.ref() == z.ref()

data class Angle3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {

    val isZero = x == 0.0 && y == 0.0 && z == 0.0
    val isNotZero = !isZero
}

enum class Plane {
    None, XY, XZ, YZ
}
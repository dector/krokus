package krokus.core.space

import krokus.core.properties.Property


data class Vector2(val x: Float = 0f, val y: Float = 0f)

data class Vector3(
    val x: Property<Double> = Property.from(0.0),
    val y: Property<Double> = Property.from(0.0),
    val z: Property<Double> = Property.from(0.0)
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

operator fun Vector3.unaryMinus() = let { current ->
    Vector3(
        x = Property.from { -current.x() },
        y = Property.from { -current.y() },
        z = Property.from { -current.z() }
    )
}

data class Angle3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {

    val isZero = x == 0.0 && y == 0.0 && z == 0.0
    val isNotZero = !isZero
}

enum class Plane {
    None, XY, XZ, YZ
}
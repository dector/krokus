package io.github.dector.krokus.core.space

import io.github.dector.krokus.core.properties.Property


// Vector 2

operator fun Vector2.plus(other: Vector2) = Vector2(x = x + other.x, y = y + other.y)
operator fun Vector2.plus(value: Int) = Vector2(x = x + value, y = y + value)

operator fun Vector2.minus(value: Int) = Vector2(x = x - value, y = y - value)

infix fun Vector2.scaleEachBy(other: Vector2) = Vector2(x = x * other.x, y = y * other.y)

// Vector 3

operator fun Vector3.plus(other: Vector3) = Vector3(
    x = Property.from { x.ref() + other.x.ref() },
    y = Property.from { y.ref() + other.y.ref() },
    z = Property.from { z.ref() + other.z.ref() }
)

operator fun (() -> Vector3).plus(other: () -> Vector3) =
    { this() + other() }
operator fun (() -> Vector3).plus(other: Vector3) =
    plus { other }

//operator fun Property<Vector3>.plus(other: Property<Vector3>) =
//    Computable { ref + other.ref }
//
//operator fun Property<Vector3>.plus(other: Vector3) =
//    plus(other.asScalar())

//operator fun Vector3.plus(ref: Int) = Vector3(xProp = xProp + ref, y = y + ref, z = z + ref)
//
//operator fun Vector3.minus(other: Vector3) = Vector3(xProp = xProp - other.xProp, y = y - other.y, z = z - other.z)
//operator fun Vector3.minus(ref: Int) = Vector3(xProp = xProp - ref, y = y - ref, z = z - ref)
//
//operator fun Vector3.times(ref: Int) = Vector3(xProp * ref, y * ref, z * ref)
//
//operator fun Vector3.div(ref: Int) = Vector3(xProp / ref, y / ref, z / ref)

// Angle

operator fun Angle3.plus(other: Angle3) = Angle3(x = x + other.x, y = y + other.y, z = z + other.z)
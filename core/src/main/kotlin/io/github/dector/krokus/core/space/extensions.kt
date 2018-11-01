package io.github.dector.krokus.core.space

import kotlin.math.absoluteValue
import kotlin.math.min


fun Vector3.minValue() = min(min(x, y), z)

val Vector3.corners: List<Vector3>
    get() = listOf(
        v(0, 0, 0),
        v(x, 0, 0),
        v(x, y, 0),
        v(x, 0, z),
        v(x, y, z),
        v(0, y, 0),
        v(0, y, z),
        v(0, 0, z)
    )

val Vector3.absolute: Vector3
    get() = v(x.absoluteValue, y.absoluteValue, z.absoluteValue)
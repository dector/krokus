package io.github.dector.krokus.api.vector

import kotlin.math.min


fun Vector3.minValue() = min(min(x, y), z)

val Vector3.corners: List<Vector3>
    get() = listOf(
        xyz(0, 0, 0),
        xyz(x, 0, 0),
        xyz(x, y, 0),
        xyz(x, 0, z),
        xyz(x, y, z),
        xyz(0, y, 0),
        xyz(0, y, z),
        xyz(0, 0, z)
    )
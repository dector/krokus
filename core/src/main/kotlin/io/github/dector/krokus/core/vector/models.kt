package io.github.dector.krokus.core.vector

data class Vector2(val x: Float = 0f, val y: Float = 0f)
data class Vector3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {

    val isZero: Boolean
        get() = x == 0.0 && y == 0.0 && z == 0.0
}
package io.github.dector.krokus.core.space

data class Vector2(val x: Float = 0f, val y: Float = 0f)

data class Vector3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {

    companion object {

        val Origin = Vector3()
    }

    val isZero = x == 0.0 && y == 0.0 && z == 0.0
    val isNotZero = !isZero
    val allAreEqual = x == y && x == z
}

data class Angle3(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {

    val isZero = x == 0.0 && y == 0.0 && z == 0.0
    val isNotZero = !isZero
}

enum class Plane {
    None, XY, XZ, YZ
}
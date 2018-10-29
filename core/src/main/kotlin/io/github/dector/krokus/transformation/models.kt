package io.github.dector.krokus.transformation

import io.github.dector.krokus.vector.Vector3


sealed class Transformation
data class Translate(val pos: Vector3) : Transformation()
//data class Scale(val empty: Unit = Unit) : Transformation()
data class Rotate(val angleX: Float = 0f, val angleY: Float = 0f, val angleZ: Float = 0f) : Transformation()
data class Mirror(val plane: MirrorPlane = MirrorPlane.None) : Transformation()

enum class MirrorPlane {
    None, X, Y, Z
}
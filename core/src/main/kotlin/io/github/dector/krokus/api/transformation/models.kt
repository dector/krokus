package io.github.dector.krokus.api.transformation

import io.github.dector.krokus.api.vector.Vector3


//data class Mirror(val plane: MirrorPlane = MirrorPlane.None) : Transformation()

enum class MirrorPlane {
    None, X, Y, Z
}

interface Transformation

data class Translation(val position: Vector3 = Vector3()) :
    Transformation {

    val isZero: Boolean
        get() = position.isZero

    val isNotZero: Boolean
        get() = !isZero
}

data class Rotation(val angleX: Number = 0f, val angleY: Number = 0f, val angleZ: Number = 0f) :
    Transformation {

    val isZero: Boolean
        get() = angleX == 0 && angleY == 0 && angleZ == 0

    val isNotZero: Boolean
        get() = !isZero
}

data class Transformations(
    val translation: Translation = Translation(),
    val rotation: Rotation = Rotation()
)
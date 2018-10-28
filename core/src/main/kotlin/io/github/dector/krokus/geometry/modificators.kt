package io.github.dector.krokus.geometry

import io.github.dector.krokus.transformation.Translate
import io.github.dector.krokus.vector.Vector3


fun Geometry.atPos(pos: Vector3) = also {
    setTransformation(Translate(pos))
}

fun Cube.centered() = copy(centered = true)


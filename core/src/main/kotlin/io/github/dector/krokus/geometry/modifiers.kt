package io.github.dector.krokus.geometry

import io.github.dector.krokus.transformation.Mirror
import io.github.dector.krokus.transformation.MirrorPlane
import io.github.dector.krokus.transformation.Translate
import io.github.dector.krokus.vector.Vector3
import io.github.dector.krokus.vector.v


fun Geometry.atPos(pos: Vector3) = also {
    setTransformation(Translate(pos))
}

fun Geometry.atPosZ(z: Number) = also {
    setTransformation(Translate(v(0, 0, z)))
}

fun Geometry.mirrorVertically() =
    Contained(this).apply { setTransformation(Mirror(MirrorPlane.Z)) }

fun Cube.centered() = copy(centered = true)


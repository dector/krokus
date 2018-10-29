package io.github.dector.krokus.geometry

import io.github.dector.krokus.transformation.Mirror
import io.github.dector.krokus.transformation.MirrorPlane
import io.github.dector.krokus.transformation.Rotate
import io.github.dector.krokus.transformation.Translate
import io.github.dector.krokus.vector.Vector3
import io.github.dector.krokus.vector.v


fun Geometry.atPos(pos: Vector3) = apply {
    setTransformation(Translate(pos))
}

fun Geometry.atPosZ(z: Number) = apply {
    setTransformation(Translate(v(0, 0, z)))
}

fun Geometry.rotateX(angleDeg: Float) = apply {
    setTransformation(Rotate(angleX = angleDeg))
}
fun Geometry.rotateX(angleDeg: Int) = rotateX(angleDeg.toFloat())

fun Geometry.rotateY(angleDeg: Float) = apply {
    setTransformation(Rotate(angleY = angleDeg))
}
fun Geometry.rotateY(angleDeg: Int) = rotateY(angleDeg.toFloat())

fun Geometry.mirrorVertically() = Contained(this).apply {
    setTransformation(Mirror(MirrorPlane.Z))
}

fun Cube.centered() = copy(centered = true)


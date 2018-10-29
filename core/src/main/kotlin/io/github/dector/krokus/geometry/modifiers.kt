package io.github.dector.krokus.geometry

import io.github.dector.krokus.transformation.Mirror
import io.github.dector.krokus.transformation.MirrorPlane
import io.github.dector.krokus.transformation.Rotate
import io.github.dector.krokus.transformation.Translate
import io.github.dector.krokus.vector.Vector3
import io.github.dector.krokus.vector.v


fun Geometry.atPos(pos: Vector3) = copyWithTransformation(Translate(pos))
fun Geometry.atPos(x: Number, y: Number, z: Number) = atPos(v(x, y, z))

fun Geometry.atPosZ(z: Number) = copyWithTransformation(Translate(v(0, 0, z)))

fun Geometry.rotateX(angleDeg: Float) = copyWithTransformation(Rotate(angleX = angleDeg))

fun Geometry.rotateX(angleDeg: Int) = rotateX(angleDeg.toFloat())

fun Geometry.rotateY(angleDeg: Float) = copyWithTransformation(Rotate(angleY = angleDeg))

fun Geometry.rotateY(angleDeg: Int) = rotateY(angleDeg.toFloat())

fun Geometry.mirrorVertically() = Contained(this, listOf(Mirror(MirrorPlane.Z)))


fun Cube.centered() = copy(centered = true)


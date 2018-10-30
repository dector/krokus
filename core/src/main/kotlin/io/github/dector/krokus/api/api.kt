package io.github.dector.krokus.api

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.space.*
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Translation


fun Geometry.moveTo(position: Vector3) = addTransformation(Translation(transformations.translation.position + position))
fun Geometry.moveToX(value: Number) = moveTo(vx(value))
fun Geometry.moveToY(value: Number) = moveTo(vy(value))
fun Geometry.moveToZ(value: Number) = moveTo(vz(value))

fun Geometry.moveByY(value: Number) = moveTo(transformations.translation.position + vy(value))

fun Geometry.rotate(angle: Angle3) = addTransformation(Rotation(angle))
fun Geometry.rotateX(angle: Number) = rotate(ax(angle))
fun Geometry.rotateY(angle: Number) = rotate(ay(angle))
fun Geometry.rotateZ(angle: Number) = rotate(az(angle))

fun Geometry.mirror(plane: Plane) = addTransformation(Mirroring(plane))
fun Geometry.mirrorVertically() = mirror(Plane.XY)
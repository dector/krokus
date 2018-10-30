package io.github.dector.krokus.api

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.space.*
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Translation


fun <G : Geometry> G.moveTo(position: Vector3) = setTransformation(Translation(position)) as G
fun Geometry.moveToX(value: Number) = moveTo(vx(value))
fun Geometry.moveToY(value: Number) = moveTo(vy(value))
fun Geometry.moveToZ(value: Number) = moveTo(vz(value))

fun Geometry.moveByY(value: Number) = moveTo(transformations.translation.position + vy(value))

fun <G : Geometry> G.rotateAt(angle: Angle3) = setTransformation(Rotation(angle)) as G
fun Geometry.rotateAtX(angle: Number) = rotateAt(ax(angle))
fun Geometry.rotateAtY(angle: Number) = rotateAt(ay(angle))
fun Geometry.rotateAtZ(angle: Number) = rotateAt(az(angle))

fun <G : Geometry> G.mirror(plane: Plane) = setTransformation(Mirroring(plane)) as G
fun Geometry.mirrorVertically() = this.mirror(Plane.XY)

fun <G : Geometry> G.multiply(count: Int): List<G> = mutableListOf<G>().also {
    for (i in 1..count) it.add(this)
}

fun <G : Geometry> G.withModified(modifier: (G) -> G) = listOf(
    this,
    modifier(this)
)
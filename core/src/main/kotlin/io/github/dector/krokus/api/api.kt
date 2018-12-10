package io.github.dector.krokus.api

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.plus
import io.github.dector.krokus.core.space.v


//fun Geometry.moveTo(position: Property<Vector3>) = apply {
//    translation = Computable { Translation(position) }
//}

fun Geometry.moveTo(xyz: Vector3) = apply {
    translation.ref().position.set(xyz)
}
fun Geometry.moveTo(xyz: Number) = moveTo(v(xyz))

fun Geometry.moveBy(delta: Vector3) = apply {
    translation.ref().position.update { it + delta }
}
//fun <G : Geometry> G.moveTo(position: Vector3) = setTransformation(Translation(position)) as G
//fun Geometry.moveToX(ref: Number) = moveTo(vx(ref))
//fun Geometry.moveToY(ref: Number) = moveTo(vy(ref))
//fun Geometry.moveToZ(ref: Number) = moveTo(vz(ref))
//fun Geometry.moveTo(xyz: Number) = moveTo(xyz, xyz, xyz)
//fun Geometry.moveTo(xProp: Number = 0, y: Number = 0, z: Number = 0) = moveTo(v(xProp, y, z))

//fun Geometry.moveBy(ref: Vector3) =
//    moveTo(transformations.translation.position + ref)
//
//fun Geometry.moveBy(xProp: Number = 0, y: Number = 0, z: Number = 0) =
//    moveBy(v(xProp, y, z))
//
//fun Geometry.moveBy(ref: Number) =
//    moveBy(ref, ref, ref)
//
//@Deprecated("", ReplaceWith("moveBy(y = ref)"))
//fun Geometry.moveByY(ref: Number) = moveBy(y = ref)
//
//fun <G : Geometry> G.rotateAt(angle: Angle3) = setTransformation(Rotation(angle)) as G
//fun Geometry.rotateAtX(angle: Number) = rotateAt(ax(angle))
//fun Geometry.rotateAtY(angle: Number) = rotateAt(ay(angle))
//fun Geometry.rotateAtZ(angle: Number) = rotateAt(az(angle))
//
//fun <G : Geometry> G.mirror(plane: Plane) = setTransformation(Mirroring(plane)) as G
//fun Geometry.mirrorVertically() = this.mirror(Plane.XY)
//
//fun <G : Geometry> G.multiply(count: Int): List<G> = mutableListOf<G>().also {
//    for (i in 1..count) it.add(this)
//}
//
//fun <G : Geometry> G.withModified(modifier: (G) -> G) = listOf(
//    this,
//    modifier(this)
//)
//
//@Deprecated("Use from api", ReplaceWith("cornerOrigin()"))
//fun ShapeGeometry<Cube>.uncenter() = moveBy(shape.size / 2)

fun Geometry.moveTo(prop: Property<Vector3>) = apply {
    translation().position.set(prop)
}
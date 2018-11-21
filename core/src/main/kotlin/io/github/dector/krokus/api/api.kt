package io.github.dector.krokus.api

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.properties.asScalar
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.v
import io.github.dector.krokus.core.transformation.Translation


fun Geometry.moveTo(position: Property<Vector3>) = this.apply {
    translation = Translation(position).asScalar()
}

fun Geometry.moveTo(xyz: Number) = moveTo(v(xyz).asScalar())

//fun <G : Geometry> G.moveTo(position: Vector3) = setTransformation(Translation(position)) as G
//fun Geometry.moveToX(value: Number) = moveTo(vx(value))
//fun Geometry.moveToY(value: Number) = moveTo(vy(value))
//fun Geometry.moveToZ(value: Number) = moveTo(vz(value))
//fun Geometry.moveTo(xyz: Number) = moveTo(xyz, xyz, xyz)
//fun Geometry.moveTo(x: Number = 0, y: Number = 0, z: Number = 0) = moveTo(v(x, y, z))

//fun Geometry.moveBy(value: Vector3) =
//    moveTo(transformations.translation.position + value)
//
//fun Geometry.moveBy(x: Number = 0, y: Number = 0, z: Number = 0) =
//    moveBy(v(x, y, z))
//
//fun Geometry.moveBy(value: Number) =
//    moveBy(value, value, value)
//
//@Deprecated("", ReplaceWith("moveBy(y = value)"))
//fun Geometry.moveByY(value: Number) = moveBy(y = value)
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
//@Deprecated("Use new api", ReplaceWith("cornerOrigin()"))
//fun ShapeGeometry<Cube>.uncenter() = moveBy(shape.size / 2)
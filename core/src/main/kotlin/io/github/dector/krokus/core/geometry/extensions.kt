package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.plus
import io.github.dector.krokus.core.space.vy
import io.github.dector.krokus.core.space.vz


fun Geometry.findSide(side: Side, applyTranslation: Boolean = true) = when (this) {
    is ShapeGeometry<*> -> when (shape) {
        is CylinderDep -> when (side) {
            Side.Top -> fromPosition(vz(shape.height / 2), applyTranslation)
            Side.Bottom -> fromPosition(vz(-shape.height / 2), applyTranslation)
            else -> throw NotImplementedError()
        }
        is Cube -> when (side) {
            Side.Top -> fromPosition(vz(shape.size.z / 2), applyTranslation)
            Side.Bottom -> fromPosition(vz(-shape.size.z / 2), applyTranslation)
//            Side.Left -> fromPosition(vx(-shape.size.z / 2), applyTranslation)
            Side.Front -> fromPosition(vy(-shape.size.y / 2), applyTranslation)
            else -> throw NotImplementedError()
        }
        is Prism -> when (side) {
            Side.Top -> fromPosition(vz(shape.height / 2), applyTranslation)
            else -> throw NotImplementedError()
        }
        else -> throw NotImplementedError()
    }
    else -> throw NotImplementedError()
}

private fun Geometry.fromPosition(delta: Vector3, applyTranslation: Boolean) =
    (if (applyTranslation) transformations.translation.position else Vector3.Origin) + delta

enum class Side {
    Top, Bottom, Left, Front
}

val ShapeGeometry<CylinderDep>.height: Double
    get() = shape.height
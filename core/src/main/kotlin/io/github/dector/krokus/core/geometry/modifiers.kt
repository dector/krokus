package io.github.dector.krokus.core.geometry


//fun Geometry.rotateAtX(angleDeg: Float) = copyWithTransformation(Rotate(x = angleDeg))
//
//fun Geometry.rotateAtX(angleDeg: Int) = rotateAtX(angleDeg.toFloat())
//
//fun Geometry.rotateAtY(angleDeg: Float) = copyWithTransformation(Rotate(y = angleDeg))
//
//fun Geometry.rotateAtY(angleDeg: Int) = rotateAtY(angleDeg.toFloat())
//
//fun Geometry.mirrorVertically() = Contained(this, listOf(Mirror(Plane.XY)))


//fun Cube.centered() = copy(centered = true)

fun ShapeGeometry<Cube>.resizeXBy(value: Number) = copy(shape = shape.copy(size = shape.size.copy(x = shape.size.x + value.toDouble())))
package io.github.dector.krokus.core.geometry


//fun Geometry.rotateX(angleDeg: Float) = copyWithTransformation(Rotate(x = angleDeg))
//
//fun Geometry.rotateX(angleDeg: Int) = rotateX(angleDeg.toFloat())
//
//fun Geometry.rotateY(angleDeg: Float) = copyWithTransformation(Rotate(y = angleDeg))
//
//fun Geometry.rotateY(angleDeg: Int) = rotateY(angleDeg.toFloat())
//
//fun Geometry.mirrorVertically() = Contained(this, listOf(Mirror(Plane.XY)))


//fun Cube.centered() = copy(centered = true)

fun ShapeGeometry<Cube>.resizeXBy(value: Number) = copy(shape = shape.copy(size = shape.size.copy(x = shape.size.x + value.toDouble())))
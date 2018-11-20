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

//fun ShapeGeometry<Cube>.resizeXBy(value: Number) = copy(shape = shape.copy(size = shape.size.copy(x = shape.size.x + value.toDouble())))
//
//fun ShapeGeometry<Cube>.resizeBy(x: Number = 0, y: Number = 0, z: Number = 0) =
//        copy(shape = shape.copy(size = shape.size + v(x, y, z)))
//fun ShapeGeometry<Cube>.resizeBy(value: Number): ShapeGeometry<Cube> =
//        resizeBy(value, value, value)
//
//fun ShapeGeometry<Cube>.cornerOrigin() =
//        copy(shape = shape.copy(origin = Cube.Origin.Corner))
//
//fun ShapeGeometry<Cylinder>.bottomOrigin() =
//        copy(shape = shape.copy(origin = Cylinder.Origin.Bottom))
//
//// TODO resolve platform clash
//fun ShapeGeometry<Cone>.bottomOriginCone() =
//        copy(shape = shape.copy(origin = Cone.Origin.Bottom))
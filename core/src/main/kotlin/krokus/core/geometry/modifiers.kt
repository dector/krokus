package krokus.core.geometry

import krokus.core.geometry.shape.Cube
import krokus.core.properties.Property


//fun Geometry.rotateAtX(angleDeg: Float) = copyWithTransformation(Rotate(xProp = angleDeg))
//
//fun Geometry.rotateAtX(angleDeg: Int) = rotateAtX(angleDeg.toFloat())
//
//fun Geometry.rotateAtY(angleDeg: Float) = copyWithTransformation(Rotate(y = angleDeg))
//
//fun Geometry.rotateAtY(angleDeg: Int) = rotateAtY(angleDeg.toFloat())
//
//fun Geometry.mirrorVertically() = Contained(this, listOf(Mirror(Plane.XY)))


//fun Cube.centered() = copy(centered = true)

//fun ShapeGeometry<Cube>.resizeXBy(ref: Number) = copy(shape = shape.copy(size = shape.size.copy(xProp = shape.size.xProp + ref.toDouble())))
//
//fun ShapeGeometry<Cube>.resizeBy(xProp: Number = 0, y: Number = 0, z: Number = 0) =
//        copy(shape = shape.copy(size = shape.size + v(xProp, y, z)))
//fun ShapeGeometry<Cube>.resizeBy(ref: Number): ShapeGeometry<Cube> =
//        resizeBy(ref, ref, ref)

fun ShapeGeometry<Cube>.cornerOrigin() = apply {
    shape.update { it().modify { it.copy(origin = Cube.Origin.Corner) } }
}

//fun ShapeGeometry<Cylinder>.bottomOrigin() =
//        copy(shape = shape.copy(origin = Cylinder.Origin.Bottom))
//
//// TODO resolve platform clash
//fun ShapeGeometry<Cone>.bottomOriginCone() =
//        copy(shape = shape.copy(origin = Cone.Origin.Bottom))

var ShapeGeometry<Cube>.width: Property<Double>
    get() = shape().size.x
    set(value) {
        shape().size.x.set(value)
    }

var ShapeGeometry<Cube>.depth: Property<Double>
    get() = shape().size.y
    set(value) {
        shape().size.y.set(value)
    }

var ShapeGeometry<Cube>.height: Property<Double>
    get() = shape().size.z
    set(value) {
        shape().size.z.set(value)
    }

var ShapeGeometry<Cube>.x: Property<Double>
    get() = translation().position().x
    set(value) {
        translation().position().x.set(value)
    }

var ShapeGeometry<Cube>.y: Property<Double>
    get() = translation().position().y
    set(value) {
        translation().position().y.set(value)
    }

var ShapeGeometry<Cube>.z: Property<Double>
    get() = translation().position().z
    set(value) {
        translation().position().z.set(value)
    }
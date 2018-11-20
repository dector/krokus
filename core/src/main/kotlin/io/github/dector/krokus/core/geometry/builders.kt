package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.geometry.shape.*
import io.github.dector.krokus.core.properties.asScalar
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.v

fun cube(size: Vector3) = ShapeGeometry(
    shape = Cube(size = size).asScalar()
)

fun cube(sx: Number = 0, sy: Number = 0, sz: Number = 0) = cube(v(sx, sy, sz))
fun cube(size: Number) = cube(size, size, size)

fun sphere(radius: Double) = ShapeGeometry(
    shape = Sphere(radius = radius).asScalar()
)

fun sphere(radius: Number) = sphere(radius.toDouble())

fun cylinder(height: Double, radius: Double) = ShapeGeometry(
    shape = Cylinder(height, radius).asScalar()
)

fun cylinder(height: Number, radius: Number) = cylinder(height.toDouble(), radius.toDouble())

fun cone(height: Number, radiusBottom: Number, radiusTop: Number = radiusBottom) =
    ShapeGeometry(
        shape = Cone(
            height = height.toDouble(),
            radiusBottom = radiusBottom.toDouble(),
            radiusTop = radiusTop.toDouble()
        ).asScalar()
    )

fun prism(height: Number, radius: Number, vertices: Int = 3) =
    ShapeGeometry(
        shape = Prism(
            height = height.toDouble(),
            radius = radius.toDouble(),
            vertices = vertices
        ).asScalar()
    )
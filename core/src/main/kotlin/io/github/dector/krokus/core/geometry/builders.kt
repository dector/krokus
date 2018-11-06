package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.v

fun cube(size: Vector3) = ShapeGeometry(shape = Cube(size))
fun cube(sx: Number = 0, sy: Number = 0, sz: Number = 0) = cube(v(sx, sy, sz))
fun cube(size: Number) = cube(size, size, size)

fun sphere(radius: Double) = ShapeGeometry(shape = Sphere(radius = radius))
fun sphere(radius: Number) = sphere(radius.toDouble())

fun cylinder(height: Double, radius: Double) = ShapeGeometry(shape = Cylinder(height, radius))
fun cylinder(height: Number, radius: Number) = cylinder(height.toDouble(), radius.toDouble())

@Deprecated("Use cone", ReplaceWith("cone(height, radiuses.first, radiuses.second)"))
fun cylinder(height: Number, radiuses: Pair<Number, Number>) = ShapeGeometry(
    CylinderDep(
        height = height.toDouble(),
        radius = CylinderDep.Radius(radiuses.first.toDouble(), radiuses.second.toDouble())
    )
)

fun cone(height: Number, radiusBottom: Number, radiusTop: Number = radiusBottom) =
    ShapeGeometry(Cone(height = height.toDouble(), radiusBottom = radiusBottom.toDouble(), radiusTop = radiusTop.toDouble()))

fun prism(height: Number, radius: Number, vertices: Int = 3) =
    ShapeGeometry(Prism(height = height.toDouble(), radius = radius.toDouble(), vertices = vertices))
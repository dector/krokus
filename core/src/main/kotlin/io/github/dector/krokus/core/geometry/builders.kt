package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.v

fun cube(size: Vector3) = ShapeGeometry(shape = Cube(size))
fun cube(sx: Number, sy: Number, sz: Number) = cube(v(sx, sy, sz))
fun cube(size: Number) = cube(size, size, size)

fun sphere(radius: Double) = ShapeGeometry(shape = Sphere(radius = radius))
fun sphere(radius: Number) = sphere(radius.toDouble())

fun cylinder(height: Double, radius: Double) = ShapeGeometry(shape = Cylinder(height, Cylinder.Radius(radius, radius)))
fun cylinder(height: Number, radius: Number) = cylinder(height.toDouble(), radius.toDouble())

fun cylinder(height: Number, radiuses: Pair<Number, Number>) = ShapeGeometry(
    Cylinder(
        height = height.toDouble(),
        radius = Cylinder.Radius(radiuses.first.toDouble(), radiuses.second.toDouble())
    )
)
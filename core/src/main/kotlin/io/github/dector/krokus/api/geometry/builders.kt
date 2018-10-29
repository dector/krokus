package io.github.dector.krokus.api.geometry

import io.github.dector.krokus.api.vector.Vector3
import io.github.dector.krokus.api.vector.xyz

fun cube(size: Vector3) = ShapeGeometry(shape = Cube3(size))
fun cube(sx: Number, sy: Number, sz: Number) = cube(xyz(sx, sy, sz))
fun cube(size: Number) = cube(size, size, size)

fun sphere(radius: Double) = ShapeGeometry(shape = Sphere3(radius = radius))
fun sphere(radius: Number) = sphere(radius.toDouble())

fun cylinder(height: Double, radius: Double) = ShapeGeometry(shape = Cylinder3(height, radius to radius))
fun cylinder(height: Number, radius: Number) = cylinder(height.toDouble(), radius.toDouble())
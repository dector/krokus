package io.github.dector.krokus.geometry

import io.github.dector.krokus.vector.Vector3
import io.github.dector.krokus.vector.v

fun cube(size: Vector3) = Cube(size)
fun cube(dx: Number, dy: Number, dz: Number) = cube(v(dx, dy, dz))
fun cube(size: Int) = cube(v(size, size, size))

fun sphere(radius: Float) = Sphere(radius)
fun sphere(radius: Int) = sphere(radius.toFloat())

fun cylinder(height: Float, radius: Float) = Cylinder(height, radius, radius)
fun cylinder(height: Int, radius: Int) = cylinder(height.toFloat(), radius.toFloat())
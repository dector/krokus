package io.github.dector.krokus.geometry

import io.github.dector.krokus.vector.Vector3
import io.github.dector.krokus.vector.v

fun cube(size: Vector3) = Cube(size)
fun cube(size: Int) = cube(v(size, size, size))

fun sphere(radius: Float) = Sphere(radius)
fun sphere(radius: Int) = sphere(radius.toFloat())
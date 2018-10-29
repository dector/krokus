package io.github.dector.krokus.core.vector


fun xyz(x: Number, y: Number, z: Number) = Vector3(x.toDouble(), y.toDouble(), z.toDouble())

fun xyz(value: Number) = xyz(value, value, value)

fun z(value: Number) = xyz(0, 0, value)
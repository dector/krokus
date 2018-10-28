package io.github.dector.krokus.vector


fun v(xyz: Int) =
    Vector3(xyz.toFloat(), xyz.toFloat(), xyz.toFloat())

fun v(x: Int, y: Int, z: Int) =
    Vector3(x.toFloat(), y.toFloat(), z.toFloat())

fun v(x: Float, y: Float, z: Float) =
    Vector3(x, y, z)

fun v(x: Number, y: Number, z: Number) =
    Vector3(x.toFloat(), y.toFloat(), z.toFloat())
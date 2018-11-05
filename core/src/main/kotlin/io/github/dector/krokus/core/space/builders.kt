package io.github.dector.krokus.core.space


fun v(x: Number = 0, y: Number = 0, z: Number = 0) = Vector3(x.toDouble(), y.toDouble(), z.toDouble())
fun v(value: Number) = v(value, value, value)

fun vx(value: Number) = Vector3(x = value.toDouble())
fun vy(value: Number) = Vector3(y = value.toDouble())
fun vz(value: Number) = Vector3(z = value.toDouble())

fun a(x: Number, y: Number, z: Number) = Angle3(x.toDouble(), y.toDouble(), z.toDouble())
fun a(value: Number) = a(value, value, value)

fun ax(value: Number) = Angle3(x = value.toDouble())
fun ay(value: Number) = Angle3(y = value.toDouble())
fun az(value: Number) = Angle3(z = value.toDouble())
package io.github.dector.krokus.vector


operator fun Vector3.plus(other: Vector3) = Vector3(x = x + other.x, y = y + other.y, z = z + other.z)
operator fun Vector3.plus(value: Int) = Vector3(x = x + value, y = y + value, z = z + value)

operator fun Vector3.minus(other: Vector3) = Vector3(x = x - other.x, y = y - other.y, z = z - other.z)
operator fun Vector3.minus(value: Int) = Vector3(x = x - value, y = y - value, z = z - value)

operator fun Vector3.div(value: Int) = Vector3(x / value, y / value, z / value)
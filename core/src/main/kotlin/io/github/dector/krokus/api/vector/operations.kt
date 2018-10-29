package io.github.dector.krokus.api.vector


// Vector 2

operator fun Vector2.plus(other: Vector2) = Vector2(x = x + other.x, y = y + other.y)
operator fun Vector2.plus(value: Int) = Vector2(x = x + value, y = y + value)

operator fun Vector2.minus(value: Int) = Vector2(x = x - value, y = y - value)

infix fun Vector2.scaleEachBy(other: Vector2) = Vector2(x = x * other.x, y = y * other.y)

// Vector 3

operator fun Vector3.plus(other: Vector3) = Vector3(x = x + other.x, y = y + other.y, z = z + other.z)
operator fun Vector3.plus(value: Int) = Vector3(x = x + value, y = y + value, z = z + value)

operator fun Vector3.minus(other: Vector3) = Vector3(x = x - other.x, y = y - other.y, z = z - other.z)
operator fun Vector3.minus(value: Int) = Vector3(x = x - value, y = y - value, z = z - value)

operator fun Vector3.div(value: Int) = Vector3(x / value, y / value, z / value)
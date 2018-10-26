package krokus

data class Vector3(val x: Float = 0f, val y: Float = 0f, val z: Float = 0f)

sealed class Body
data class Cube(val size: Vector3, val centered: Boolean = false) : Body()
data class Sphere(val r: Float) : Body()

enum class Operation {
    Union, Difference, Intersection
}

sealed class Transformation
data class Translation(val pos: Vector3) : Transformation()
data class Rotation(val empty: Unit = Unit) : Transformation()

sealed class Geometry
data class CombinedGeometry(val operation: Operation, val children: List<Geometry>) : Geometry()
data class PrimitiveGeometry<T : Body>(val body: T) : Geometry()
data class TransformationGeometry(val geometry: Geometry, val transformation: Transformation) : Geometry()
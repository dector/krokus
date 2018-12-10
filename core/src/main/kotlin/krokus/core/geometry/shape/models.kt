package krokus.core.geometry.shape

import krokus.core.space.Vector3


//data class Bounds(
//    var from: Property<Vector3> = Vector3().asScalar(),
//    var to: Property<Vector3> = Vector3().asScalar()
//) {
//
//    /*fun side(side: Side) = when (side) {
//        Side.Front -> ((from - to) / 2).copy(y = from.y)
//        else -> throw NotImplementedError()
//    }
//
//    fun center() = (to - from) / 2*/
//
//    fun size() = (to - from).run { v(xProp.absoluteValue, y.absoluteValue, z.absoluteValue) }
//}

sealed class Shape


data class Cube(
    val size: Vector3 = Vector3.Empty,
    val origin: Origin = Origin.Center
) : Shape() {

    fun modify(with: (Cube) -> Cube) = { with(this) }

    enum class Origin {
        Center, Corner
    }
}


data class Sphere(
    val radius: Double
) : Shape()


data class Cylinder(
    val height: Double,
    val radius: Double,
    val origin: Origin = Origin.Center
) : Shape() {

    enum class Origin {
        Center, Bottom
    }
}


data class Cone(
    val height: Double,
    val radiusBottom: Double,
    val radiusTop: Double,
    val origin: Origin = Origin.Center
) : Shape() {

    enum class Origin {
        Center, Bottom
    }
}


data class Prism(val height: Double, val radius: Double, val vertices: Int) : Shape()


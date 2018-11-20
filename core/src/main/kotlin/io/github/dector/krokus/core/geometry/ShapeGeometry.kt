package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.geometry.shape.Shape
import io.github.dector.krokus.core.properties.Property

data class ShapeGeometry<T : Shape>(
    var shape: Property<T>
//    override val transformations: Transformations = Transformations()
) : BaseGeometry() {



//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

    /*override fun bounds(absolute: Boolean): Bounds {
        fun count(absolute: Boolean, halfSize: Vector3) =
            (if (absolute) position else Vector3.Origin).let { origin ->
                Bounds(
                    from = origin - halfSize,
                    to = origin + halfSize
                )
            }

        return when (shape) {
            is Cube -> {
                val halfSize =
                    v(shape.size.x / 2, shape.size.y / 2, shape.size.z / 2)
                count(absolute, halfSize)
            }
            is Cylinder -> {
                val halfSize = v(shape.radius, shape.radius, shape.height / 2)
                count(absolute, halfSize)
            }
            is CylinderDep -> {
                val maxRadius = shape.radius.max()
                val halfSize = v(maxRadius, maxRadius, shape.height / 2)
                count(absolute, halfSize)
            }
            else -> throw NotImplementedError("$shape")
        }
    }*/

//    private val position = transformations.translation.position
}
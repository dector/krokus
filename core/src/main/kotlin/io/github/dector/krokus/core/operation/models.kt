package io.github.dector.krokus.core.operation

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.transformation.Transformation
import io.github.dector.krokus.core.transformation.Transformations


interface CombinedGeometry : Geometry

data class Union(
    val children: List<Geometry>,
    override val transformations: Transformations = Transformations()
) : CombinedGeometry {

    override fun setTransformation(transformation: Transformation) =
        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}

data class Difference(
    val source: Geometry,
    val children: List<Geometry>,
    override val transformations: Transformations = Transformations()
) : CombinedGeometry {

    override fun setTransformation(transformation: Transformation) =
        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}

data class Intersection(
    val children: List<Geometry>,
    override val transformations: Transformations = Transformations()
) : CombinedGeometry {

    override fun setTransformation(transformation: Transformation) =
        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}
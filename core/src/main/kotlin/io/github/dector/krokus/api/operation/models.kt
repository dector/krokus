package io.github.dector.krokus.api.operation

import io.github.dector.krokus.api.geometry.Geometry
import io.github.dector.krokus.api.geometry.merge
import io.github.dector.krokus.api.transformation.Transformation
import io.github.dector.krokus.api.transformation.Transformations


interface CombinedGeometry : Geometry

data class Union(
    val children: List<Geometry>,
    override val transformations: Transformations = Transformations()
) : CombinedGeometry {

    override fun addTransformation(transformation: Transformation) =
        copy(transformations = transformations merge transformation)
}

data class Difference(
    val source: Geometry,
    val children: List<Geometry>,
    override val transformations: Transformations = Transformations()
) : CombinedGeometry {

    override fun addTransformation(transformation: Transformation) =
        copy(transformations = transformations merge transformation)
}

data class Intersection(
    val children: List<Geometry>,
    override val transformations: Transformations = Transformations()
) : CombinedGeometry {

    override fun addTransformation(transformation: Transformation) =
        copy(transformations = transformations merge transformation)
}
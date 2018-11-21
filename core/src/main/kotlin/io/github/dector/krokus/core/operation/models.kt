package io.github.dector.krokus.core.operation

import io.github.dector.krokus.core.geometry.BaseGeometry
import io.github.dector.krokus.core.geometry.Geometry


interface CombinedGeometry : Geometry {
    var children: () -> List<Geometry>
}

abstract class BaseCombinedGeometry(
    override var children: () -> List<Geometry> = { emptyList() }
) : BaseGeometry(), CombinedGeometry

data class Union(
    override var children: () -> List<Geometry>
) : BaseCombinedGeometry(children) {

//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}

data class Difference(
    val source: () -> Geometry,
    override var children: () -> List<Geometry>
) : BaseCombinedGeometry(children) {

//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

//    override fun bounds(absolute: Boolean) = source.bounds(absolute)    // FIXME may be less

    //    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}

data class Intersection(
    override var children: () -> List<Geometry>
) : BaseCombinedGeometry(children) {

//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}
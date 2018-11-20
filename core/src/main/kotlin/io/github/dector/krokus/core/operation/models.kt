package io.github.dector.krokus.core.operation

import io.github.dector.krokus.core.geometry.BaseGeometry
import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.properties.asScalar


interface CombinedGeometry : Geometry {
    var children: Property<List<Geometry>>
}

abstract class BaseCombinedGeometry(
    override var children: Property<List<Geometry>> = emptyList<Geometry>().asScalar()
) : BaseGeometry(), CombinedGeometry

data class Union(
    override var children: Property<List<Geometry>>
) : BaseCombinedGeometry(children) {

//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}

data class Difference(
    val source: Property<Geometry>,
    override var children: Property<List<Geometry>>
) : BaseCombinedGeometry(children) {

//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

//    override fun bounds(absolute: Boolean) = source.bounds(absolute)    // FIXME may be less

    //    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}

data class Intersection(
    override var children: Property<List<Geometry>>
) : BaseCombinedGeometry(children) {

//    override fun setTransformation(transformation: Transformation) =
//        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)
}
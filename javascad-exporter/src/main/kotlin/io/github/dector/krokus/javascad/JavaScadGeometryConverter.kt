package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.basic.Radius
import eu.printingin3d.javascad.coords.Angles3d
import eu.printingin3d.javascad.coords.Coords3d
import eu.printingin3d.javascad.coords.Dims3d
import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.core.converter.GeometryConverter
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.operation.Difference
import io.github.dector.krokus.core.operation.Intersection
import io.github.dector.krokus.core.operation.Union
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.vector.Vector3
import eu.printingin3d.javascad.models.Cube as JCube
import eu.printingin3d.javascad.models.Cylinder as JCylinder
import eu.printingin3d.javascad.models.Sphere as JSphere
import eu.printingin3d.javascad.tranzitions.Difference as JDifference
import eu.printingin3d.javascad.tranzitions.Intersection as JIntersection
import eu.printingin3d.javascad.tranzitions.Mirror as JMirror
import eu.printingin3d.javascad.tranzitions.Rotate as JRotate
import eu.printingin3d.javascad.tranzitions.Translate as JTranslate
import eu.printingin3d.javascad.tranzitions.Union as JUnion


class JavaScadGeometryConverter : GeometryConverter<Abstract3dModel> {

    override fun convert(geometry: Geometry): Abstract3dModel {
        return unpackGeometry(geometry)
    }

    private fun unpackGeometry(g: Geometry): Abstract3dModel = when (g) {
        is ShapeGeometry ->
            unpackShapeGeometry(g)
        is Union ->
            JUnion(g.children.map(this::unpackGeometry))
        is Difference ->
            JDifference(unpackGeometry(g.source), g.children.map(this::unpackGeometry))
        is Intersection ->
            JIntersection(g.children.map(this::unpackGeometry))
        else ->
            throw NotImplementedError("Unknown geometry $g")
    }.let { model ->
        val transformations = g.transformations

        model.wrapIf(transformations.translation.isNotZero) {
            JTranslate(it, transformations.translation.position.toCoords3d())
        }.wrapIf(transformations.rotation.isNotZero) {
            JRotate(it, transformations.rotation.toAngles3d())
        }
    }

    private fun unpackShapeGeometry(g: ShapeGeometry) = g.shape.let { shape ->
        when (shape) {
            is Cube3 -> JCube(shape.size.toDims3d())
            is Sphere3 -> JSphere(Radius.fromRadius(shape.radius))
            is Cylinder3 -> JCylinder(shape.height, shape.radius.first, shape.radius.second)
        }
    }
}

private fun Abstract3dModel.wrapIf(
    condition: Boolean,
    converter: (Abstract3dModel) -> Abstract3dModel
) =
    if (condition) converter(this)
    else this

private fun Rotation.toAngles3d() =
    Angles3d(angleX.toDouble(), angleY.toDouble(), angleZ.toDouble())

fun Vector3.toDims3d() = Dims3d(x, y, z)
fun Vector3.toCoords3d() = Coords3d(x, y, z)
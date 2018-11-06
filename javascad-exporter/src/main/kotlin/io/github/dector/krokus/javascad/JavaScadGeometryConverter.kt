package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.basic.Radius
import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.core.converter.GeometryConverter
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.operation.Difference
import io.github.dector.krokus.core.operation.Intersection
import io.github.dector.krokus.core.operation.Union
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Transformations
import io.github.dector.krokus.core.transformation.Translation
import eu.printingin3d.javascad.models.Cube as JCube
import eu.printingin3d.javascad.models.Cylinder as JCylinder
import eu.printingin3d.javascad.models.Prism as JPrism
import eu.printingin3d.javascad.models.Sphere as JSphere
import eu.printingin3d.javascad.tranzitions.Difference as JDifference
import eu.printingin3d.javascad.tranzitions.Intersection as JIntersection
import eu.printingin3d.javascad.tranzitions.Mirror as JMirror
import eu.printingin3d.javascad.tranzitions.Rotate as JRotate
import eu.printingin3d.javascad.tranzitions.Translate as JTranslate
import eu.printingin3d.javascad.tranzitions.Union as JUnion


class JavaScadGeometryConverter : GeometryConverter<Abstract3dModel> {

    override fun convert(geometry: Geometry) = resolveGeometry(geometry)

    private fun resolveGeometry(g: Geometry): Abstract3dModel = when (g) {
        is ShapeGeometry<*> -> unpackShapeGeometry(g)
        is Union -> createUnion(g)
        is Difference -> createDifference(g)
        is Intersection -> createIntersection(g)
        else -> throw NotImplementedError("Unknown geometry $g")
    }.run { resolveTransformations(g.transformations) }

    // Operations

    private fun createUnion(g: Union) = JUnion(g.children.map(this::resolveGeometry))
    private fun createDifference(g: Difference) =
        JDifference(resolveGeometry(g.source), g.children.map(this::resolveGeometry))

    private fun createIntersection(g: Intersection) = JIntersection(g.children.map(this::resolveGeometry))

    // Shapes

    private fun unpackShapeGeometry(g: ShapeGeometry<*>) = g.shape.let { shape ->
        when (shape) {
            is Cube -> createCube(shape)
            is Sphere -> createSphere(shape)
            is CylinderDep -> createCylinder(shape)
            is Prism -> createPrism(shape)
        }
    }

    private fun createCube(shape: Cube) = JCube(shape.size.asDims3d())
    private fun createSphere(shape: Sphere) = JSphere(Radius.fromRadius(shape.radius))
    private fun createCylinder(shape: CylinderDep) = JCylinder(shape.height, shape.radius.bottom, shape.radius.top)
    private fun createPrism(shape: Prism) = JPrism(shape.height, shape.radius, shape.vertices)

    // Transformations

    // FIXME custom transformations order is not supported yet
    private fun Abstract3dModel.resolveTransformations(transformations: Transformations) = this
        .wrapIf(transformations.hasRotation) {
            createRotation(it, transformations.rotation)
        }.wrapIf(transformations.hasMirroring) {
            createMirroring(it, transformations.mirroring)
        }.wrapIf(transformations.hasTranslation) {
            createTranslation(it, transformations.translation)
        }

    private fun createTranslation(model: Abstract3dModel, translation: Translation) =
        JTranslate(model, translation.position.toCoords3d())

    private fun createRotation(model: Abstract3dModel, rotation: Rotation) = JRotate(model, rotation.angle.asAngles3d())
    private fun createMirroring(model: Abstract3dModel, mirroring: Mirroring) = when (mirroring.plane) {
        Plane.XY -> JMirror.mirrorZ(model)
        Plane.XZ -> JMirror.mirrorY(model)
        Plane.YZ -> JMirror.mirrorX(model)
        Plane.None -> model
    }
}


package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.basic.Radius
import eu.printingin3d.javascad.coords.Angles3d
import eu.printingin3d.javascad.coords.Coords3d
import eu.printingin3d.javascad.coords.Dims3d
import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.converter.GeometryConverter
import io.github.dector.krokus.geometry.*
import io.github.dector.krokus.operation.Operation.*
import io.github.dector.krokus.transformation.*
import io.github.dector.krokus.vector.Vector3
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

    private val transformationsOrder = { t: Transformation ->
        when (t) {
            is Mirror -> 80
            is Rotate -> 90
            is Translate -> 100
            else -> 1000
        }
    }

    override fun convert(geometry: Geometry): Abstract3dModel {
        return unpackGeometry(geometry)
    }

    private fun unpackGeometry(g: Geometry): Abstract3dModel {
        return when (g) {
            is Cube -> {
                if (g.centered) JCube(g.size.asDims3d())
                else JCube.fromCoordinates(Coords3d.ZERO, g.size.asCoords3d())
            }
            is Sphere -> JSphere(g.r.toDouble())
            is Cylinder -> JCylinder(g.h.toDouble(), Radius.fromRadius(g.bottomR.toDouble()), Radius.fromRadius(g.topR.toDouble()))
            is Combined -> {
                val operation = g.operation

                when (operation) {
                    Union -> JUnion(g.children.map { unpackGeometry(it) })
                    Intersection -> JIntersection(g.children.map { unpackGeometry(it) })
                    Difference -> JDifference(
                        unpackGeometry(g.children.first()),
                        g.children.drop(1).map { unpackGeometry(it) })
                }
            }
            is Contained -> JUnion(unpackGeometry(g.geometry))
            else -> throw NotImplementedError(g.toString())
        }.let { model ->
            var transformedModel: Abstract3dModel = model

            for (t in g.transformations.sortedBy(transformationsOrder)) {
                transformedModel = when (t) {
                    is Translate -> JTranslate(transformedModel, t.pos.asCoords3d())
                    is Mirror -> when (t.plane) {
                        MirrorPlane.X -> JMirror.mirrorX(transformedModel)
                        MirrorPlane.Y -> JMirror.mirrorY(transformedModel)
                        MirrorPlane.Z -> JMirror.mirrorZ(transformedModel)
                        MirrorPlane.None -> transformedModel
                    }
                    is Rotate -> {
                        JRotate(transformedModel,
                            Angles3d(t.angleX.toDouble(), t.angleY.toDouble(), t.angleZ.toDouble()))
                    }
                    else -> throw NotImplementedError(t.toString())
                }
            }

            transformedModel
        }
    }

    private fun Vector3.asDims3d() = Dims3d(x.toDouble(), y.toDouble(), z.toDouble())
    private fun Vector3.asCoords3d() = Coords3d(x.toDouble(), y.toDouble(), z.toDouble())
}
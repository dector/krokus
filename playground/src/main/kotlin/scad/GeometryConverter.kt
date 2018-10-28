package scad

import eu.printingin3d.javascad.coords.Coords3d
import eu.printingin3d.javascad.coords.Dims3d
import eu.printingin3d.javascad.models.Abstract3dModel
import eu.printingin3d.javascad.tranzitions.Translate
import io.github.dector.krokus.*
import io.github.dector.krokus.Operation.*
import eu.printingin3d.javascad.models.Cube as JCube
import eu.printingin3d.javascad.models.Sphere as JSphere
import eu.printingin3d.javascad.tranzitions.Difference as JDifference
import eu.printingin3d.javascad.tranzitions.Intersection as JIntersection
import eu.printingin3d.javascad.tranzitions.Union as JUnion


class GeometryConverter {

    fun convert(g: Geometry): Abstract3dModel {
        return unpackGeometry(g)
    }

    private fun unpackGeometry(g: Geometry): Abstract3dModel {
        return when (g) {
            is PrimitiveGeometry<out Body> -> {
                val body = g.body

                when (body) {
                    is Cube -> {
                        if (body.centered) JCube(body.size.asDims3d())
                        else JCube.fromCoordinates(Coords3d.ZERO, body.size.asCoords3d())
                    }
                    is Sphere -> JSphere(body.r.toDouble())
                    else -> throw NotImplementedError()
                }
            }
            is TransformationGeometry -> {
                val tranformation = g.transformation

                when (tranformation) {
                    is Translation -> Translate(unpackGeometry(g.geometry), tranformation.pos.asCoords3d())
                    else -> throw NotImplementedError()
                }
            }
            is CombinedGeometry -> {
                val operation = g.operation

                when (operation) {
                    Union -> JUnion(g.children.map { unpackGeometry(it) })
                    Intersection -> JIntersection(g.children.map { unpackGeometry(it) })
                    Difference -> JDifference(
                        unpackGeometry(g.children.first()),
                        g.children.drop(1).map { unpackGeometry(it) })
                }
            }
            else -> throw NotImplementedError()
        }
    }

    private fun Vector3.asDims3d() = Dims3d(x.toDouble(), y.toDouble(), z.toDouble())
    private fun Vector3.asCoords3d() = Coords3d(x.toDouble(), y.toDouble(), z.toDouble())
}
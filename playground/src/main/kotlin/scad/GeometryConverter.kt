package scad

import eu.printingin3d.javascad.coords.Coords3d
import eu.printingin3d.javascad.coords.Dims3d
import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.geometry.Combined
import io.github.dector.krokus.geometry.Cube
import io.github.dector.krokus.geometry.Geometry
import io.github.dector.krokus.geometry.Sphere
import io.github.dector.krokus.operation.Operation.*
import io.github.dector.krokus.transformation.Translate
import io.github.dector.krokus.vector.Vector3
import eu.printingin3d.javascad.models.Cube as JCube
import eu.printingin3d.javascad.models.Sphere as JSphere
import eu.printingin3d.javascad.tranzitions.Difference as JDifference
import eu.printingin3d.javascad.tranzitions.Intersection as JIntersection
import eu.printingin3d.javascad.tranzitions.Translate as JTranslate
import eu.printingin3d.javascad.tranzitions.Union as JUnion


class GeometryConverter {

    fun convert(g: Geometry): Abstract3dModel {
        return unpackGeometry(g)
    }

    private fun unpackGeometry(g: Geometry): Abstract3dModel {
        return when (g) {
            is Cube -> {
                if (g.centered) JCube(g.size.asDims3d())
                else JCube.fromCoordinates(Coords3d.ZERO, g.size.asCoords3d())
            }
            is Sphere -> JSphere(g.r.toDouble())
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
            else -> throw NotImplementedError()
        }.let { model ->
            var transformedModel: Abstract3dModel = model

            for (t in g.transformations) {
                transformedModel = when (t) {
                    is Translate -> JTranslate(transformedModel, t.pos.asCoords3d())
                    else -> throw NotImplementedError()
                }
            }

            transformedModel
        }
    }

    private fun Vector3.asDims3d() = Dims3d(x.toDouble(), y.toDouble(), z.toDouble())
    private fun Vector3.asCoords3d() = Coords3d(x.toDouble(), y.toDouble(), z.toDouble())
}
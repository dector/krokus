package krokus.openscad

import krokus.core.converter.GeometryConverter
import krokus.core.geometry.Geometry
import krokus.core.geometry.ShapeGeometry
import krokus.core.geometry.shape.Cone
import krokus.core.geometry.shape.Cube
import krokus.core.geometry.shape.Cylinder
import krokus.core.geometry.shape.Sphere
import krokus.core.operation.Difference
import krokus.core.operation.Intersection
import krokus.core.operation.Union

class OpenScadGeometryConverter(
    val builder: OpenScadBuilder
) : GeometryConverter<String> {

    override fun convert(geometry: Geometry): String =
        convertTransformationsIn(geometry) + convertGeometry(geometry)

    private fun convertTransformationsIn(geometry: Geometry) = buildString {
        builder.appendTranslationIfRequired(this, geometry.translation.ref())

        if (geometry.hasMirroring) {
            append("mirror(")
            append(geometry.mirroring.ref().asString())
            append(") ")
        }

        if (geometry.hasRotation) {
            append("rotate(")
            append(geometry.rotation.ref().angle.ref().asString())
            append(") ")
        }
    }

    private fun convertGeometry(geometry: Geometry) = when (geometry) {
        is ShapeGeometry<*> -> {
            val shape = geometry.shape.ref()

            when (shape) {
                is Cube -> convertCube(shape)
                is Sphere -> convertSphere(shape)
                is Cylinder -> convertCylinder(shape)
                is Cone -> convertCone(shape)
                else -> throw notImplemented(shape)
            }
        }
        is Union -> convertUnion(geometry)
        is Difference -> convertDifference(geometry)
        is Intersection -> convertIntersection(geometry)
        else -> throw notImplemented(geometry)
    }

    private fun convertCube(cube: Cube) = buildString {
        append("cube(")
        append(cube.size.asString(canBeSimplified = true))

        when (cube.origin) {
            Cube.Origin.Corner -> {
                /* do nothing */
            }
            Cube.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    private fun convertCylinder(cylinder: Cylinder) = buildString {
        append("cylinder(")
        append("h = ").append(cylinder.height)
        append(", r = ").append(cylinder.radius)

        when (cylinder.origin) {
            Cylinder.Origin.Bottom -> {
                /* do nothing */
            }
            Cylinder.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    private fun convertCone(cone: Cone) = buildString {
        append("cylinder(")
        append("h = ").append(cone.height)
        append(", r1 = ").append(cone.radiusBottom)
        append(", r2 = ").append(cone.radiusTop)

        when (cone.origin) {
            Cone.Origin.Bottom -> {
                /* do nothing */
            }
            Cone.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    private fun convertSphere(sphere: Sphere) = buildString {
        append("sphere(r = ")
        append(sphere.radius)
        append(");")
    }

    private fun convertUnion(union: Union) = buildString {
        append("union() {")
        union.children().joinAllTo(this)
        append("}")
    }

    private fun convertDifference(difference: Difference) = buildString {
        append("difference(){\n")
        append(convert(difference.source()))

        difference.children().joinAllTo(this)

        append("}")
    }

    private fun convertIntersection(intersection: Intersection) = buildString {
        append("intersection() {")

        intersection.children().joinAllTo(this)

        append("}")
    }

    internal fun List<Geometry>.joinAllTo(sb: StringBuilder) =
        this.joinTo(sb, separator = "\n", prefix = "\n", postfix = "\n", transform = ::convert)

}
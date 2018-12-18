package krokus.v3.openscad

import krokus.openscad.notImplemented
import krokus.v3.bodies.*


class OpenScadBodyConverter(
    private val tokenizer: OpenScadTokenizer
) {

    fun convert(body: Body): String =
        convertModificationsFor(body) + convertShape(body)

    private fun convertModificationsFor(body: Body) = buildString {
        if (body.highlighted)
            append(tokenizer.highlighted())

        append(tokenizer color body.color)
        append(tokenizer translation body.position)

        append(tokenizer rotation body.rotation)

        /*if (geometry.hasMirroring) {
            append("mirror(")
            append(krokus.v3.openscad.asString())
            append(") ")
        }*/
    }

    private fun convertShape(shape: Shape) = when (shape) {
        is PrimitiveBody -> convertPrimitive(shape)
        is CompositeBody -> convertComposite(shape)
        else -> throw notImplemented(shape)
    }

    private fun convertPrimitive(body: PrimitiveBody) = when (body) {
        is Cube -> convertCube(body)
        is Sphere -> convertSphere(body)
        is Cylinder -> convertCylinder(body)
        else -> throw notImplemented(body)
    }

    private fun convertCube(cube: Cube) = tokenizer.shape(cube)
    private fun convertSphere(sphere: Sphere) = tokenizer.shape(sphere)
    private fun convertCylinder(cylinder: Cylinder) = tokenizer.shape(cylinder)

    private fun convertComposite(body: CompositeBody) = when (body) {
        is UnionBody -> convertUnion(body)
        is IntersectionBody -> convertIntersection(body)
        is DifferenceBody -> convertDifference(body)
        else -> throw notImplemented(body)
    }

    private fun convertUnion(union: UnionBody) = tokenizer.union(union, ::convert)
    private fun convertIntersection(intersection: IntersectionBody) = tokenizer.intersection(intersection, ::convert)
    private fun convertDifference(difference: DifferenceBody) = tokenizer.difference(difference, ::convert)

    /*
    private fun convertCone(cone: Cone) = buildString {
        append("cylinder(")
        append("h = ").append(cone.height)
        append(", r1 = ").append(cone.radiusBottom)
        append(", r2 = ").append(cone.radiusTop)

        when (cone.origin) {
            Cone.Origin.Bottom -> {
                *//* do nothing *//*
            }
            Cone.Origin.Center -> append(", center = true")
        }

        append(");")
    }
    */

}
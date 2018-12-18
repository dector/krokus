package krokus.v3.openscad

import krokus.v3.*


/**
 * Tokenizer for Primitives converts only shape
 */
class OpenScadTokenizer {

    infix fun translation(position: Vec3) =
        if (position.isNotZero()) buildString {
            append("translate(")
            append(position.asString())
            append(") ")
        } else ""

    infix fun rotation(rotation: Ang3) =
        if (rotation.isNotZero()) buildString {
            append("rotate(")
            append(rotation.asString())
            append(") ")
        } else ""

    infix fun color(color: Color) =
        if (color != Color.Undefined) buildString {
            append("color(")
            append(color.asString())
            append(") ")
        } else ""

    infix fun shape(cube: Cube) = buildString {
        append("cube(")
        append(cube.size.asString(simplifyIfPossible = true))

        when (cube.origin) {
            Cube.Origin.Corner -> {
                /* do nothing */
            }
            Cube.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    infix fun shape(sphere: Sphere) = buildString {
        append("sphere(r = ")
        append(sphere.radius)
        append(");")
    }

    infix fun shape(cylinder: Cylinder) = buildString {
        append("cylinder(")
        append("r = ").append(cylinder.radius)
        append(", h = ").append(cylinder.height)

        when (cylinder.origin) {
            Cylinder.Origin.Bottom -> {
                /* do nothing */
            }
            Cylinder.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    fun union(union: UnionBody, entitiesConverter: (Body) -> String) = buildString {
        append("union() {")
        union.children.joinAllTo(this, entitiesConverter)
        append("}")
    }

    fun intersection(intersection: IntersectionBody, entitiesConverter: (Body) -> String) = buildString {
        append("intersection() {")
        intersection.children.joinAllTo(this, entitiesConverter)
        append("}")
    }

    fun difference(difference: DifferenceBody, entitiesConverter: (Body) -> String) = buildString {
        append("difference(){\n")
        append(entitiesConverter(difference.source))
        difference.children.joinAllTo(this, entitiesConverter)
        append("}")
    }
}

private fun ColorComponent.asOpenScadValue(precision: Int = 2) =
    (this / 255.0 * 10.pow(precision)).toInt() / 10.pow(precision).toDouble()

internal fun Dimensions.asString(simplifyIfPossible: Boolean = false) =
    if (simplifyIfPossible && allAreEquals()) x.toString()
    else "[$x, $y, $z]"

internal fun Vec3.asString(simplifyIfPossible: Boolean = false) =
    if (simplifyIfPossible && allAreEquals()) x.toString()
    else "[$x, $y, $z]"

internal fun Ang3.asString() = "[$x, $y, $z]"

internal fun Color.asString() = buildString {
    append("[")
    append(r.asOpenScadValue())
    append(", ")
    append(g.asOpenScadValue())
    append(", ")
    append(b.asOpenScadValue())
    append("]")
}

private fun List<Body>.joinAllTo(sb: StringBuilder, converter: (Body) -> String) =
    joinTo(sb, separator = "\n", prefix = "\n", postfix = "\n", transform = converter)

//internal fun Mirroring.asString() = when (plane()) {
//    Plane.None -> ""
//    Plane.XY -> "[0, 0, 1]"
//    Plane.XZ -> "[0, 1, 0]"
//    Plane.YZ -> "[1, 0, 0]"
//}

// Math

private fun Int.pow(value: Int) = Math.pow(this.toDouble(), value.toDouble()).toInt()
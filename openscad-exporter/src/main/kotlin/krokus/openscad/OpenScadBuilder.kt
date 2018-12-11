package krokus.openscad

import krokus.core.material.Color
import krokus.core.space.*
import krokus.core.transformation.Mirroring
import krokus.core.transformation.Translation


class OpenScadBuilder {

    fun appendTranslationIfRequired(sb: StringBuilder, translation: Translation) =
        if (translation.position().isNotZero) appendTranslation(sb, translation)
        else sb

    fun appendTranslation(sb: StringBuilder, translation: Translation) = sb.apply {
        append("translate(")
        append(translation.position().asString())
        append(") ")
    }

    fun appendColor(sb: StringBuilder, color: Color) = sb.apply {
        append("color([")
        append(color.redComponent)
        append(", ")
        append(color.greenComponent)
        append(", ")
        append(color.blueComponent)
        append("]) ")
    }
}

internal fun Vector3.asString(canBeSimplified: Boolean = false) =
    if (canBeSimplified && allAreEqual) x.toString()
    else "[${x()}, ${y()}, ${z()}]"

internal fun Angle3.asString() = "[$x, $y, $z]"

internal fun Mirroring.asString() = when (plane()) {
    Plane.None -> ""
    Plane.XY -> "[0, 0, 1]"
    Plane.XZ -> "[0, 1, 0]"
    Plane.YZ -> "[1, 0, 0]"
}
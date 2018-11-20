package io.github.dector.krokus.openscad

import io.github.dector.krokus.core.material.Color
import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.space.Angle3
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.allAreEqual
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Translation
import io.github.dector.krokus.core.transformation.isNotZero


class OpenScadBuilder {

    fun appendTranslationIfRequired(sb: StringBuilder, translation: Property<Translation>) =
        if (translation.value.isNotZero) appendTranslation(sb, translation.value)
        else sb

    fun appendTranslation(sb: StringBuilder, translation: Translation) = sb.apply {
        append("translate(")
        append(translation.position.asString())
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
    else "[$x, $y, $z]"

internal fun Angle3.asString() = "[$x, $y, $z]"

internal fun Mirroring.asString() = when (plane) {
    Plane.None -> ""
    Plane.XY -> "[0, 0, 1]"
    Plane.XZ -> "[0, 1, 0]"
    Plane.YZ -> "[1, 0, 0]"
}
package krokus.samples

import krokus.core.geometry.Geometry
import krokus.samples.utils.export
import kotlin.reflect.KCallable


fun main() {

    val samples = listOf<Example>(
        ::primitiveCubeCentered,
        ::primitiveCubeWithModifier,
        ::primitiveCubeCornered,
        ::primitiveSphere,
        ::primitiveCylinder,
        ::primitiveCubeWithAbsoluteTranslation,
        ::primitiveCubeWithRelativeTranslation
    )

    samples
        .map { it.functionName() to it }
        .forEach { (name, example) ->
            export(name, example())
        }
}

typealias Example = () -> Geometry

private fun Example.functionName() =
    (this as KCallable<*>)
        .name
        .split("(?=\\p{Upper})".toRegex())
        .joinToString(" ", transform = String::capitalize)
package io.github.dector.krokus.samples

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.samples.utils.export
import kotlin.reflect.KCallable


fun main() {

    val samples = listOf<Example>(
        ::primitiveCubeCentered,
        ::primitiveCubeCornered,
        ::primitiveSphere,
        ::primitiveCylinder
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
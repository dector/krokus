package io.github.dector.krokus.samples

import io.github.dector.krokus.api.moveBy
import io.github.dector.krokus.api.moveTo
import io.github.dector.krokus.core.geometry.cornerOrigin
import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.geometry.cylinder
import io.github.dector.krokus.core.geometry.sphere
import io.github.dector.krokus.core.space.v


fun primitiveCubeCentered() =
    cube(10)

fun primitiveCubeCornered() =
    cube(10)
        .cornerOrigin()

fun primitiveCubeWithAbsoluteTranslation() =
    cube(10)
        .cornerOrigin()
        .moveTo(v(5))
        .moveTo(v(-5))

fun primitiveCubeWithRelativeTranslation() =
    cube(10)
        .cornerOrigin()
        .moveBy(v(5))
        .moveBy(v(-5))

fun primitiveSphere() =
    sphere(10)

fun primitiveCylinder() =
    cylinder(10, 10)
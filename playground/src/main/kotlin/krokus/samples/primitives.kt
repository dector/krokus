package krokus.samples

import krokus.api.moveBy
import krokus.api.moveTo
import krokus.core.geometry.*
import krokus.core.space.v


fun primitiveCubeCentered() =
    cube(10)

fun primitiveCubeWithModifier() =
    cube {
        // size = 10
        // width = 10

        size(10)
    }

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
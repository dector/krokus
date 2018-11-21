package io.github.dector.krokus.samples

import io.github.dector.krokus.api.moveTo
import io.github.dector.krokus.core.geometry.cornerOrigin
import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.geometry.cylinder
import io.github.dector.krokus.core.geometry.sphere


fun primitiveCubeCentered() =
    cube(10)

fun primitiveCubeCornered() =
    cube(10)
        .cornerOrigin()

fun primitiveCubeWithTranslation() =
        cube(10)
            .cornerOrigin()
            .moveTo(10)

fun primitiveSphere() =
    sphere(10)

fun primitiveCylinder() =
    cylinder(10, 10)
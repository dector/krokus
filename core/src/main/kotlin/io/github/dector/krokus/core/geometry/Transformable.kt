package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.core.space.isNotZero
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Translation


interface Transformable {

    val translation: Property<Translation>
    val rotation: Property<Rotation>
    val mirroring: Property<Mirroring>

    val hasTranslation: Boolean
        get() = translation().position().isNotZero
    val hasRotation: Boolean
        get() = rotation().angle().isNotZero
    val hasMirroring: Boolean
        get() = mirroring().plane() != Plane.None
}
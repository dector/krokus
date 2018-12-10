package krokus.core.geometry

import krokus.core.properties.Property
import krokus.core.space.Plane
import krokus.core.space.isNotZero
import krokus.core.transformation.Mirroring
import krokus.core.transformation.Rotation
import krokus.core.transformation.Translation


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
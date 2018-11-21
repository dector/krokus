package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Translation
import io.github.dector.krokus.core.transformation.isNotZero


interface Transformable {

    var translation: Property<Translation>
    var rotation: Property<Rotation>
    var mirroring: Property<Mirroring>

    val hasTranslation: Boolean
        get() = translation.isNotZero
    val hasRotation: Boolean
        get() = rotation.value.angle.value.isNotZero
    val hasMirroring: Boolean
        get() = mirroring.value.plane.value != Plane.None
}
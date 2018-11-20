package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.properties.asScalar
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Translation


abstract class BaseGeometry(

    override var translation: Property<Translation> = Translation().asScalar(),
    override var rotation: Property<Rotation> = Rotation().asScalar(),
    override var mirroring: Property<Mirroring> = Mirroring().asScalar()

) : Geometry
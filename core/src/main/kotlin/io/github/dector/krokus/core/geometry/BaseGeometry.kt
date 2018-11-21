package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.transformation.Mirroring
import io.github.dector.krokus.core.transformation.Rotation
import io.github.dector.krokus.core.transformation.Translation


abstract class BaseGeometry : Geometry {

    override val translation = Property.from(Translation())
    override val rotation = Property.from(Rotation())
    override val mirroring = Property.from(Mirroring())
}
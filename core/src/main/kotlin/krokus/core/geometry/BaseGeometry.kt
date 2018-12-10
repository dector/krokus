package krokus.core.geometry

import krokus.core.properties.Property
import krokus.core.transformation.Mirroring
import krokus.core.transformation.Rotation
import krokus.core.transformation.Translation


abstract class BaseGeometry : Geometry {

    override val translation = Property.from(Translation())
    override val rotation = Property.from(Rotation())
    override val mirroring = Property.from(Mirroring())
}
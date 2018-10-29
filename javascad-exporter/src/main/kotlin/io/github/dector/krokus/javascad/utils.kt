package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.coords.Angles3d
import eu.printingin3d.javascad.coords.Coords3d
import eu.printingin3d.javascad.coords.Dims3d
import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.core.space.Angle3
import io.github.dector.krokus.core.space.Vector3

internal fun Abstract3dModel.wrapIf(
    condition: Boolean,
    converter: (Abstract3dModel) -> Abstract3dModel
) = if (condition) converter(this) else this

internal fun Angle3.asAngles3d() = Angles3d(x, y, z)
internal fun Vector3.asDims3d() = Dims3d(x, y, z)
internal fun Vector3.toCoords3d() = Coords3d(x, y, z)
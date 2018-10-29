package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.models.Abstract3dModel
import eu.printingin3d.javascad.tranzitions.Colorize
import io.github.dector.krokus.core.component.Component
import io.github.dector.krokus.core.converter.ComponentConverter
import io.github.dector.krokus.core.converter.GeometryConverter
import eu.printingin3d.javascad.models.Cube as JCube
import eu.printingin3d.javascad.models.Sphere as JSphere
import eu.printingin3d.javascad.tranzitions.Difference as JDifference
import eu.printingin3d.javascad.tranzitions.Intersection as JIntersection
import eu.printingin3d.javascad.tranzitions.Translate as JTranslate
import eu.printingin3d.javascad.tranzitions.Union as JUnion
import java.awt.Color as JColor


class JavaScadComponentConverter(
    private val geometryConverter: GeometryConverter<Abstract3dModel>
) : ComponentConverter<Abstract3dModel> {

    override fun convert(component: Component): Abstract3dModel {
        val model = geometryConverter.convert(component.geometry)

        return component.material?.color?.let {
            Colorize(JColor.decode(it.value), model)
        } ?: model
    }
}
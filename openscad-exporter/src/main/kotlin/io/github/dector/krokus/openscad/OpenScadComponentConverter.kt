package io.github.dector.krokus.openscad

import io.github.dector.krokus.core.component.Component
import io.github.dector.krokus.core.converter.ComponentConverter
import io.github.dector.krokus.core.material.Material


class OpenScadComponentConverter(
    private val builder: OpenScadBuilder,
    private val geometryConverter: OpenScadGeometryConverter
) : ComponentConverter<String> {

    override fun convert(component: Component) =
        applyMaterial(
            component.material,
            geometryConverter.convert(component.geometry)
        )

    private fun applyMaterial(material: Material?, model: String): String {
        val color = material?.color

        return if (color != null) buildString {
            builder.appendColor(this, color)
            append(model)
        }
        else model
    }
}
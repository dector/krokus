package krokus.openscad

import krokus.core.component.Component
import krokus.core.converter.ComponentConverter
import krokus.core.material.Material


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
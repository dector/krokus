package krokus.openscad

import krokus.core.assembly.Assembly
import krokus.core.converter.AssemblyConverter
import krokus.core.properties.Property
import krokus.core.transformation.Translation


class OpenScadAssemblyConverter(
    val builder: OpenScadBuilder,
    private val componentConverter: OpenScadComponentConverter
) : AssemblyConverter<String> {

    override fun convert(assembly: Assembly) =
        assembly.entries.joinToString("\n") { entry ->
            buildString {
                builder.appendTranslation(this, Translation(Property.from { entry.position }))

                append(componentConverter.convert(entry.component))
            }
        }
}
package io.github.dector.krokus.openscad

import io.github.dector.krokus.core.assembly.Assembly
import io.github.dector.krokus.core.converter.AssemblyConverter
import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.transformation.Translation


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
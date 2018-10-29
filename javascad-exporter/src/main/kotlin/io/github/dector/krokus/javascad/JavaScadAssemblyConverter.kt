package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.api.assembly.Assembly
import io.github.dector.krokus.api.converter.AssemblyConverter
import eu.printingin3d.javascad.tranzitions.Translate as JTranslate
import eu.printingin3d.javascad.tranzitions.Union as JUnion


class JavaScadAssemblyConverter(
    private val componentConverter: JavaScadComponentConverter
) : AssemblyConverter<Abstract3dModel> {

    override fun convert(assembly: Assembly): Abstract3dModel {
        return assembly.entries.map { entry ->
            JTranslate(
                componentConverter.convert(entry.component),
                entry.position.toCoords3d()
            )
        }.toList().let { JUnion(it) }
    }
}
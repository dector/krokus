package io.github.dector.krokus.samples.utils

import io.github.dector.krokus.core.assembly.Assembly
import io.github.dector.krokus.core.component.Component
import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.openscad.OpenScadExporter
import java.io.File


private val exporter = OpenScadExporter(dryRun = false)

private val outFolder = getOutFolder()

fun export(name: String, separate: Boolean = false, assembly: Assembly) {
    if (separate) {
        assembly.entries.distinctBy { it.component.name }.forEachIndexed { i, entry ->
            val filename = "${outFolder.absolutePath}/${name}_${assembly.name}_${entry.component.name}.scad"
            exporter.export(entry.component, File(filename))
        }
    } else {
        exporter.export(assembly, File("${outFolder.absolutePath}/${name}_${assembly.name}.scad"))
    }
}

fun export(name: String, geometry: Geometry) {
    exporter.export(geometry, File("${outFolder.absolutePath}/$name.scad"))
        .evaluateResult()
}

fun export(name: String, component: Component) {
    exporter.export(component, File("${outFolder.absolutePath}/${name}_${component.name}.scad"))
        .evaluateResult()
}

fun exportGeometry(name: String, builder: () -> Geometry) = export(name, builder())
fun exportComponent(name: String, builder: () -> Component) = export(name, builder())
fun exportAssembly(name: String, builder: () -> Assembly) = export(name, false, builder())

private fun Boolean.evaluateResult() {
    if (this) println("Done.")
    else System.err.println("Export failed")
}

private fun getOutFolder(): File {
    return File(
        System.getProperty("user.home"),
        "tmp"
    ).apply {
        mkdirs()
    }
}
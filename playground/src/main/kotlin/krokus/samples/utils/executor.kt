package krokus.samples.utils

import krokus.core.assembly.Assembly
import krokus.core.component.Component
import krokus.core.geometry.Geometry
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
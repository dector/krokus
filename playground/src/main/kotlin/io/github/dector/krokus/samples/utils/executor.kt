package io.github.dector.krokus.samples.utils

import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.core.assembly.Assembly
import io.github.dector.krokus.core.component.Component
import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.javascad.JavaScadAssemblyConverter
import io.github.dector.krokus.javascad.JavaScadComponentConverter
import io.github.dector.krokus.javascad.JavaScadExporter
import io.github.dector.krokus.javascad.JavaScadGeometryConverter


private val geometryConverter = JavaScadGeometryConverter()
private val componentConverter = JavaScadComponentConverter(geometryConverter)
private val assemblyConverter = JavaScadAssemblyConverter(componentConverter)

fun exportGeometry(fileName: String, geometryBuilder: () -> Geometry) {
    export(fileName, geometryBuilder())
}

fun exportComponent(fileName: String, componentBuilder: () -> Component) {
    val component = componentBuilder()

    export("${fileName}_${component.name}", component)
}

fun exportAssembly(fileName: String, assemblyBuilder: () -> Assembly) {
    exportAssembly(fileName, false, assemblyBuilder)
}

fun exportAssembly(fileName: String, separate: Boolean = false, assemblyBuilder: () -> Assembly) {
    val assembly = assemblyBuilder()

    if (separate) {
        assembly.entries.distinctBy { it.component.name }.forEachIndexed { i, entry ->
            val name = "${fileName}_${assembly.name}_${entry.component.name}"
            export(name, entry.component)
        }
    } else {
        export("${fileName}_${assembly.name}", assembly)
    }
}

private fun export(name: String, geometry: Geometry) {
    exportToScad(
        name,
        geometryConverter.convert(geometry)
    )
        .evaluateResult()
}

private fun export(name: String, component: Component) {
    exportToScad(
        name,
        componentConverter.convert(component)
    )
        .evaluateResult()
}

private fun export(name: String, assembly: Assembly) {
    exportToScad(
        name,
        assemblyConverter.convert(assembly)
    )
        .evaluateResult()
}

private fun exportToScad(name: String, model: Abstract3dModel): Boolean {
    val fileName = "samples/$name.scad"

    println("Writing \"$fileName\"")

    return JavaScadExporter()
        .exportToScad(model, fileName)
}

private fun Boolean.evaluateResult() {
    if (this) println("Done.")
    else System.err.println("Export failed")
}
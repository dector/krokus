package io.github.dector.krokus.samples

import eu.printingin3d.javascad.models.Abstract3dModel
import io.github.dector.krokus.assembly.Assembly
import io.github.dector.krokus.component.Component
import io.github.dector.krokus.geometry.Geometry
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
    val assembly = assemblyBuilder()

    export("${fileName}_${assembly.name}", assembly)
}

private fun export(name: String, geometry: Geometry) {
    exportToScad(name, geometryConverter.convert(geometry))
        .evaluateResult()
}

private fun export(name: String, component: Component) {
    exportToScad(name, componentConverter.convert(component))
        .evaluateResult()
}

private fun export(name: String, assembly: Assembly) {
    exportToScad(name, assemblyConverter.convert(assembly))
        .evaluateResult()
}

private fun exportToScad(name: String, model: Abstract3dModel): Boolean {
    return JavaScadExporter()
        .exportToScad(model, "samples/$name.scad")
}

private fun Boolean.evaluateResult() {
    if (this) println("Done.")
    else System.err.println("Export failed")
}
package io.github.dector.krokus.samples

import io.github.dector.krokus.component.Component
import io.github.dector.krokus.geometry.Geometry
import io.github.dector.krokus.javascad.JavaScadComponentConverter
import io.github.dector.krokus.javascad.JavaScadExporter
import io.github.dector.krokus.javascad.JavaScadGeometryConverter


fun exportGeometry(fileName: String, geometryBuilder: () -> Geometry) {
    export(fileName, geometryBuilder())
}

fun exportComponent(fileName: String, componentBuilder: () -> Component) {
    val component = componentBuilder()

    export("${fileName}_${component.name}", component)
}

private fun export(name: String, geometry: Geometry) {
    val converter = JavaScadGeometryConverter()
    val result = JavaScadExporter().exportToScad(
        converter.convert(geometry), "samples/$name.scad"
    )

    if (result) println("Done.")
    else System.err.println("Export failed")
}

private fun export(name: String, component: Component) {
    val converter = JavaScadComponentConverter(JavaScadGeometryConverter())
    val result = JavaScadExporter().exportToScad(
        converter.convert(component), "samples/$name.scad"
    )

    if (result) println("Done.")
    else System.err.println("Export failed")
}
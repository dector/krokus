package io.github.dector.krokus.samples

import io.github.dector.krokus.component.Component
import io.github.dector.krokus.geometry.Geometry
import io.github.dector.krokus.javascad.JavaScadExporter
import io.github.dector.krokus.javascad.JavaScadGeometryConverter


fun exportGeometry(name: String, geometryBuilder: () -> Geometry) {
    export(name, geometryBuilder())
}

fun exportComponent(name: String, componentBuilder: () -> Component) {
    val component = componentBuilder()

    export("${name}_${component.name}", component.geometry)
}

private fun export(name: String, geometry: Geometry) {
    val converter = JavaScadGeometryConverter()
    val result = JavaScadExporter().exportToScad(
        converter.convert(geometry), "samples/$name.scad"
    )

    if (result) println("Done.")
    else System.err.println("Export failed")
}
package io.github.dector.krokus.openscad

import io.github.dector.krokus.api.mirrorVertically
import io.github.dector.krokus.api.moveTo
import io.github.dector.krokus.api.rotateAtY
import io.github.dector.krokus.core.assembly.Assembly
import io.github.dector.krokus.core.component.Component
import io.github.dector.krokus.core.geometry.*
import java.io.File


class OpenScadExporter {

    private val builder by lazy { OpenScadBuilder() }
    private val geometryConverter by lazy { OpenScadGeometryConverter(builder) }
    private val componentConverter by lazy { OpenScadComponentConverter(builder, geometryConverter) }
    private val assemblyConverter by lazy { OpenScadAssemblyConverter(builder, componentConverter) }

    fun export(geometry: Geometry, file: File): Boolean {
        file.writeAndClose(
            geometryConverter.convert(geometry)
        )

        return true
    }

    fun export(component: Component, file: File): Boolean {
        file.writeAndClose(
            componentConverter.convert(component)
        )

        return true
    }

    fun export(assembly: Assembly, file: File): Boolean {
        file.writeAndClose(
            assemblyConverter.convert(assembly)
        )

        return true
    }

    private fun File.writeAndClose(s: String) {
        this.bufferedWriter().apply {
            write(s)
            flush()
            close()
        }
    }
}

fun main(args: Array<String>) {
    val export = object {

        private var counter = 0
        private val exporter = OpenScadExporter()

        operator fun invoke(vararg geometry: Geometry) {
            geometry.forEach(this::export)
        }

        fun export(geometry: Geometry) {
            val filename = "tmp/test${counter}.scad"
            val file = File(filename).also { it.parentFile.mkdirs() }

            exporter.export(
                geometry,
                file
            )

            // OpenScad export to image seems broken
            //render(file)

            counter++
        }

        fun render(src: File) {
            val out = File("tmp/out/${src.nameWithoutExtension}.png")
                .also { it.parentFile.mkdirs() }
                .absolutePath

            val p = ProcessBuilder(
                "openscad",
                "-m", "make",
                "-o", out,
                "--render", "--camera=100,100,100,0,0,0",
                "--colorscheme=Tomorrow Night",
                src.absolutePath
            ).run {
                start()
            }

            val result = p.waitFor()

            if (result != 0) {
                System.err.println(p.errorStream.reader().readText())
            }
        }
    }

    fun clip(vararg geometry: Geometry) {
        val scad = OpenScadGeometryConverter(OpenScadBuilder()).convert(geometry.last())
        ProcessBuilder("sh", "-c", "echo '$scad' | xclip -selection clipboard")
            .start().waitFor()
    }

//    export(
    clip(
        cube(10),
        cube(10).cornerOrigin(),
        cylinder(10, 5),
        cylinder(10, 5).bottomOrigin(),
        sphere(10),
        cube(10) + sphere(7),
        cube(10).cornerOrigin() + sphere(7),
        cube(10) - sphere(7),
        cube(10).cornerOrigin() - sphere(7),
        cube(10) * sphere(7),
        cube(10).cornerOrigin() * sphere(7),
        cone(10, 10, 5),
        cone(10, 10, 5).bottomOriginCone(),
        cube(8) - (cube(10) + sphere(7)),
        cone(10, 5, 0).bottomOriginCone().mirrorVertically().rotateAtY(30).moveTo(10)
    )
}
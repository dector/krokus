package io.github.dector.krokus.samples

import io.github.dector.krokus.api.moveBy
import io.github.dector.krokus.api.multiply
import io.github.dector.krokus.api.uncenter
import io.github.dector.krokus.core.assembly.Entry
import io.github.dector.krokus.core.assembly.assembly
import io.github.dector.krokus.core.assembly.toEntry
import io.github.dector.krokus.core.assembly.toEntryAt
import io.github.dector.krokus.core.component.component
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.math.asRadius
import io.github.dector.krokus.core.space.plus
import io.github.dector.krokus.core.space.times
import io.github.dector.krokus.core.space.v
import io.github.dector.krokus.samples.utils.exportAssembly


fun main(args: Array<String>) {
    exportAssembly("CncElectronicBox", true, ::assembly)
}

private val boardSize = v(90.2, 91.5, 30)
private val thickness = 1.0
private val coverSize = boardSize.copy(z = thickness)
private val boardThickness = 1.7
private val spacerHoleRadius = asRadius(5.2)
private val coverHolderSize = v(thickness, 10, 1)
private val coverHolderGap = 0.2

fun assembly() = assembly("All") {
    spacerEntries() +
            boxComponent().toEntry() +
            coverComponent().toEntryAt(y = -(boardSize.y + 10))
}

fun boxComponent() = component("box") {
    val shell = cube(boardSize + v(thickness) * 2).uncenter()

    shell -
            coverHolders(applyGap = true).moveBy(x = coverHolderSize.x, z = shell.size.z - coverHolderSize.z) -
            cube(boardSize + v(z = thickness)).uncenter().moveBy(thickness) -
            cube(thickness, 8, 4.2 + thickness + boardThickness).uncenter().moveBy(
                y = thickness + 28.7,
                z = boardThickness
            ) -
            cube(thickness, 9.2, 11).uncenter().moveBy(y = thickness + 42, z = boardThickness) -
            cylinder(thickness + boardThickness, spacerHoleRadius).multiply(4).mapIndexed { i, it ->
                val offset = 3 + thickness
                val ref = shell.size

                val positions = mapOf(
                    0 to v(offset, offset),
                    1 to v(offset, ref.y - offset),
                    2 to v(ref.x - offset, ref.y - offset),
                    3 to v(ref.x - offset, offset)
                )
                it.moveBy(positions[i] ?: v())
            }.union()
}

fun coverComponent() = component("cover") {
    val cover = cube(coverSize).uncenter()

    cover + coverHolders() - cube(25, 7, thickness)
        .moveBy(z = cover.size.z / 2)
        .moveBy(x = cover.size.x / 2, y = cover.size.y - 20)
}

fun spacerEntries(): List<Entry> {
    val spacerHeight = 5 - thickness

    val spacer = component("spacer") {
        cylinder(spacerHeight, asRadius(11)) -
                cylinder(spacerHeight, spacerHoleRadius)
    }

    val x = -spacer.geometry.size.x / 2 - 10
    val y = spacer.geometry.size.y / 2

    return (0..3).map { index ->
        spacer.toEntryAt(
            x = x,
            y = (2 * y + 5) * index + 10,
            z = spacerHeight / 2
        )
    }
}

private fun coverHolders(applyGap: Boolean = false) = cube(coverHolderSize + (v(y = 2*coverHolderGap).takeIf { applyGap } ?: v()))
    .uncenter().multiply(2).mapIndexed { i, it ->
        when (i) {
            0 -> it.moveBy(-it.size.x, coverSize.y / 2 - it.size.y / 2)
            1 -> it.moveBy(coverSize.x, coverSize.y / 2 - it.size.y / 2)
            else -> cube(0, 0, 0)
        }
    }.union()

private operator fun Double.plus(number: Number) = this + number.toDouble()
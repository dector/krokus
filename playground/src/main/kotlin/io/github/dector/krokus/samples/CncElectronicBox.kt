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
private val boardThickness = 1.7
private val spacerHoleRadius = asRadius(5.2)
private val coverHolderSize = v(thickness, 10, 1)
private val coverHolderGap = 0.3
private val boardOffset = v(5, 5, 0)

private val innerSpace = boardSize + boardOffset * 2
private val shellSize = innerSpace + v(2 * thickness)
private val coverSize = innerSpace.copy(z = thickness)

fun assembly() = assembly("All") {
    spacerEntries() +
            boxComponent().toEntry() +
            coverComponent().toEntryAt(y = -(innerSpace.y + 10))
}

fun boxComponent() = component("box") {
    fun shell() = cube(shellSize).uncenter() -
            cube(innerSpace + v(z = thickness)).uncenter().moveBy(thickness) -
            coverHolder(coverHolderGap)
                .multiply(2)
                .mapIndexed { i, cut ->
                    when (i) {
                        0 -> cut
                        1 -> cut.moveBy(x = shellSize.x - cut.size.x)
                        else -> cube()
                    }.moveBy(
                        y = (shellSize.y - cut.size.y) / 2,
                        z = shellSize.z - coverHolderSize.z
                    )
                }.union()

    fun usbCut() = cube(thickness, 8, 4).uncenter()
        .moveBy(
            y = thickness + 28.7,
            z = thickness + boardThickness
        )

    fun powerCut() = cube(thickness, 9, 11).uncenter()
        .moveBy(
            y = thickness + 42,
            z = thickness + boardThickness
        )

    fun mountHoles() = cylinder(thickness, spacerHoleRadius)
        .moveBy(z = thickness / 2)
        .multiply(4)
        .mapIndexed { i, hole ->
            val offset = v(6, 10) + boardOffset + v(thickness)
            val ref = v(78, 76)

            val positions = mapOf(
                0 to v(offset.x, offset.y),
                1 to v(offset.x, offset.y + ref.y),
                2 to v(offset.x + ref.x, offset.y + ref.y),
                3 to v(offset.x + ref.x, offset.y)
            )
            hole.moveBy(positions[i] ?: v())
        }.union()

    shell() - (usbCut() + powerCut() + mountHoles())
}

fun coverComponent() = component("cover") {
    val cover = cube(coverSize).uncenter()

    fun coverHolders() =
        coverHolder()
            .multiply(2)
            .mapIndexed { i, cut ->
                when (i) {
                    0 -> cut.moveBy(x = -cut.size.x)
                    1 -> cut.moveBy(x = cover.size.x)
                    else -> cube()
                }.moveBy(
                    y = (cover.size.y - cut.size.y) / 2
                )
            }.union()

    cover + coverHolders() -
            cube(25, 7, thickness)
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

private fun coverHolder(gap: Double = 0.0) =
    cube(coverHolderSize + v(y = 2 * gap)).uncenter()

private operator fun Double.plus(number: Number) = this + number.toDouble()
package io.github.dector.krokus.samples.life

//import io.github.dector.krokus.api.moveBy
//import io.github.dector.krokus.api.multiply
//import io.github.dector.krokus.api.uncenter


/*
fun main(args: Array<String>) {
    assemblyAll().entries.map { it.component }.distinctBy { it::name }.forEach {
        export("CncElectronicBox_${it.name}", it.geometry)
    }
//    exportAssembly("CncElectronicBox", true, ::assemblyAll)
}

private val boardSize = v(90.2, 91.5, 30)
private val thickness = 1.0
private val boardBottomGap = 5
private val boardThickness = 1.7
private val spacerHoleRadius = asRadius(5.2)
private val coverHolderSize = v(thickness, 10, 1)
private val coverHolderGap = 0.3
private val boardOffset = v(0, 3, 0)

private val innerSpace = boardSize + boardOffset + v(xProp = boardOffset.y, y = boardOffset.y)
private val shellSize = innerSpace + v(2 * thickness)
private val coverSize = innerSpace.copy(z = thickness)

fun assemblyAll() = assembly("All") {
    spacerEntries() +
            boxComponent().toEntry() +
            coverComponent().toEntryAt(y = -(innerSpace.y + 10))
}

fun boxComponent() = component("box") {
    fun shell() = cube(shellSize).uncenter() -
            cube(innerSpace + v(z = thickness)).uncenter().moveBy(
                thickness
            ) -
            coverHolder(coverHolderGap)
                .multiply(2)
                .mapIndexed { i, cut ->
                    when (i) {
                        0 -> cut
                        1 -> cut.moveBy(xProp = shellSize.xProp - cut.size.xProp)
                        else -> cube()
                    }.moveBy(
                        y = (shellSize.y - cut.size.y) / 2,
                        z = shellSize.z - coverHolderSize.z
                    )
                }.union()

    fun usbCut() = cube(thickness, 8 + 0.3, 4 + 0.3).uncenter()
        .moveBy(
            y = thickness + 29,
            z = thickness + boardThickness + boardBottomGap
        ).moveBy(y = boardOffset.y)

    fun powerCut() = cube(thickness, 9, 11).uncenter()
        .moveBy(
            y = thickness + 42,
            z = thickness + boardThickness + boardBottomGap
        ).moveBy(y = boardOffset.y)

    fun mountHoles() = cylinder(
        thickness,
        spacerHoleRadius
    )
        .moveBy(z = thickness / 2)
        .multiply(4)
        .mapIndexed { i, hole ->
            val offset = v(6, 10) + boardOffset + v(thickness)
            val ref = v(78, 76)

            val positions = mapOf(
                0 to v(offset.xProp, offset.y),
                1 to v(offset.xProp, offset.y + ref.y),
                2 to v(offset.xProp + ref.xProp, offset.y + ref.y),
                3 to v(offset.xProp + ref.xProp, offset.y)
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
                    0 -> cut.moveBy(xProp = -cut.size.xProp)
                    1 -> cut.moveBy(xProp = cover.size.xProp)
                    else -> cube()
                }.moveBy(
                    y = (cover.size.y - cut.size.y) / 2
                )
            }.union()

    cover + coverHolders() -
            cube(25, 7, thickness)
                .moveBy(z = cover.size.z / 2)
                .moveBy(xProp = cover.size.xProp / 2, y = cover.size.y - 20)
}

fun spacerEntries(): List<Entry> {
    val spacerHeight = boardBottomGap - boardThickness //5 - thickness

    val spacer = component("spacer") {
        cylinder(spacerHeight, asRadius(11)) -
                cylinder(spacerHeight, spacerHoleRadius)
    }

    val xProp = -spacer.geometry.size.xProp / 2 - 10
    val y = spacer.geometry.size.y / 2

    return (0..3).map { index ->
        spacer.toEntryAt(
            xProp = xProp,
            y = (2 * y + 5) * index + 10,
            z = spacerHeight / 2
        )
    }
}

private fun coverHolder(gap: Double = 0.0) =
    cube(coverHolderSize + v(y = 2 * gap)).uncenter()

private operator fun Double.plus(number: Number) = this + number.toDouble()*/

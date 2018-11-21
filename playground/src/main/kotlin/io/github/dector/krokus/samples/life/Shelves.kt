package io.github.dector.krokus.samples.life

//import io.github.dector.krokus.api.moveBy
//import io.github.dector.krokus.api.multiply
//import io.github.dector.krokus.api.rotateAtZ
//import io.github.dector.krokus.api.uncenter
//import io.github.dector.krokus.core.geometry.minus
//import io.github.dector.krokus.core.geometry.union


/*
fun main(args: Array<String>) {
    exportAssembly("Shelves") {
        assembly("All") {
            listOf(
                design().toEntry(),
                room().toEntry()
            )
        }
    }
}

val demoSpace = v(200, 200, 235)
val demoOffset = v(10)

fun room() = component("room", Material(Color("#9f9f9f"))) {
    val inner = cube(demoSpace).uncenter()
        .moveBy(demoOffset.copy(y = 0.0))
    val outer = cube(demoSpace + demoOffset).uncenter()

    outer - inner
}

private typealias Modifier = (Geometry) -> Geometry

private val thickness = 1.8

private val topHeight = 230

// First shelf
private val fsWidth = 40
private val fsHeight = 35
private val fsDepth = 50
private val fsBottomSpace = 30

// Second shelf
private val ssWidth = 40
private val ssWidth1 = fsDepth + 20//ssWidth
private val ssWidth2 = ssWidth
private val ssHeight = fsHeight
private val ssDepth = 30
private val ssBottomSpace = fsBottomSpace

fun design() = component("all", Material(Color.Erin)) {
    listOf(
        first(),
        second()
    ).union()
        .rotateAtZ(90)
        .moveBy(
            xProp = fsDepth,
            y = demoSpace.y - (3* thickness + 2* fsWidth + ssDepth),
            z = demoOffset.z
        )
}

fun first(): Geometry {
    fun fsStands() = vPlate(
        topHeight,
        fsDepth
    ).multiply(3, mapOf(
        1 to { it -> it.moveBy(xProp = thickness + fsWidth) },
        2 to { it -> it.moveBy(xProp = 2 * (thickness + fsWidth)) }
    )).union()

    fun fsTop() = hPlate(
        3 * thickness + 2 * fsWidth,
        fsDepth
    )
        .moveBy(z = topHeight - thickness)

    fun fsShelves(): Geometry {
        val vertSpace = topHeight - thickness

        val shelves = mutableListOf<Geometry>()
        var shelfIndex = 0
        while (true) {
            val atHeight = fsBottomSpace + shelfIndex * (thickness + fsHeight)
            if (atHeight >= vertSpace) break

            hPlate(
                fsWidth,
                fsDepth
            ).moveBy(
                xProp = thickness,
                z = atHeight
            ).multiply(2, mapOf(
                1 to { it -> it.moveBy(xProp = fsWidth + thickness) }
            )).forEach { shelves.add(it) }

            shelfIndex++
        }

        return shelves.union()
    }

    return listOf(
        fsStands(),
        fsTop(),
        fsShelves()
    ).union()
}

fun second(): Geometry {
    fun ssStands() = vPlate(
        topHeight,
        ssDepth
    ).multiply(3, mapOf(
        1 to { it -> it.moveBy(xProp = thickness + ssWidth1) },
        2 to { it -> it.moveBy(xProp = 2 * thickness + ssWidth1 + ssWidth2) }
    )).union()

    fun ssTop() = hPlate(
        3 * thickness + ssWidth1 + ssWidth2,
        ssDepth
    )
        .moveBy(z = topHeight - thickness)

    fun ssShelves(): Geometry {
        val vertSpace = topHeight - thickness

        val shelves = mutableListOf<Geometry>()
        var shelfIndex = 0
        while (true) {
            val atHeight = ssBottomSpace + shelfIndex * (thickness + ssHeight)
            if (atHeight >= vertSpace) break

            hPlate(
                ssWidth1 + thickness + ssWidth2,
                ssDepth
            ).moveBy(
                xProp = thickness,
                z = atHeight
            ).let { shelves.add(it) }

            shelfIndex++
        }

        return shelves.union()
    }

    return listOf(
        ssStands(),
        ssTop(),
        ssShelves()
    ).union()
        .rotateAtZ(-90)
        .moveBy(
            xProp = thickness * 3 + fsWidth * 2,
            y = fsDepth
        )
}

// ---

fun vPlate(height: Number, depth: Number) =
    cube(thickness, depth, height).uncenter()

fun hPlate(width: Number, depth: Number) =
    cube(width, depth, thickness).uncenter()

private fun Geometry.multiply(number: Int, modifiers: Map<Int, Modifier>) =
    multiply(number).mapIndexed { index, geometry ->
        val modifier = modifiers[index]
        modifier?.invoke(geometry) ?: geometry
    }*/

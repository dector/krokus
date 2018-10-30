package io.github.dector.krokus.api

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.geometry.Side
import io.github.dector.krokus.core.geometry.findSide
import io.github.dector.krokus.core.space.minus


fun <G : Geometry> G.moveTo(other: Geometry, joinSides: Pair<Side, Side>): G {
    val p1 = this.findSide(joinSides.first, applyTranslation = false)
    val p2 = other.findSide(joinSides.second)
    return moveTo(p2 - p1)
}
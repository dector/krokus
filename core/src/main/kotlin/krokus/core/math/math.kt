package krokus.core.math


fun asRadius(diameter: Double) = diameter / 2
fun asRadius(diameter: Number) = asRadius(diameter.toDouble())
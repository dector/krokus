package io.github.dector.krokus.core.properties


operator fun Property<Double>.plus(other: Property<Double>): Property<Double> =
    Property.from { ref() + other.ref() }

operator fun Property<Double>.minus(other: Property<Double>): Property<Double> =
    Property.from { ref() - other.ref() }

operator fun Property<Double>.unaryMinus(): Property<Double> =
    Property.from { -ref() }

operator fun Property<Double>.times(v: Int): Property<Double> =
    Property.from { ref() * v }

operator fun Property<Double>.div(v: Int): Property<Double> =
    Property.from { ref() / v }

operator fun Int.times(other: Property<Double>): Property<Double> =
    Property.from { other.ref() * this }
package krokus.core.properties


//operator fun Property<Double>.plus(other: Property<Double>) =
//    Computable { ref + other.ref }
//
//operator fun Property<Double>.div(other: Property<Double>) =
//    Computable { ref / other.ref }
//
//operator fun Property<Int>.div(v: Int) =
//    this / v.asScalar()

operator fun Property<Int>.times(v: Int) = { ref() * v }
package krokus.core.properties


//fun <T> T.asScalar() = Scalar(this)

fun <T> p(producer: Producer<T>) = Property.from(producer)
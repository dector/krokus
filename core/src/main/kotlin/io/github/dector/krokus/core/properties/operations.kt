package io.github.dector.krokus.core.properties


operator fun Property<Int>.div(v: Int) = Computable { value / v }
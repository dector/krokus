package io.github.dector.krokus.core.converter

import io.github.dector.krokus.core.assembly.Assembly


interface AssemblyConverter<T> {

    fun convert(assembly: Assembly): T
}
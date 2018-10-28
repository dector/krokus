package io.github.dector.krokus.converter

import io.github.dector.krokus.assembly.Assembly


interface AssemblyConverter<T> {

    fun convert(assembly: Assembly): T
}
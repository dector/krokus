package io.github.dector.krokus.api.converter

import io.github.dector.krokus.api.assembly.Assembly


interface AssemblyConverter<T> {

    fun convert(assembly: Assembly): T
}
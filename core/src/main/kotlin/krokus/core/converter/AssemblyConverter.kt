package krokus.core.converter

import krokus.core.assembly.Assembly


interface AssemblyConverter<T> {

    fun convert(assembly: Assembly): T
}
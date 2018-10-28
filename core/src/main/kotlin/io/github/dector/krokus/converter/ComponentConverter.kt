package io.github.dector.krokus.converter

import io.github.dector.krokus.component.Component


interface ComponentConverter<T> {

    fun convert(component: Component): T
}
package io.github.dector.krokus.core.converter

import io.github.dector.krokus.core.component.Component


interface ComponentConverter<T> {

    fun convert(component: Component): T
}
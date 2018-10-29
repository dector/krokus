package io.github.dector.krokus.api.converter

import io.github.dector.krokus.api.component.Component


interface ComponentConverter<T> {

    fun convert(component: Component): T
}
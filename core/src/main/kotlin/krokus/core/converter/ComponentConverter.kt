package krokus.core.converter

import krokus.core.component.Component


interface ComponentConverter<T> {

    fun convert(component: Component): T
}
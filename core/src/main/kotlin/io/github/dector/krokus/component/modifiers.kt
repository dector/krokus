package io.github.dector.krokus.component

import io.github.dector.krokus.geometry.mirrorVertically


fun Component.mirrorVertically() = copy(geometry = geometry.mirrorVertically())
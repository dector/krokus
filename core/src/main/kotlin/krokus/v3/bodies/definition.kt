package krokus.v3.bodies

import krokus.v3.properties.Colorable
import krokus.v3.properties.Highlightable
import krokus.v3.properties.Positionable
import krokus.v3.properties.Rotatable


interface Shape

interface Body : Shape, Positionable, Rotatable, Colorable, Highlightable
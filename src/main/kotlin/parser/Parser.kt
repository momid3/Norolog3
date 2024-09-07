package com.momid.parser

import com.momid.parseTypes
import com.momid.parser.expression.text

fun main() {
    val typesToken = "some<ooo>some<ooo>some<ooo>"
    val types = parseTypes(typesToken)!!
    println(types[0].text)
}

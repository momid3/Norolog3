package com.momid.parsing_rules

import com.momid.parser.expression.anyOf
import com.momid.type.Type

@Type
val declaration by lazy { anyOf(functionDeclaration) }

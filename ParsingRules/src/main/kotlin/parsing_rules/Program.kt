package com.momid.parsing_rules

import com.momid.parser.expression.one
import com.momid.parser.expression.plus
import com.momid.parser.expression.some0
import com.momid.parser.expression.spaces
import com.momid.parser.expression.get
import com.momid.type.Type

@Type
val programDeclaration by lazy { one(spaces + declaration["declaration"] + spaces) }

@Type
val program by lazy { some0(programDeclaration) }

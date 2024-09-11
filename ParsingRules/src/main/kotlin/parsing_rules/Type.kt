package com.momid.parsing_rules

import com.momid.parser.expression.anyOf
import com.momid.parser.expression.same
import com.momid.type.Type

@Type
val klass by lazy { same(allowedName) }

@Type
val type by lazy { anyOf(klass) }

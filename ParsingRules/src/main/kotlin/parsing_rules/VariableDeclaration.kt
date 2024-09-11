package com.momid.parsing_rules

import com.momid.parser.expression.plus
import com.momid.parser.expression.space
import com.momid.parser.not
import com.momid.parser.expression.get
import com.momid.parser.expression.spaces
import com.momid.type.Type

@Type
val variableDeclaration by lazy { !"val" + space + allowedName["variableName"] + spaces + !"=" + spaces + cExpression["assignedValue"] }

package com.momid.parsing_rules

import com.momid.parser.expression.plus
import com.momid.parser.expression.space
import com.momid.parser.not
import com.momid.parser.expression.get
import com.momid.parser.expression.spaces
import com.momid.type.Type

@Type
val declaredVariable by lazy { allowedName }

@Type
val variableDeclaration by lazy { !"val" + space + declaredVariable["variableName"] + spaces + !"=" + spaces + cExpression["assignedValue"] }

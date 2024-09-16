package com.momid.parsing_rules

import com.momid.parser.expression.*
import com.momid.parser.not
import com.momid.type.Type

@Type
val allowedName = condition { it.isLetter() } + some(condition { it.isLetterOrDigit() })

@Type
val parameter = spaces + allowedName["variableName"] + spaces

@Type
val parameters = splitBy(parameter, ",")

@Type
val functionCall = allowedName["functionName"] + insideOf('(', ')') {
    parameters
}["parameters"]

@Type
val fullFunctionCall = !"some" + insideOf('(', ')') {
    one(!"fun" + spaces + functionCall["functionCall"])
}["ooo"]

@Type
val numbers = some(require(com.momid.parser.expression.fNumber))

@Type
val variable = same(allowedName)

@Type
val plus = !"+"

@Type
val minus = !"-"

@Type
val times = !"*"

@Type
val divide = !"/"

@Type
val operator by lazy { anyOf(plus, minus, times, divide) }

@Type
val operatorInExpression by lazy {
    one(operator["operator"] + spaces)
}

@Type
val swn by lazy { some0(condition { it.isWhitespace() && it != '\n' && it != '\r' }) }

@Type
val simpleExpression: ColdExpression by lazy {
    cold {
        some(one(swn + anyOf(atomic, operatorInExpression)["actualExpression"] + swn))
    }
}

@Type
val expressionWithParentheses: ColdExpression by lazy {
    cold {
        (!"(" + spaces + cExpression["inside"] + spaces + !")")
    }
}

@Type
val cExpression by lazy { some(anyOf(simpleExpression, expressionWithParentheses)["actualExpression"]) }

@Type
val variables by lazy { some(one(spaces + parameter["parameter"] + spaces)) }

@Type
val fNumber by lazy { same(com.momid.parser.expression.fNumber) }

@Type
val variableAccess by lazy { !"." + variable["variable"] }

@Type
val arrayAccess by lazy { !"[" + cExpression["insideArrayExpression"] + !"]" }

@Type
val nextAccessItem by lazy {
    anyOf(variableAccess, arrayAccess)
}

@Type
val atomic by lazy {
    cold {
        anyOf(variable, fNumber)["first"] + some0(nextAccessItem)["nextAccessItems"]
    }
}

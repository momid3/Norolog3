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
val klass = same(allowedName)

@Type
val genericType = cold { allowedName["name"] + !"<" + type["typeParameter"] + !">" }

@Type
val type: EachOfExpression = anyOf(genericType, klass)

@Type
val requiresType = (!"ooo")["ooo"] + require((!"sor"))["some"] + (!"ooo")["someooo"]

@Type
val types = some(require(type))

@Type
val numbers = some(require(com.momid.parser.expression.fNumber))

@Type
val optionalTypes = optional(allowedName)["identifier"] + space + type["type"]

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
val simpleExpression: RecurringSomeExpression by lazy {
    some(one(spaces + anyOf(atomic, operator)["actualExpression"] + spaces))
}

@Type
val expressionWithParentheses: ColdExpression by lazy {
    cold {
        !"(" + spaces + cExpression["inside"] + spaces + !")"
    }
}

@Type
val cExpression by lazy { some(one(spaces + anyOf(simpleExpression, expressionWithParentheses, operator)["actualExpression"] + spaces)) }

@Type
val variables by lazy { some(one(spaces + parameter["parameter"] + spaces)) }

@Type
val fNumber by lazy { same(com.momid.parser.expression.fNumber) }

@Type
val variableAccess by lazy { !"." + variable["variable"] }

@Type
val arrayAccess by lazy { !"[" + cExpression["insideArrayExpression"] + !"]" }

@Type
val atomic by lazy {
    cold {
        anyOf(variable, fNumber)["first"] + some0(anyOf(variableAccess, arrayAccess))["nextAccessItems"]
    }
}

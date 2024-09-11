package com.momid

import com.momid.parser.expression.*import com.momid.parsing_rules.allowedName
import com.momid.parsing_rules.parameter
import com.momid.parsing_rules.parameters
import com.momid.parsing_rules.functionCall
import com.momid.parsing_rules.fullFunctionCall
import com.momid.parsing_rules.numbers
import com.momid.parsing_rules.variable
import com.momid.parsing_rules.plus
import com.momid.parsing_rules.minus
import com.momid.parsing_rules.times
import com.momid.parsing_rules.divide
import com.momid.parsing_rules.operator
import com.momid.parsing_rules.operatorInExpression
import com.momid.parsing_rules.swn
import com.momid.parsing_rules.simpleExpression
import com.momid.parsing_rules.expressionWithParentheses
import com.momid.parsing_rules.cExpression
import com.momid.parsing_rules.variables
import com.momid.parsing_rules.fNumber
import com.momid.parsing_rules.variableAccess
import com.momid.parsing_rules.arrayAccess
import com.momid.parsing_rules.atomic
import com.momid.parsing_rules.klass
import com.momid.parsing_rules.type
class Atomic(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val first: Anonymous15
get() {
return Anonymous15(expressionResult["first"])
}
val nextAccessItems: Anonymous17
get() {
return Anonymous17(expressionResult["nextAccessItems"])
}
}
fun parseAtomic(tokens: String): Atomic? {
val parsed = firstEval(atomic, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Atomic(parsed)
} else {
return null
}
}
class Klass(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseKlass(tokens: String): Klass? {
val parsed = firstEval(klass, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Klass(parsed)
} else {
return null
}
}
class Type(val expressionResult: ExpressionResult, val isKlass: Klass? = if (expressionResult.content.expression == klass) {
Klass(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseType(tokens: String): Type? {
val parsed = firstEval(type, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Type(parsed)
} else {
return null
}
}
class AllowedName(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseAllowedName(tokens: String): AllowedName? {
val parsed = firstEval(allowedName, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return AllowedName(parsed)
} else {
return null
}
}
class Anonymous13(val expressionResult: ExpressionResult, val isAtomic: Atomic? = if (expressionResult.content.expression == atomic) {
Atomic(expressionResult.content)
} else {
null
},
val isOperatorInExpression: OperatorInExpression? = if (expressionResult.content.expression == operatorInExpression) {
OperatorInExpression(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Parameter(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val variableName: AllowedName
get() {
return AllowedName(expressionResult["variableName"])
}
}
fun parseParameter(tokens: String): Parameter? {
val parsed = firstEval(parameter, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Parameter(parsed)
} else {
return null
}
}
class Anonymous12(val expressionResult: ExpressionResult, val isAtomic: Atomic? = if (expressionResult.content.expression == atomic) {
Atomic(expressionResult.content)
} else {
null
},
val isOperatorInExpression: OperatorInExpression? = if (expressionResult.content.expression == operatorInExpression) {
OperatorInExpression(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Parameters(val expressionResult: ExpressionResult, val items: List<Parameter> = expressionResult.asMulti().map {
Parameter(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Parameter> by items {

}
fun parseParameters(tokens: String): Parameters? {
val parsed = firstEval(parameters, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Parameters(parsed)
} else {
return null
}
}
class Anonymous15(val expressionResult: ExpressionResult, val isVariable: Variable? = if (expressionResult.content.expression == variable) {
Variable(expressionResult.content)
} else {
null
},
val isFNumber: FNumber? = if (expressionResult.content.expression == fNumber) {
FNumber(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous2(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: Parameters
get() {
return Parameters(expressionResult["inside"])
}
}
class Anonymous19(val expressionResult: ExpressionResult, val isVariableAccess: VariableAccess? = if (expressionResult.content.expression == variableAccess) {
VariableAccess(expressionResult.content)
} else {
null
},
val isArrayAccess: ArrayAccess? = if (expressionResult.content.expression == arrayAccess) {
ArrayAccess(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous0(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: Parameters
get() {
return Parameters(expressionResult["inside"])
}
}
class Anonymous17(val expressionResult: ExpressionResult, val items: List<Anonymous19> = expressionResult.asMulti().map {
Anonymous19(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous19> by items {

}
class FunctionCall(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val functionName: AllowedName
get() {
return AllowedName(expressionResult["functionName"])
}
val parameters: Anonymous0
get() {
return Anonymous0(expressionResult["parameters"])
}
}
fun parseFunctionCall(tokens: String): FunctionCall? {
val parsed = firstEval(functionCall, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return FunctionCall(parsed)
} else {
return null
}
}
class Anonymous6(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val functionName: AllowedName
get() {
return AllowedName(expressionResult["functionName"])
}
val parameters: Anonymous0
get() {
return Anonymous0(expressionResult["parameters"])
}
}
class Anonymous5(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: Anonymous6
get() {
return Anonymous6(expressionResult["inside"])
}
}
class Anonymous3(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: Anonymous6
get() {
return Anonymous6(expressionResult["inside"])
}
}
class FullFunctionCall(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val ooo: Anonymous3
get() {
return Anonymous3(expressionResult["ooo"])
}
}
fun parseFullFunctionCall(tokens: String): FullFunctionCall? {
val parsed = firstEval(fullFunctionCall, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return FullFunctionCall(parsed)
} else {
return null
}
}
class Anonymous9(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous8(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

val isOk: Anonymous9? 
get() {
return if(expressionResult !is ErrorExpressionResult) {
Anonymous9(expressionResult)
} else {
null
}
}
val isError: ErrorExpressionResult? 
get() {
return if(expressionResult is ErrorExpressionResult)
 {expressionResult
} else {
null
}
}
}
class Numbers(val expressionResult: ExpressionResult, val items: List<Anonymous8> = expressionResult.asMulti().map {
Anonymous8(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous8> by items {

}
fun parseNumbers(tokens: String): Numbers? {
val parsed = firstEval(numbers, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Numbers(parsed)
} else {
return null
}
}
class Variable(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseVariable(tokens: String): Variable? {
val parsed = firstEval(variable, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Variable(parsed)
} else {
return null
}
}
class Plus(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parsePlus(tokens: String): Plus? {
val parsed = firstEval(plus, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Plus(parsed)
} else {
return null
}
}
class Minus(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseMinus(tokens: String): Minus? {
val parsed = firstEval(minus, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Minus(parsed)
} else {
return null
}
}
class Times(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseTimes(tokens: String): Times? {
val parsed = firstEval(times, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Times(parsed)
} else {
return null
}
}
class Divide(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseDivide(tokens: String): Divide? {
val parsed = firstEval(divide, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Divide(parsed)
} else {
return null
}
}
class Operator(val expressionResult: ExpressionResult, val isPlus: Plus? = if (expressionResult.content.expression == plus) {
Plus(expressionResult.content)
} else {
null
},
val isMinus: Minus? = if (expressionResult.content.expression == minus) {
Minus(expressionResult.content)
} else {
null
},
val isTimes: Times? = if (expressionResult.content.expression == times) {
Times(expressionResult.content)
} else {
null
},
val isDivide: Divide? = if (expressionResult.content.expression == divide) {
Divide(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseOperator(tokens: String): Operator? {
val parsed = firstEval(operator, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Operator(parsed)
} else {
return null
}
}
class OperatorInExpression(val expressionResult: ExpressionResult, val isPlus: Plus? = if (expressionResult.content.expression == plus) {
Plus(expressionResult.content)
} else {
null
},
val isMinus: Minus? = if (expressionResult.content.expression == minus) {
Minus(expressionResult.content)
} else {
null
},
val isTimes: Times? = if (expressionResult.content.expression == times) {
Times(expressionResult.content)
} else {
null
},
val isDivide: Divide? = if (expressionResult.content.expression == divide) {
Divide(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseOperatorInExpression(tokens: String): OperatorInExpression? {
val parsed = firstEval(operatorInExpression, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return OperatorInExpression(parsed)
} else {
return null
}
}
class Anonymous10(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Swn(val expressionResult: ExpressionResult, val items: List<Anonymous10> = expressionResult.asMulti().map {
Anonymous10(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous10> by items {

}
fun parseSwn(tokens: String): Swn? {
val parsed = firstEval(swn, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Swn(parsed)
} else {
return null
}
}
class SimpleExpression(val expressionResult: ExpressionResult, val items: List<Anonymous12> = expressionResult.asMulti().map {
Anonymous12(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous12> by items {

}
fun parseSimpleExpression(tokens: String): SimpleExpression? {
val parsed = firstEval(simpleExpression, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return SimpleExpression(parsed)
} else {
return null
}
}
class ExpressionWithParentheses(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: CExpression
get() {
return CExpression(expressionResult["inside"])
}
}
fun parseExpressionWithParentheses(tokens: String): ExpressionWithParentheses? {
val parsed = firstEval(expressionWithParentheses, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return ExpressionWithParentheses(parsed)
} else {
return null
}
}
class Anonymous20(val expressionResult: ExpressionResult, val isSimpleExpression: SimpleExpression? = if (expressionResult.content.expression == simpleExpression) {
SimpleExpression(expressionResult.content)
} else {
null
},
val isExpressionWithParentheses: ExpressionWithParentheses? = if (expressionResult.content.expression == expressionWithParentheses) {
ExpressionWithParentheses(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class CExpression(val expressionResult: ExpressionResult, val items: List<Anonymous20> = expressionResult.asMulti().map {
Anonymous20(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous20> by items {

}
fun parseCExpression(tokens: String): CExpression? {
val parsed = firstEval(cExpression, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return CExpression(parsed)
} else {
return null
}
}
class Anonymous22(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val variableName: AllowedName
get() {
return AllowedName(expressionResult["variableName"])
}
}
class Variables(val expressionResult: ExpressionResult, val items: List<Anonymous22> = expressionResult.asMulti().map {
Anonymous22(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous22> by items {

}
fun parseVariables(tokens: String): Variables? {
val parsed = firstEval(variables, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Variables(parsed)
} else {
return null
}
}
class FNumber(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseFNumber(tokens: String): FNumber? {
val parsed = firstEval(fNumber, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return FNumber(parsed)
} else {
return null
}
}
class VariableAccess(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val variable: Variable
get() {
return Variable(expressionResult["variable"])
}
}
fun parseVariableAccess(tokens: String): VariableAccess? {
val parsed = firstEval(variableAccess, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return VariableAccess(parsed)
} else {
return null
}
}
class ArrayAccess(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val insideArrayExpression: CExpression
get() {
return CExpression(expressionResult["insideArrayExpression"])
}
}
fun parseArrayAccess(tokens: String): ArrayAccess? {
val parsed = firstEval(arrayAccess, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return ArrayAccess(parsed)
} else {
return null
}
}

package com.momid

import com.momid.parser.expression.*import com.momid.parsing_rules.allowedName
import com.momid.parsing_rules.parameter
import com.momid.parsing_rules.parameters
import com.momid.parsing_rules.functionCall
import com.momid.parsing_rules.fullFunctionCall
import com.momid.parsing_rules.klass
import com.momid.parsing_rules.genericType
import com.momid.parsing_rules.type
import com.momid.parsing_rules.requiresType
import com.momid.parsing_rules.types
import com.momid.parsing_rules.numbers
import com.momid.parsing_rules.optionalTypes
import com.momid.parsing_rules.variable
import com.momid.parsing_rules.plus
import com.momid.parsing_rules.minus
import com.momid.parsing_rules.times
import com.momid.parsing_rules.divide
import com.momid.parsing_rules.operator
import com.momid.parsing_rules.simpleExpression
import com.momid.parsing_rules.expressionWithParentheses
import com.momid.parsing_rules.cExpression
import com.momid.parsing_rules.variables
import com.momid.parsing_rules.fNumber
import com.momid.parsing_rules.variableAccess
import com.momid.parsing_rules.arrayAccess
import com.momid.parsing_rules.atomic
class Anonymous21(val expressionResult: ExpressionResult, val isAtomic: Atomic? = if (expressionResult.content.expression == atomic) {
Atomic(expressionResult.content)
} else {
null
},
val isOperator: Operator? = if (expressionResult.content.expression == operator) {
Operator(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class SimpleExpression(val expressionResult: ExpressionResult, val items: List<Anonymous21> = expressionResult.asMulti().map {
Anonymous21(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous21> by items {

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
class Anonymous30(val expressionResult: ExpressionResult, val isSimpleExpression: SimpleExpression? = if (expressionResult.content.expression == simpleExpression) {
SimpleExpression(expressionResult.content)
} else {
null
},
val isExpressionWithParentheses: ExpressionWithParentheses? = if (expressionResult.content.expression == expressionWithParentheses) {
ExpressionWithParentheses(expressionResult.content)
} else {
null
},
val isOperator: Operator? = if (expressionResult.content.expression == operator) {
Operator(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous29(val expressionResult: ExpressionResult, val isSimpleExpression: SimpleExpression? = if (expressionResult.content.expression == simpleExpression) {
SimpleExpression(expressionResult.content)
} else {
null
},
val isExpressionWithParentheses: ExpressionWithParentheses? = if (expressionResult.content.expression == expressionWithParentheses) {
ExpressionWithParentheses(expressionResult.content)
} else {
null
},
val isOperator: Operator? = if (expressionResult.content.expression == operator) {
Operator(expressionResult.content)
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
class CExpression(val expressionResult: ExpressionResult, val items: List<Anonymous29> = expressionResult.asMulti().map {
Anonymous29(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous29> by items {

}
fun parseCExpression(tokens: String): CExpression? {
val parsed = firstEval(cExpression, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return CExpression(parsed)
} else {
return null
}
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
class Anonymous32(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val variableName: AllowedName
get() {
return AllowedName(expressionResult["variableName"])
}
}
class Variables(val expressionResult: ExpressionResult, val items: List<Anonymous32> = expressionResult.asMulti().map {
Anonymous32(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous32> by items {

}
fun parseVariables(tokens: String): Variables? {
val parsed = firstEval(variables, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Variables(parsed)
} else {
return null
}
}
class Anonymous2(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: Parameters
get() {
return Parameters(expressionResult["inside"])
}
}
class Anonymous0(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val inside: Parameters
get() {
return Parameters(expressionResult["inside"])
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
class Anonymous24(val expressionResult: ExpressionResult, val isVariable: Variable? = if (expressionResult.content.expression == variable) {
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
class Anonymous28(val expressionResult: ExpressionResult, val isVariableAccess: VariableAccess? = if (expressionResult.content.expression == variableAccess) {
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
class GenericType(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val name: AllowedName
get() {
return AllowedName(expressionResult["name"])
}
val typeParameter: Type
get() {
return Type(expressionResult["typeParameter"])
}
}
fun parseGenericType(tokens: String): GenericType? {
val parsed = firstEval(genericType, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return GenericType(parsed)
} else {
return null
}
}
class Anonymous26(val expressionResult: ExpressionResult, val items: List<Anonymous28> = expressionResult.asMulti().map {
Anonymous28(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous28> by items {

}
class Type(val expressionResult: ExpressionResult, val isGenericType: GenericType? = if (expressionResult.content.expression == genericType) {
GenericType(expressionResult.content)
} else {
null
},
val isKlass: Klass? = if (expressionResult.content.expression == klass) {
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
class Anonymous9(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous13(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous11(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

val isOk: Anonymous13? 
get() {
return if(expressionResult !is ErrorExpressionResult) {
Anonymous13(expressionResult)
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
class RequiresType(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val ooo: Anonymous9
get() {
return Anonymous9(expressionResult["ooo"])
}
val some: Anonymous11
get() {
return Anonymous11(expressionResult["some"])
}
val someooo: Anonymous14
get() {
return Anonymous14(expressionResult["someooo"])
}
}
fun parseRequiresType(tokens: String): RequiresType? {
val parsed = firstEval(requiresType, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return RequiresType(parsed)
} else {
return null
}
}
class Anonymous14(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous16(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

val isOk: Type? 
get() {
return if(expressionResult !is ErrorExpressionResult) {
Type(expressionResult)
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
class Types(val expressionResult: ExpressionResult, val items: List<Anonymous16> = expressionResult.asMulti().map {
Anonymous16(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous16> by items {

}
fun parseTypes(tokens: String): Types? {
val parsed = firstEval(types, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Types(parsed)
} else {
return null
}
}
class Anonymous18(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class Anonymous17(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

val isOk: Anonymous18? 
get() {
return if(expressionResult !is ErrorExpressionResult) {
Anonymous18(expressionResult)
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
class Numbers(val expressionResult: ExpressionResult, val items: List<Anonymous17> = expressionResult.asMulti().map {
Anonymous17(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous17> by items {

}
fun parseNumbers(tokens: String): Numbers? {
val parsed = firstEval(numbers, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Numbers(parsed)
} else {
return null
}
}
class Anonymous19(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

val isPresent: AllowedName? 
get() {
return if(expressionResult.range.first != expressionResult.range.last) {
AllowedName(expressionResult)
} else {
null
}
}
val isNotPresent: Boolean 
get() {
return expressionResult.range.first == expressionResult.range.last
}
}
class OptionalTypes(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val identifier: Anonymous19
get() {
return Anonymous19(expressionResult["identifier"])
}
val type: Type
get() {
return Type(expressionResult["type"])
}
}
fun parseOptionalTypes(tokens: String): OptionalTypes? {
val parsed = firstEval(optionalTypes, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return OptionalTypes(parsed)
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
class Atomic(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val first: Anonymous24
get() {
return Anonymous24(expressionResult["first"])
}
val nextAccessItems: Anonymous26
get() {
return Anonymous26(expressionResult["nextAccessItems"])
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
class Anonymous22(val expressionResult: ExpressionResult, val isAtomic: Atomic? = if (expressionResult.content.expression == atomic) {
Atomic(expressionResult.content)
} else {
null
},
val isOperator: Operator? = if (expressionResult.content.expression == operator) {
Operator(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}

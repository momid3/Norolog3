package com.momid

import com.momid.parser.expression.*import com.momid.parsing_rules.allowedName
import com.momid.parsing_rules.parameter
import com.momid.parsing_rules.parameters
import com.momid.parsing_rules.functionCall
import com.momid.parsing_rules.fullFunctionCall
import com.momid.parsing_rules.klass
import com.momid.parsing_rules.genericType
import com.momid.parsing_rules.requiresType
import com.momid.parsing_rules.types
import com.momid.parsing_rules.numbers
import com.momid.parsing_rules.optionalTypes
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
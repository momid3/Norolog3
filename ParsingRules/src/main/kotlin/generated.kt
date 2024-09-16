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
import com.momid.parsing_rules.nextAccessItem
import com.momid.parsing_rules.atomic
import com.momid.parsing_rules.codeBlock
import com.momid.parsing_rules.declaration
import com.momid.parsing_rules.functionArgument
import com.momid.parsing_rules.typeParameter
import com.momid.parsing_rules.functionTypeParameters
import com.momid.parsing_rules.functionDeclaration
import com.momid.parsing_rules.programDeclaration
import com.momid.parsing_rules.program
import com.momid.parsing_rules.statement
import com.momid.parsing_rules.klass
import com.momid.parsing_rules.type
import com.momid.parsing_rules.declaredVariable
import com.momid.parsing_rules.variableDeclaration
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
class DeclaredVariable(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseDeclaredVariable(tokens: String): DeclaredVariable? {
val parsed = firstEval(declaredVariable, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return DeclaredVariable(parsed)
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
class Anonymous19(val expressionResult: ExpressionResult, val isSimpleExpression: SimpleExpression? = if (expressionResult.content.expression == simpleExpression) {
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
class CExpression(val expressionResult: ExpressionResult, val items: List<Anonymous19> = expressionResult.asMulti().map {
Anonymous19(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous19> by items {

}
fun parseCExpression(tokens: String): CExpression? {
val parsed = firstEval(cExpression, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return CExpression(parsed)
} else {
return null
}
}
class Anonymous21(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val variableName: AllowedName
get() {
return AllowedName(expressionResult["variableName"])
}
}
class Variables(val expressionResult: ExpressionResult, val items: List<Anonymous21> = expressionResult.asMulti().map {
Anonymous21(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous21> by items {

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
class NextAccessItem(val expressionResult: ExpressionResult, val isVariableAccess: VariableAccess? = if (expressionResult.content.expression == variableAccess) {
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
fun parseNextAccessItem(tokens: String): NextAccessItem? {
val parsed = firstEval(nextAccessItem, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return NextAccessItem(parsed)
} else {
return null
}
}
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
class VariableDeclaration(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val variableName: AllowedName
get() {
return AllowedName(expressionResult["variableName"])
}
val assignedValue: CExpression
get() {
return CExpression(expressionResult["assignedValue"])
}
}
fun parseVariableDeclaration(tokens: String): VariableDeclaration? {
val parsed = firstEval(variableDeclaration, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return VariableDeclaration(parsed)
} else {
return null
}
}
class Statement(val expressionResult: ExpressionResult, val isVariableDeclaration: VariableDeclaration? = if (expressionResult.content.expression == variableDeclaration) {
VariableDeclaration(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseStatement(tokens: String): Statement? {
val parsed = firstEval(statement, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Statement(parsed)
} else {
return null
}
}
class Anonymous22(val expressionResult: ExpressionResult, val isVariableDeclaration: VariableDeclaration? = if (expressionResult.content.expression == variableDeclaration) {
VariableDeclaration(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
class CodeBlock(val expressionResult: ExpressionResult, val items: List<Anonymous22> = expressionResult.asMulti().map {
Anonymous22(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<Anonymous22> by items {

}
fun parseCodeBlock(tokens: String): CodeBlock? {
val parsed = firstEval(codeBlock, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return CodeBlock(parsed)
} else {
return null
}
}
class TypeParameter(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseTypeParameter(tokens: String): TypeParameter? {
val parsed = firstEval(typeParameter, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return TypeParameter(parsed)
} else {
return null
}
}
class FunctionTypeParameters(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseFunctionTypeParameters(tokens: String): FunctionTypeParameters? {
val parsed = firstEval(functionTypeParameters, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return FunctionTypeParameters(parsed)
} else {
return null
}
}
class FunctionDeclaration(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val functionTypeParameter: FunctionTypeParameters
get() {
return FunctionTypeParameters(expressionResult["functionTypeParameter"])
}
val functionName: AllowedName
get() {
return AllowedName(expressionResult["functionName"])
}
val arguments: Anonymous23
get() {
return Anonymous23(expressionResult["arguments"])
}
val codeBlock: CodeBlock
get() {
return CodeBlock(expressionResult["codeBlock"])
}
}
fun parseFunctionDeclaration(tokens: String): FunctionDeclaration? {
val parsed = firstEval(functionDeclaration, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return FunctionDeclaration(parsed)
} else {
return null
}
}
class FunctionArgument(val expressionResult: ExpressionResult): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {
val name: AllowedName
get() {
return AllowedName(expressionResult["name"])
}
val type: Type
get() {
return Type(expressionResult["type"])
}
}
fun parseFunctionArgument(tokens: String): FunctionArgument? {
val parsed = firstEval(functionArgument, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return FunctionArgument(parsed)
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
class Anonymous23(val expressionResult: ExpressionResult, val items: List<FunctionArgument> = expressionResult.asMulti().map {
FunctionArgument(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<FunctionArgument> by items {

}
class Declaration(val expressionResult: ExpressionResult, val isFunctionDeclaration: FunctionDeclaration? = if (expressionResult.content.expression == functionDeclaration) {
FunctionDeclaration(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseDeclaration(tokens: String): Declaration? {
val parsed = firstEval(declaration, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Declaration(parsed)
} else {
return null
}
}
class ProgramDeclaration(val expressionResult: ExpressionResult, val isFunctionDeclaration: FunctionDeclaration? = if (expressionResult.content.expression == functionDeclaration) {
FunctionDeclaration(expressionResult.content)
} else {
null
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex) {

}
fun parseProgramDeclaration(tokens: String): ProgramDeclaration? {
val parsed = firstEval(programDeclaration, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return ProgramDeclaration(parsed)
} else {
return null
}
}
class Program(val expressionResult: ExpressionResult, val items: List<ProgramDeclaration> = expressionResult.asMulti().map {
ProgramDeclaration(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<ProgramDeclaration> by items {

}
fun parseProgram(tokens: String): Program? {
val parsed = firstEval(program, 0, tokens.toList(), tokens.length)
if (parsed != null) {
return Program(parsed)
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
class Anonymous17(val expressionResult: ExpressionResult, val items: List<NextAccessItem> = expressionResult.asMulti().map {
NextAccessItem(it)
}): ExpressionResult(expressionResult.parser, expressionResult.expression, expressionResult.range, expressionResult.nextTokenIndex), List<NextAccessItem> by items {

}

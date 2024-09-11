package com.momid.parser

import com.momid.CExpression
import com.momid.parseCExpression
import com.momid.parser.expression.text
import com.momid.parser.expression.then

fun main() {
    val cExpressionTokens = "(some.other[some] + other) + 3 * 8 + 333"
    val cExpression = parseCExpression(cExpressionTokens)!!
    writeExpressionParsing(cExpression)
}

fun writeExpressionParsing(cExpression: CExpression) {
    println(cExpression.text)
    cExpression.forEach {
        it.isSimpleExpression.then {
            it.forEach {
                it.isAtomic.then {
                    it.first.isVariable.then {
                        println("variable " + it.text)
                    }
                    it.first.isFNumber.then {
                        println("number " + it.text)
                    }
                    it.nextAccessItems.forEach {
                        it.isVariableAccess.then {
                            println("variable access " + it.variable.text)
                        }
                        it.isArrayAccess.then {
                            println("array access " + "[" + it.insideArrayExpression.text + "]")
                        }
                    }
                }
                it.isOperatorInExpression.then {
                    println("operator " + it.text)
                }
            }
        }

        it.isExpressionWithParentheses.then {
            writeExpressionParsing(it.inside)
        }
    }
}

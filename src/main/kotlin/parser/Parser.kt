package com.momid.parser

import com.momid.CExpression
import com.momid.Program
import com.momid.parseCExpression
import com.momid.parseProgram
import com.momid.parser.expression.text
import com.momid.parser.expression.then
import java.io.File

fun parseProgramFiles(projectRootDirectory: File): List<Pair<Program, File>> {
    val parsing = ArrayList<Pair<Program, File>>()
    if (projectRootDirectory.exists()) {
        if (projectRootDirectory.isDirectory) {
            projectRootDirectory.listFiles()?.forEach {
                parsing.addAll(parseProgramFiles(it))
            }
        } else {
            val programFileParsing = parseProgram(projectRootDirectory.readText())
                ?: throw (Throwable("there is an issue parsing file " + projectRootDirectory.path))
            parsing.add(Pair(programFileParsing, projectRootDirectory))
        }
    }
    return parsing
}

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

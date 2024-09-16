package com.momid.analysis

import com.momid.*
import com.momid.Atomic
import com.momid.CExpression
import com.momid.SimpleExpression
import com.momid.Variable
import com.momid.data.*
import com.momid.data.CodeBlock
import com.momid.data.Type
import com.momid.data.VariableDeclaration
import com.momid.parser.expression.text
import com.momid.parser.expression.then

fun Asg.analyzeCExpression(cExpression: CExpression): Data<CExpressionAsg> {
    var type: Data<Type> = NoData()
    val items = ArrayList<Data<Asg>>()
    cExpression.forEach {
        it.isSimpleExpression.then {
            val simpleExpression = analyzeSimpleExpression(it)
            type = simpleExpression.access { type }
            items.add(simpleExpression.toAsg())
        }

        it.isExpressionWithParentheses.then {
            items.add(analyzeExpressionWithParentheses(it).toAsg())
        }
    }

    return Ok(CExpressionAsg(this, cExpression, items, NoData()))
}

fun Asg.analyzeSimpleExpression(simpleExpression: SimpleExpression): Data<SimpleExpressionAsg> {
    var type: Data<Type> = NoData()
    val items = ArrayList<Data<Asg>>()
    simpleExpression.forEach {
        it.isAtomic.then {
            val atomic = analyzeAtomic(it)
            type = atomic.access { type }
            items.add(atomic.toAsg())
        }

        it.isOperatorInExpression.then {
            val operator = Ok(OperatorAsg(this, it))
            items.add(operator.toAsg())
        }
    }

    return Ok(SimpleExpressionAsg(this, simpleExpression, items, type))
}

fun Asg.analyzeExpressionWithParentheses(expressionWithParentheses: ExpressionWithParentheses): Data<CExpressionWithParenthesesAsg> {
    val cExpression = analyzeCExpression(expressionWithParentheses.inside)
    return Ok(CExpressionWithParenthesesAsg(this, expressionWithParentheses, cExpression, cExpression.access { type }))
}

fun Asg.analyzeAtomic(atomic: Atomic): Data<AtomicAsg> {
    val createdAtomic: AtomicAsg
    var firstAccessItem: Data<FirstAccessItemAsg> = NoData()
    var type: Data<Type> = NoData()

    val first = atomic.first

    first.isVariable.then {
        val variable = analyzeVariable(it)
        firstAccessItem = if (variable is Ok) {
            type = variable.data.type
            Ok(FirstAccessItemAsg(this, atomic.first, variable.data, variable.data.type))
        } else {
            NoData()
        }
    }

    createdAtomic = AtomicAsg(
        this,
        atomic,
        firstAccessItem,
        listOf(),
        type
    )

    return Ok(createdAtomic)
}

fun Asg.analyzeVariable(variable: Variable): Data<VariableAsg> {
    val resolved = resolveVariable(variable)
    if (resolved == null) {
        return ErrorData()
    } else {
        val type = if (resolved is VariableDeclaration) {
            resolved.variableType
        } else {
            throw (Throwable("variable source is not valid"))
        }
        return Ok(VariableAsg(this, variable, resolved, Ok(type)))
    }
}

fun Asg.resolveVariable(variable: Variable): Asg? {
    if (this is CodeBlock) {
        this.items.forEach {
            if (it is VariableDeclaration) {
                if (it.variableName == variable.text) {
                    return it
                }
            }
        }
    }
    if (this.upper == null) {
        return null
    } else {
        return this.upper.resolveVariable(variable)
    }
}

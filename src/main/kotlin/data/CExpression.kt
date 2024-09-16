package com.momid.data

import com.momid.*
import com.momid.ArrayAccess
import com.momid.Atomic
import com.momid.CExpression
import com.momid.DeclaredVariable
import com.momid.NextAccessItem
import com.momid.Operator
import com.momid.SimpleExpression
import com.momid.Variable
import com.momid.VariableAccess
import com.momid.parser.expression.ExpressionResult

class OperatorAsg(upper: Asg?, val parsing: OperatorInExpression): Asg(upper)

class DeclaredVariableAsg(upper: Asg?, val parsing: DeclaredVariable, val name: String, val type: Data<Type>, val scope: Asg): Asg(upper)

class VariableAsg(upper: Asg?, val parsing: Variable, val declaredVariableRoot: Asg, val type: Data<Type>): Asg(upper)

class VariableAccessAsg(upper: Asg?, val parsing: VariableAccess, val name: String, val type: Data<Type>): Asg(upper)

class ArrayAccessAsg(upper: Asg?, val parsing: ArrayAccess, val accessInsideExpression: CExpressionAsg, val type: Data<Type>): Asg(upper)

class FirstAccessItemAsg(upper: Asg?, val parsing: ExpressionResult, val item: Asg, val type: Data<Type>): Asg(upper)

class NextAccessItemAsg(upper: Asg?, val parsing: NextAccessItem, val item: Asg, val type: Data<Type>): Asg(upper)

class AtomicAsg(upper: Asg?, val parsing: Atomic, val firstAccessItem: Data<FirstAccessItemAsg>, val nextAccessItems: List<NextAccessItemAsg>, val type: Data<Type>): Asg(upper)

class SimpleExpressionAsg(upper: Asg?, val parsing: SimpleExpression, val items: List<Data<Asg>>, val type: Data<Type>): Asg(upper)

class CExpressionWithParenthesesAsg(upper: Asg?, val parsing: ExpressionWithParentheses, val inside: Data<CExpressionAsg>, val type: Data<Type>): Asg(upper)

class CExpressionAsg(upper: Asg?, val parsing: CExpression, val items: List<Data<Asg>>, val type: Data<Type>): Asg(upper)

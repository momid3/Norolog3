package com.momid.data

import com.momid.VariableDeclaration

class VariableDeclaration(upper: Asg?, val parsing: VariableDeclaration, val variableName: String, val variableType: Type, val valueCExpression: CExpressionAsg): Asg(upper)

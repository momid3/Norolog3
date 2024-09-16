package com.momid.data

import com.momid.FunctionArgument
import com.momid.FunctionDeclaration
import com.momid.TypeParameter

class FunctionArgumentAsg(
    upper: Asg?,
    val parsing: FunctionArgument,
    val name: Data<String>,
    val type: Data<Type>
): Asg(upper)

class TypeParameterAsg(
    upper: Asg?,
    val parsing: TypeParameter,
    val name: Data<String>,
    val actualType: Data<Type?>
): Asg(upper)

class FunctionAsg(
    upper: Asg?,
    val parsing: FunctionDeclaration,
    val packageName: Data<String?>,
    val name: Data<String>,
    val arguments: Data<List<FunctionArgument>>,
    val codeBlock: CodeBlock,
    val typeParameters: Data<TypeParameter?>
): Asg(upper)

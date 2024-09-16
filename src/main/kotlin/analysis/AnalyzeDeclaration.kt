package com.momid.analysis

import com.momid.ProgramDeclaration
import com.momid.data.*
import com.momid.parser.expression.then

fun Asg.analyzeDeclaration(declaration: ProgramDeclaration): Data<DeclarationAsg> {
    var asg: Data<Asg> = NoData()

    declaration.isFunctionDeclaration.then {
        asg = analyzeFunctionDeclaration(it).toAsg()
    }

    return Ok(DeclarationAsg(this, declaration, asg))
}

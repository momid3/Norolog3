package com.momid.analysis

import com.momid.Program
import com.momid.data.*
import com.momid.parser.expression.then

fun Asg.analyzeProgram(program: Program): Data<ProgramAsg> {
    val declarations = program.map {
        analyzeDeclaration(it)
    }
    return Ok(ProgramAsg(this, program, declarations))
}

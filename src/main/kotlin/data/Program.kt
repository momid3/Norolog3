package com.momid.data

import com.momid.Program

class ProgramAsg(upper: Asg?, val parsing: Program, val declarations: List<Data<DeclarationAsg>>): Asg(upper)

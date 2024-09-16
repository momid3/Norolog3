package com.momid.data

import com.momid.CodeBlock

class CodeBlock(upper: Asg?, val parsing: CodeBlock, val items: List<Asg>): Asg(upper)

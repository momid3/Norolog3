package com.momid.parser.expression

class Parser() {
    var tokens: List<Char>? = null

    val errors = HashMap<ExpressionResult, Expression?>()
    var isCurrentError: Boolean = false
    val endIfConditions = ArrayList<Conditioning>()
}

class Conditioning(var end: Boolean)

package com.momid.parsing_rules

import com.momid.parser.expression.*
import com.momid.parser.not
import com.momid.type.Type

@Type
val functionArgument by lazy { allowedName["name"] + spaces + !":" + spaces + type["type"] }

@Type
val typeParameter by lazy { same(allowedName) }

@Type
val functionTypeParameters by lazy { !"<" + splitBy(one(spaces + typeParameter["typeParameter"] + spaces), ",") }

@Type
val functionDeclaration by lazy {
    !"fun" + functionTypeParameters["functionTypeParameter"] + space + allowedName["functionName"] + !"(" + spaces + splitBy(functionArgument, ",")["arguments"] + spaces + !")" +
            spaces + !"{" + spaces + codeBlock["codeBlock"] + !"}"
}

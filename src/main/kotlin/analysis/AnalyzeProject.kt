package com.momid.analysis

import com.momid.Program
import com.momid.data.Data
import com.momid.data.FileAsg
import com.momid.data.Ok
import com.momid.data.ProjectAsg
import java.io.File

fun analyzeProject(files: List<Pair<Program, File>>): Data<ProjectAsg> {
    val root = ProjectAsg(listOf())
    root.files = files.map {
        val (program, file) = it
        val asg = root.analyzeProgram(program)
        FileAsg(root, asg, file)
    }

    return Ok(root)
}

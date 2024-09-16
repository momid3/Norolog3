package com.momid.data

import java.io.File

class ProjectAsg(var files: List<FileAsg>): Asg(null)

class FileAsg(upper: Asg?, val program: Data<ProgramAsg>, val file: File): Asg(upper)

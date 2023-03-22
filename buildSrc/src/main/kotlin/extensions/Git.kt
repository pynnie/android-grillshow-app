package extensions

import org.gradle.api.Project

fun Project.commitCount(): Int {
    val command = "git rev-list --all --count".split(" ")
    val process = ProcessBuilder(command)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .start()
    return String(process.inputStream.readBytes()).filter { it.isDigit() }.toInt()
}

fun Project.gitBranch(): String {
    val command = "git rev-parse --abbrev-ref HEAD".split(" ")
    val process = ProcessBuilder(command)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .start()
    return String(process.inputStream.readBytes())
}

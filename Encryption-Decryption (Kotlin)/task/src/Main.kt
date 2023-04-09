package encryptdecrypt

import encryptdecrypt.Decrypt.allKeyDecrypt
import encryptdecrypt.Encrypt.allKeyEncrypt
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap


fun main(args: Array<String>) {
    if (args.isNotEmpty() && args.size % 2 == 0) {
        val triple = validateArgs(args)
        val arguments = triple.first
        val dataSources = triple.second
        val isValidInput = triple.third
        if (isValidInput) {
            run(arguments, dataSources)
        } else {
            println("check input")
        }
    } else {
        println("check input")
    }
}

private fun validateArgs(args: Array<String>): Triple<HashMap<String, String?>, Int, Boolean> {
    var validInput = true
    val arguments = HashMap<String, String?>()
    var dataSources = 0
    var i = 0
    while (i < args.size) {
        if (args[i].startsWith("-") && args[i].matches("-mode|-key|-data|-in|-out".toRegex())) {
            if (args[i].matches("-data|-in".toRegex())) {
                dataSources++
            }
            arguments[args[i]] = args[i + 1]
        } else {
            validInput = false
            break
        }
        i += 2
    }
    return Triple(arguments, dataSources, validInput)
}

private fun run(arguments: HashMap<String, String?>, dataSources: Int) {
    val opt = arguments.getOrDefault("-mode", "enc")
    val key = arguments.getOrDefault("-key", "0")
    var input = arguments.getOrDefault("-data", "")!!.replace("\"".toRegex(), "")
    val inPath = arguments.getOrDefault("-in", null)
    val outPath = arguments.getOrDefault("-out", null)
    if (dataSources == 2) {
        println(run(opt, key, input))
    } else {
        if (inPath == null || outPath == null) println(run(opt!!, key!!, input)) else {
            val inFile = File(inPath)
            val outFile = File(outPath)
            try {
                Scanner(inFile).use { `in` ->
                    FileWriter(outFile).use { out ->
                        while (`in`.hasNext()) {
                            input = `in`.nextLine()
                        }
                        val output: String = run(opt, key, input)
                        out.write(output)
                    }
                }
            } catch (e: IOException) {
                println("Error")
            }
        }
    }
}

fun run(opt: String?, keyS: String?, input: String?): String {
    var ret: String
    try {
        val operation = opt?.let { Operation.valueOf(it.uppercase()) }
        var key = 0
        try {
            if (keyS != null) {
                key = keyS.toInt()
            }
        } catch (_: IllegalArgumentException) {
            // do nothing
        }
        ret = when (operation) {
            Operation.ENC -> input?.let { allKeyEncrypt(it, key) }.toString()
            Operation.DEC -> input?.let { allKeyDecrypt(it, key) }.toString()
            else -> {
                throw IllegalArgumentException()
            }
        }
    } catch (e: IllegalArgumentException) {
        ret = "enter a valid operation"
    }
    return ret
}
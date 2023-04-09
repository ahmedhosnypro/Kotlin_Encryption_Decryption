package encryptdecrypt

import encryptdecrypt.Start.start
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*


object InputProcessor {
    fun process(args: Array<String>) {
        if (args.isNotEmpty() && args.size % 2 == 0) {
            val arguments = HashMap<String, String>()
            val (dataSources, validInput) = isValidArgs(args, arguments)

            if (validInput) {
                extracted(arguments, dataSources)
            } else println("check input")
        } else println("check input")
    }

    private fun extracted(arguments: HashMap<String, String>, dataSources: Int) {
        val opt = arguments.getOrDefault("-mode", "enc")
        val key = arguments.getOrDefault("-key", "0")
        var input = arguments.getOrDefault("-data", "").replace("\"".toRegex(), "")
        val algorithm = arguments.getOrDefault("-alg", "shift")
        val inPath = arguments.getOrDefault("-in", null)
        val outPath = arguments.getOrDefault("-out", null)
        if (dataSources == 2) {
            println(start(algorithm, opt, key, input))
        } else {
            if (inPath == null || outPath == null) println(start(algorithm, opt, key, input)) else {
                val inFile = File(inPath)
                val outFile = File(outPath)
                try {
                    Scanner(inFile).use { `in` ->
                        FileWriter(outFile).use { out ->
                            while (`in`.hasNext()) {
                                input = `in`.nextLine()
                            }
                            val output = start(algorithm, opt, key, input)
                            out.write(output)
                        }
                    }
                } catch (e: IOException) {
                    println("Error")
                }
            }
        }
    }

    private fun isValidArgs(args: Array<String>, arguments: HashMap<String, String>): Pair<Int, Boolean> {
        var validInput = true
        var dataSources = 0
        var i = 0
        while (i < args.size) {
            if (args[i].startsWith("-") && args[i].matches("-mode|-key|-data|-in|-out|-alg".toRegex())) {
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
        return Pair(dataSources, validInput)
    }
}
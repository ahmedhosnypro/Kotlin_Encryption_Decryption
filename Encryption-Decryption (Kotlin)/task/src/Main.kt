package encryptdecrypt

import encryptdecrypt.Decrypt.allKeyDecrypt
import encryptdecrypt.Encrypt.allKeyEncrypt


fun main(args: Array<String>) {
    if (args.isNotEmpty() && args.size % 2 == 0) {
        val arguments = HashMap<String, String>()
        var i = 0
        while (i < args.size) {
            arguments[args[i]] = args[i + 1]
            i += 2
        }
        val opt = arguments.getOrDefault("-mode", "enc")
        val key = arguments.getOrDefault("-key", "0")
        val input = arguments.getOrDefault("-data", "").replace("\"".toRegex(), "")
        start(opt, key, input)
    } else {
        println("check input")
    }
}

fun start(opt: String, keyS: String, input: String?) {
    try {
        val operation = Operation.valueOf(opt.uppercase())
        var key = 0
        try {
            key = keyS.toInt()
        } catch (e: IllegalArgumentException) {
            println("enter a number")
        }
        when (operation) {
            Operation.ENC -> input?.let { allKeyEncrypt(it, key) }
            Operation.DEC -> input?.let { allKeyDecrypt(it, key) }
        }
    } catch (e: IllegalArgumentException) {
        println("enter a valid operation")
    }
}
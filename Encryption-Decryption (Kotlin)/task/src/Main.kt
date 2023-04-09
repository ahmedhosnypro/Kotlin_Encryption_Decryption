package encryptdecrypt

import encryptdecrypt.Decrypt.allKeyDecrypt
import encryptdecrypt.Encrypt.allKeyEncrypt


fun main() {

    val opt = readln().trim { it <= ' ' }.uppercase()

    try {
        val operation: Operation = Operation.valueOf(opt)
        val input =  readln().trim { it <= ' ' }
        val keyS =  readln().trim { it <= ' ' }
        var key = 0
        try {
            key = keyS.toInt()
        } catch (e: IllegalArgumentException) {
            println("enter a number")
        }
        when (operation) {
            Operation.ENC -> allKeyEncrypt(input, key)
            Operation.DEC -> allKeyDecrypt(input, key)
        }
    } catch (e: IllegalArgumentException) {
        println("enter a valid operation")
    }
}
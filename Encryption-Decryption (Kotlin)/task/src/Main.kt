package encryptdecrypt

import encryptdecrypt.Encrypt.keyEncrypt

fun main() {
    val s = readln().trim { it <= ' ' }
    val keyS = readln().trim { it <= ' ' }
    var key = 0
    try {
        key = keyS.toInt()
    } catch (e: IllegalArgumentException) {
        println("enter a number")
    }
    if (key != 0) {
        keyEncrypt(s, key)
    } else {
        println("key shouldn't be zero")
    }
}
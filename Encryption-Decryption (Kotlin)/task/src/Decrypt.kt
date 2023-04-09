package encryptdecrypt


object Decrypt {
    fun allKeyDecrypt(cypherText: String, key: Int) {
        val sb = StringBuilder()
        for (element in cypherText) {
            val newChar = (element.code - key).toChar()
            sb.append(newChar)
        }
        println(sb)
    }
}
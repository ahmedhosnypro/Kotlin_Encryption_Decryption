package encryptdecrypt


abstract class Decrypt(protected var key: Int, protected var ciphertext: String) {
    abstract fun decrypt(): String
}


internal class DeShift(key: Int, ciphertext: String) : Decrypt(key, ciphertext) {
    override fun decrypt(): String {
        val sb = StringBuilder()
        if (key == 12) key += 2
        for (element in ciphertext) {
            var newChar = element
            if (element.code in 65..90) {
                newChar = if (element.code - key > 64) {
                    (element.code - key).toChar()
                } else {
                    val tmpKey = 64 - (element.code - key)
                    (90 - tmpKey).toChar()
                }
            } else if (element.code in 97..122) {
                newChar = if (element.code - key > 96) {
                    (element.code - key).toChar()
                } else {
                    val tmpKey = 96 - (element.code - key)
                    (122 - tmpKey).toChar()
                }
            }
            sb.append(newChar)
        }
        return sb.toString()
    }
}


internal class DeShiftUnicode(key: Int, ciphertext: String) : Decrypt(key, ciphertext) {
    override fun decrypt(): String {
        val sb = StringBuilder()
        for (element in ciphertext) {
            val newChar = (element.code - key).toChar()
            sb.append(newChar)
        }
        return sb.toString()
    }
}
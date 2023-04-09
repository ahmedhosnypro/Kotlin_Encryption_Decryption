package encryptdecrypt

import java.util.*


internal object Encryptor {
    fun encrypt(algorithm: String?, message: String?, key: Int): String {
        var encryptor: Encrypt? = null
        when (algorithm) {
            "shift" -> encryptor = Shift(key, message!!)
            "unicode" -> encryptor = Unicode(key, message!!)
        }
        assert(encryptor != null)
        return encryptor!!.encrypt()
    }
}


internal object Decrypts {
    fun decrypt(algorithm: String?, ciphertext: String?, key: Int): String {
        var decrypts: Decrypt? = null
        when (algorithm) {
            "shift" -> decrypts = DeShift(key, ciphertext!!)
            "unicode" -> decrypts = DeShiftUnicode(key, ciphertext!!)
        }
        assert(decrypts != null)
        return decrypts!!.decrypt()
    }
}


object Start {
    fun start(algorithm: String?, opt: String, keyS: String, input: String?): String {
        var ret: String
        try {
            val operation = Operation.valueOf(opt.uppercase(Locale.getDefault()))
            var key = 0
            try {
                key = keyS.toInt()
            } catch (_: IllegalArgumentException) {
                // do nothing
            }
            ret = when (operation) {
                Operation.ENC -> Encryptor.encrypt(algorithm, input, key)
                Operation.DEC -> Decrypts.decrypt(algorithm, input, key)
            }
        } catch (e: IllegalArgumentException) {
            ret = "enter a valid operation"
        }
        return ret
    }
}
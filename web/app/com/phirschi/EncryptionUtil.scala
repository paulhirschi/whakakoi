package com.phirschi.util

import org.mindrot.jbcrypt.BCrypt

object EncryptionUtil {

   def encrypt(s: String): String = encryptGetSalt(s)._1

  /**
   * Encrypt the password and retrieve the encrypted pass and salt
   * @param pass: String
   * @return (String, String) = (encrypted password, salt)
   */

   def encryptGetSalt(pass: String): (String, String) = {
    val salt = BCrypt.gensalt
    val encrypt = BCrypt.hashpw(pass, salt)
    (encrypt, salt)
   }

   def checkPassword(pass: String, hashed: String): Boolean = BCrypt.checkpw(pass, hashed)

}
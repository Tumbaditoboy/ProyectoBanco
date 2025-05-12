package mx.itson.banco.encrypt;

import java.math.BigInteger;
import java.security.SecureRandom;
import mx.itson.banco.entities.Usuario;

/**
 * Clase que implementa cifrado ElGamal para contraseñas
 */
public class Encrypt {

    private final BigInteger p;
    private final BigInteger g;
    private final BigInteger y;
    private final BigInteger x;
    private final SecureRandom random = new SecureRandom();

    /**
     * genera las claves publicas y privadas
     * @param bitLength Longitud en bits del numero p
     */
    public Encrypt(int bitLength) {
        this.p = BigInteger.probablePrime(bitLength, random);
        this.g = new BigInteger(bitLength - 2, random).mod(p);
        this.x = new BigInteger(bitLength - 2, random);
        this.y = g.modPow(x, p);
    }

    /**
     * Cifra la contrasena de un usuario.
     *
     * @param usuario Usuario cuya contraseña se cifrara
     * @return Un arreglo de dos Strings: [a, b] en base64 string
     */
    public String[] cifrarContrasena(Usuario usuario) {
        BigInteger m = new BigInteger(usuario.getContrasena().getBytes());
        BigInteger k = new BigInteger(p.bitLength() - 2, random);
        BigInteger a = g.modPow(k, p);
        BigInteger b = y.modPow(k, p).multiply(m).mod(p);

        // Se guarda como un string
        return new String[]{a.toString(), b.toString()};
    }

    /**
     * Descifra la contrasena usando los valores a y b
     *
     * @param aStr parte a del cifrado en texto
     * @param bStr parte b del cifrado en texto
     * @return Contrasena original como texto
     */
    public String descifrarContrasena(String aStr, String bStr) {
        BigInteger a = new BigInteger(aStr);
        BigInteger b = new BigInteger(bStr);
        BigInteger s = a.modPow(x, p);
        BigInteger sInv = s.modInverse(p);
        BigInteger mensaje = b.multiply(sInv).mod(p);
        return new String(mensaje.toByteArray());
    }
}

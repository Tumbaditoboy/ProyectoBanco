/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.banco.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author zBook
 */
public class Hashed {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String encriptarContrasena (String contrasena){
        return encoder.encode(contrasena);
    }
    public static boolean compararContrasena(String contrasenaIngresada, String contrasenaCifrada) {
    return encoder.matches(contrasenaIngresada, contrasenaCifrada);
}

}

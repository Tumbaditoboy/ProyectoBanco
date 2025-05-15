/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.banco.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.banco.encrypt.Hashed;
import mx.itson.banco.entities.Usuario;
import mx.itson.banco.utils.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Akane
 */
public class UsuarioDAO {
    
    /**
     * Metodo que obtiene todos los valores en la tabla Usuario de acuerdo con el mapeo de las columnas en la clase Usuario.
     * @return Una lista llamada "Usuarios" con los valores contenidos en las columnas de la base de datos.
     */
    public static List<Usuario> getAll(){
        List<Usuario> usuarios = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Usuario> criteriaQuery = 
                    session.getCriteriaBuilder().createQuery(Usuario.class);
            criteriaQuery.from(Usuario.class);
            
            usuarios = session.createQuery(criteriaQuery).getResultList();
        }catch(Exception ex){ 
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return usuarios;
    }
    
public static Usuario getUsuarioPorCredenciales(String correo, String contrasena) {
    Usuario usuario = null;
    try {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Buscar solo por correo
        String hql = "FROM Usuario WHERE correo = :correo";
        usuario = session.createQuery(hql, Usuario.class)
                .setParameter("correo", correo)
                .uniqueResult();

        session.close();

        // Si se encontró el usuario, verificar la contraseña cifrada
        if (usuario != null) {
            boolean contrasenaValida = Hashed.compararContrasena(contrasena, usuario.getContrasena());
            if (!contrasenaValida) {
                usuario = null; // contrasena incorrecta
            }
        }

    } catch (Exception ex) {
        System.err.println("Error al obtener el usuario: " + ex.getMessage());
    }
    return usuario;
}



public static boolean save(Usuario u) {
    boolean resultado = false;
    Session session = null;

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        if (u.getId() == 0) {
            
            session.save(u);
        } else {
            
            session.update(u);
        }

        session.getTransaction().commit();
        resultado = true;

    } catch (Exception ex) {
        if (session != null && session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
        throw ex; // Para que la excepción llegue fuera del DAO
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return resultado;
}

    
}

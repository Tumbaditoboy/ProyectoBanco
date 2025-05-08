/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.banco.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import mx.itson.banco.entities.Usuario;
import mx.itson.banco.utils.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Akane
 */
public class UsuarioDAO {
    
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
}

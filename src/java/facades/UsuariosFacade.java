/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entidades.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Myriam
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "menuPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    public Usuarios login(Usuarios user) {
        Usuarios u = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.correo = :correo AND u.contrasena = :contrasena");
            query.setParameter("correo", user.getCorreo());
            query.setParameter("contrasena", user.getContrasena());
            List<Usuarios> listUser = query.getResultList();
            if (!listUser.isEmpty()) {
                u = listUser.get(0);
                return u;
            }

        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public Usuarios findCorreo(String correo) {
        Usuarios user = null;
        List<Usuarios> lista = null;

        try {

            Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.correo = :correo");

            query.setParameter("correo", correo);

            lista = query.getResultList();

            while (!lista.isEmpty()) {
                user = lista.get(0);
            }

        } catch (Exception e) {
            throw e;
        }

        return user;
    }

    public List<Usuarios> findRol(Object idRol) {
        Usuarios user = null;
        List<Usuarios> lista = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.idRol.idRol = :id_rol");
            query.setParameter("id_rol", idRol);
            lista = query.getResultList();
            

        } catch (Exception e) {
        }
        return lista;
    }

}

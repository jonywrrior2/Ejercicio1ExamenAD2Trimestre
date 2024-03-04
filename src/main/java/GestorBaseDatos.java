import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class GestorBaseDatos {

    public void insertarCliente(Cliente cliente) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data.odb");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

    public void getCliente(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data.odb");
        EntityManager em = emf.createEntityManager();

        Cliente cliente = em.find(Cliente.class, id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }

        em.close();
        emf.close();
    }

    public void listarMejoresClientes(Long cantidad) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data.odb");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.activo = true AND c.totalVentas > :cantidad", Cliente.class);
        query.setParameter("cantidad", cantidad);
        List<Cliente> clientes = query.getResultList();
        System.out.println("Mejores clientes activos");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        em.close();
        emf.close();
    }

    public void estadisticas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("data.odb");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Long> totalVentasQuery = em.createQuery("SELECT SUM(c.totalVentas) FROM Cliente c", Long.class);
        Long totalVentas = totalVentasQuery.getSingleResult();

        TypedQuery<Double> promedioVentasQuery = em.createQuery("SELECT AVG(c.totalVentas) FROM Cliente c WHERE c.activo = true", Double.class);
        Double promedioVentas = promedioVentasQuery.getSingleResult();

        TypedQuery<Long> clientesInactivosQuery = em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.activo = false AND c.totalVentas > 0", Long.class);
        Long clientesInactivosConVentas = clientesInactivosQuery.getSingleResult();

        System.out.println("Total de ventas entre todos los clientes: " + totalVentas);
        System.out.println("Promedio de ventas de los clientes activos: " + promedioVentas);
        System.out.println("Cantidad de clientes inactivos que tienen total de ventas mayor a 0: " + clientesInactivosConVentas+ " cliente/s");

        em.close();
        emf.close();
    }
}


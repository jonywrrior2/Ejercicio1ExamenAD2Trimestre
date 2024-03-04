public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Cliente 1");
        Cliente cliente2 = new Cliente("Cliente 2");
        Cliente cliente3 = new Cliente("Cliente 3");
        Cliente cliente4 = new Cliente("Cliente 4");
        Cliente cliente5 = new Cliente("Cliente 5");

        cliente1.setTotalVentas(1000L);
        cliente2.setTotalVentas(2000L);
        cliente3.setTotalVentas(3000L);
        cliente4.setTotalVentas(4000L);
        cliente5.setTotalVentas(5000L);

        cliente1.setActivo(false);
        cliente3.setActivo(false);

        GestorBaseDatos gestor = new GestorBaseDatos();
        /* gestor.insertarCliente(cliente1);
        gestor.insertarCliente(cliente2);
        gestor.insertarCliente(cliente3);
        gestor.insertarCliente(cliente4);
        gestor.insertarCliente(cliente5); */ //Comento el insertar para que solo haya 5 clientes y no se inserten 5 en cada prueba

        gestor.getCliente(1L);
        gestor.listarMejoresClientes(2500L);
        gestor.estadisticas();
    }
}

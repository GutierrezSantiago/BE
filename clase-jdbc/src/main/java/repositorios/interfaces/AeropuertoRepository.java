package repositorios.interfaces;

import entidades.Aeropuerto;

public interface AeropuertoRepository extends Repository<Aeropuerto> {
    Aeropuerto getByCodigoInternacional(String codigoInternacional);
}

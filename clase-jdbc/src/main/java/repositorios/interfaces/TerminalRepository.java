package repositorios.interfaces;

import entidades.Terminal;

import java.util.List;

public interface TerminalRepository extends Repository<Terminal> {
    List<Terminal> getByAeropuertoId(int id);
}

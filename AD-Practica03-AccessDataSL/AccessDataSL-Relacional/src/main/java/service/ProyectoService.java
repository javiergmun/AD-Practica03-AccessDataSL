package service;

import dto.ProyectoDTO;
import mapper.ProyectoMapper;
import model.Proyecto;
import repository.RepoProyecto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProyectoService extends BaseService<Proyecto, String, RepoProyecto> {

    ProyectoMapper mapper = new ProyectoMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public ProyectoService(RepoProyecto repository) {
        super(repository);
    }

    /**
     * Coger todos los proyectos
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<Optional<ProyectoDTO>>> getAllProyectos() throws SQLException {
        return mapper.toDTO(this.getAll());
    }
    /**
     * Coger todos los proyectos por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<ProyectoDTO> getProyectoById(String id) throws SQLException {
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("ProyectoService -> " +
                "No se ha encontrado el Proyecto by id");
        return Optional.empty();
    }
    /**
     * Postear un proyecto
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<ProyectoDTO> postProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        if (mapper.fromDTO(proyectoDTO).isPresent()) {
            Optional<Proyecto> res = this.save(mapper.fromDTO(proyectoDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("ProyectoService -> " +
                        "No se ha encontrado Proyecto toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("ProyectoService -> " +
                    "No se ha encontrado Proyecto fromDTO");
            return Optional.empty();
        }
    }

    /**
     * Updatear un proyecto
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<ProyectoDTO> updateProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        if (mapper.fromDTO(proyectoDTO).isPresent()) {
            Optional<Proyecto> res = this.update(mapper.fromDTO(proyectoDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("ProyectoService -> " +
                        "No se ha encontrado Proyecto toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("ProyectoService -> " +
                    "No se ha encontrado Proyecto fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Deletear un proyecto
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<ProyectoDTO> deleteProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        if (mapper.fromDTO(proyectoDTO).isPresent()) {
            Optional<Proyecto> res = this.delete(mapper.fromDTO(proyectoDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("ProyectoService -> " +
                        "No se ha encontrado Proyecto toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("ProyectoService -> " +
                    "No se ha encontrado Proyecto fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Coger los proyectos mas caros
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public List<Object> getProyectosMasCaros() throws SQLException {
        RepoProyecto repo = new RepoProyecto();
        return repo.getProyectosMasCaros().orElseThrow(()->(new SQLException("IssueService -> Error al encontrar los proyectos mas caros y los salarios")));
    }
}

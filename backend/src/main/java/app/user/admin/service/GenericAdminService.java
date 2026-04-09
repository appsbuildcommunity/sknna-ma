package app.user.admin.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.common.exception.ResourceNotFoundException;

public abstract class GenericAdminService<T, ID, ResponseDTO, UpdateDTO> {

    protected abstract JpaRepository<T, ID> getRepository();
    protected abstract ResponseDTO toResponseDTO(T entity);
    protected abstract void applyUpdate(T entity, UpdateDTO dto);
    protected abstract void deactivate(T entity);

    public List<ResponseDTO> getAll() {
        return getRepository().findAll()
                .stream().map(this::toResponseDTO).toList();
    }

    public ResponseDTO getOne(ID id) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        return toResponseDTO(entity);
    }

    public ResponseDTO update(ID id, UpdateDTO dto) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        applyUpdate(entity, dto);
        return toResponseDTO(getRepository().save(entity));
    }

    public void delete(ID id) {
        if (!getRepository().existsById(id)) 
            throw new ResourceNotFoundException("Not found: " + id);
        getRepository().deleteById(id);
    }

    public ResponseDTO deactivateEntity(ID id) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
        deactivate(entity);
        return toResponseDTO(getRepository().save(entity));
    }
}
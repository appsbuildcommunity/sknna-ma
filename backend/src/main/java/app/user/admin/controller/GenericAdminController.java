// package app.user.admin.controller;



// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import app.user.admin.service.GenericAdminService;

// public abstract class GenericAdminController<T, ID, ResponseDTO, UpdateDTO> {

//     protected abstract GenericAdminService<T, ID, ResponseDTO, UpdateDTO> getService();

//     @GetMapping
//     public ResponseEntity<List<ResponseDTO>> getAll() {
//         return ResponseEntity.ok(getService().getAll());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<ResponseDTO> getOne(@PathVariable ID id) {
//         return ResponseEntity.ok(getService().getOne(id));
//     }

//     @PatchMapping("/{id}")
//     public ResponseEntity<ResponseDTO> update(@PathVariable ID id, @RequestBody UpdateDTO dto) {
//         return ResponseEntity.ok(getService().update(id, dto));
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> delete(@PathVariable ID id) {
//         getService().delete(id);
//         return ResponseEntity.noContent().build();
//     }

//     @PatchMapping("/{id}/deactivate")
//     public ResponseEntity<ResponseDTO> deactivate(@PathVariable ID id) {
//         return ResponseEntity.ok(getService().deactivateEntity(id));
//     }
// }
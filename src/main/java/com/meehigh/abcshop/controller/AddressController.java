package com.meehigh.abcshop.controller;

import com.meehigh.abcshop.dto.AddressRequest;
import com.meehigh.abcshop.dto.AddressResponse;
import com.meehigh.abcshop.exception.AddressNotFoundException;
import com.meehigh.abcshop.exception.UserNotFoundException;
import com.meehigh.abcshop.model.Address;
import com.meehigh.abcshop.model.User;
import com.meehigh.abcshop.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponse> getAllAddress() {
        return addressService.getAllAddress();
    }

    @PostMapping
    public ResponseEntity<?> addNewAddress(@Valid @RequestBody AddressRequest addressRequest) {
        //addressService.addNewAddress(address);
        return ResponseEntity.ok(addressService.addNewAddress(addressRequest));
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable long id) {
        AddressResponse address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/getbyname/{addressName}")
    public ResponseEntity<?> getAddressByName(@PathVariable String addressName) {
        List<AddressResponse> address = addressService.getAddressByName(addressName);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/getbyuserid/{userId}")
    public ResponseEntity<?> getAddressByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(addressService.getAddressByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAddress(@PathVariable long id, @RequestBody Address updatedAddress) {
        return ResponseEntity.ok(addressService.editAddress(id, updatedAddress));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable long id) {
        return ResponseEntity.ok(addressService.deleteAddress(id));
    }
/*
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AddressNotFoundException.class)
    public ErrorResponse handleAddressNotFound(AddressNotFoundException ex){
        return new ErrorResponse(ex.getMessage());
    }

 */
}


/* //TODO - Sumar — ce trebuie să corectezi imediat în codul tău

Elimină rutele duplicate — schimbă mapping-urile pentru name / userId (ex.: /search, /user/{userId}).

Înlocuiește expunerea entităților direct cu DTO-uri.

Adaugă handler global pentru excepții (404, 400).

Returnează coduri HTTP potrivite (201 la create, 204 la delete).

Folosește @Valid pe DTO-uri și nu pe entități JPA.
*/


/*//TODO - EXPLICATII DETALIATE
GET /api/addresses/{id} — apelează service pentru a găsi adresa.

Returnează 200 OK + adresa ca JSON.

Observație: dacă addressService.getAddressById aruncă ex. EntityNotFoundException,
 ar trebui gestionat (ex. 404 NOT FOUND). De preferat Optional + mapare la 404.


//TODO - PROBLEMĂ MAJORĂ — duplicate mappings pentru GET

Următoarele două metode au exact același mapping (@GetMapping("/{...}")) — asta cauzează
 ambiguitate la runtime și aplicația nu pornește (sau mapping-urile sunt ambigue):

@GetMapping("/{addressName}")
public ResponseEntity<?> getAddressByName(@PathVariable String addressName) { ... }

@GetMapping("/{userId}")
public ResponseEntity<?> getAddressByUserId(@PathVariable long userId) { ... }


Toate trei (/{id}, /{addressName}, /{userId}) sunt aceleași pattern-uri (un path segment).
 Spring nu poate decide care metodă să folosească când vine
 GET /api/addresses/123 sau GET /api/addresses/Bucuresti.

Corecție: folosește rute distincte
 (ex.: /api/addresses/{id}, /api/addresses/search?name=..., /api/addresses/user/{userId})
  sau prefixe /by-name/{name}, /by-user/{id}.
*/

/* //TODO - Varianta corectată + recomandată (exemplu complet)
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAll() {
        List<AddressDto> list = addressService.getAll().stream().map(AddressMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable Long id) {
        return addressService.findById(id)
                .map(addr -> ResponseEntity.ok(AddressMapper.toDto(addr)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<AddressDto>> searchByName(@RequestParam("name") String name) {
        List<AddressDto> list = addressService.findByName(name).stream().map(AddressMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressDto>> getByUser(@PathVariable Long userId) {
        List<AddressDto> list = addressService.getByUserId(userId).stream().map(AddressMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@Valid @RequestBody AddressCreateDto dto, UriComponentsBuilder uriBuilder) {
        Address created = addressService.create(dto);
        URI uri = uriBuilder.path("/api/addresses/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(AddressMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @Valid @RequestBody AddressUpdateDto dto) {
        Address updated = addressService.update(id, dto);
        return ResponseEntity.ok(AddressMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
*/
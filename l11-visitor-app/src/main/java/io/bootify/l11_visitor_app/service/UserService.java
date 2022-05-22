package io.bootify.l11_visitor_app.service;

import io.bootify.l11_visitor_app.domain.Address;
import io.bootify.l11_visitor_app.domain.Flat;
import io.bootify.l11_visitor_app.domain.Role;
import io.bootify.l11_visitor_app.domain.User;
import io.bootify.l11_visitor_app.model.UserDTO;
import io.bootify.l11_visitor_app.repos.AddressRepository;
import io.bootify.l11_visitor_app.repos.FlatRepository;
import io.bootify.l11_visitor_app.repos.RoleRepository;
import io.bootify.l11_visitor_app.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final FlatRepository flatRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;

    public UserService(final UserRepository userRepository, final FlatRepository flatRepository,
            final AddressRepository addressRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.flatRepository = flatRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setFlat(user.getFlat() == null ? null : user.getFlat().getId());
        userDTO.setAddress(user.getAddress() == null ? null : user.getAddress().getId());
        userDTO.setRoleId(user.getRoleId() == null ? null : user.getRoleId().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        if (userDTO.getFlat() != null && (user.getFlat() == null || !user.getFlat().getId().equals(userDTO.getFlat()))) {
            final Flat flat = flatRepository.findById(userDTO.getFlat())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "flat not found"));
            user.setFlat(flat);
        }
        if (userDTO.getAddress() != null && (user.getAddress() == null || !user.getAddress().getId().equals(userDTO.getAddress()))) {
            final Address address = addressRepository.findById(userDTO.getAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
            user.setAddress(address);
        }
        if (userDTO.getRoleId() != null && (user.getRoleId() == null || !user.getRoleId().getId().equals(userDTO.getRoleId()))) {
            final Role roleId = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "roleId not found"));
            user.setRoleId(roleId);
        }
        return user;
    }

}

/**
 *
 */
package com.poc.dellnxppoc.emgmt.service;

import java.util.List;
import java.util.Optional;

import com.poc.dellnxppoc.emgmt.common.RoleEnum;
import com.poc.dellnxppoc.emgmt.entity.Role;
import com.poc.dellnxppoc.emgmt.entity.User;
import com.poc.dellnxppoc.emgmt.exception.NoDataFoundException;
import com.poc.dellnxppoc.emgmt.exception.UserAlreadyExistException;
import com.poc.dellnxppoc.emgmt.exception.UndefinedRoleException;
import com.poc.dellnxppoc.emgmt.mapper.UserMapper;
import com.poc.dellnxppoc.emgmt.model.UserDTO;
import com.poc.dellnxppoc.emgmt.repository.RoleRepository;
import com.poc.dellnxppoc.emgmt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class OrganizerServiceImpl implements OrganizerService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserMapper mapper;

    private final
    UserRepository repository;

    private final
    RoleRepository roleRepository;

    public OrganizerServiceImpl(UserMapper mapper, UserRepository repository, RoleRepository roleRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDTO> fetchAllOrganizers() {
        List<User> entities = repository.findAllByRoleId(RoleEnum.ORGANIZER);

        log.info("DB operation success! Fetched {} Organizers!", entities.size());
        return mapper.entityToDTO(entities);
    }

    @Override
    public UserDTO findOrganizerByID(final Integer ID) {
        Optional<User> optional = repository.findById(ID);
        return optional.map(entity -> {
            log.info("DB operation success! Fetched User:{} ", entity.getUserId());
            return mapper.entityToDTO(entity);
        }).orElseThrow(NoDataFoundException::new);
    }

    @Override
    public UserDTO findOrganizerByUserName(final String USERNAME) {
        Optional<User> optional = repository.findByUserName(USERNAME);
        return optional.map(entity -> {
            log.info("DB operation success! Fetched User:{} by username: {}", entity.getUserId(), USERNAME);
            return mapper.entityToDTO(entity);
        }).orElseThrow(NoDataFoundException::new);
    }

    @Override
    public UserDTO addOrganizer(final UserDTO dto) {
		Optional<User> user = repository.findByUserName(dto.getUserName());
        if (user.isPresent()) {
            throw new UserAlreadyExistException();
        }

		Role role = roleRepository.findById(RoleEnum.ORGANIZER)
				.orElseThrow(UndefinedRoleException::new);

		User entity = mapper.prepareForCreate(dto);
        entity.setRole(role);

        entity = repository.save(entity);
        log.info("DB operation success! Added User : {}", entity.getUserId());
        return mapper.entityToDTO(entity);
    }

    @Override
    public UserDTO updateOrganizer(final UserDTO dto) {
		User user = repository.findById(dto.getUserId())
				.map(e -> mapper.prepareForUpdate(e, dto))
				.orElseThrow(NoDataFoundException::new);

		user = repository.save(user);
		log.info("DB operation success! Updated Organizer : {}", user.getUserId());
		return mapper.entityToDTO(user);
    }

    @Override
    public boolean deleteOrganizerByID(final Integer ID) {
        boolean exist = repository.existsById(ID);
        if (!exist) throw new NoDataFoundException();

        repository.deleteById(ID);

        exist = repository.existsById(ID);
        log.info("DB operation success! Deleted the Organizer : {}", !exist);
        return !exist;
    }

}

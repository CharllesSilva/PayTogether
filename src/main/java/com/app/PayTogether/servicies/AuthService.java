package com.app.PayTogether.servicies;

import com.app.PayTogether.dto.RegisterRequestDTO;
import com.app.PayTogether.entity.Group;
import com.app.PayTogether.entity.User;
import com.app.PayTogether.entity.UserGroup;
import com.app.PayTogether.exception.EmailAlreadyExistsException;
import com.app.PayTogether.exception.GroupAssociationException;
import com.app.PayTogether.exception.InvalidCredentialsException;
import com.app.PayTogether.repositories.GroupRepository;
import com.app.PayTogether.repositories.UserGroupRepository;
import com.app.PayTogether.repositories.UserRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class AuthService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(GroupRepository groupRepository, UserRepository userRepository, UserGroupRepository userGroupRepository, PasswordEncoder passwordEncoder) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void authenticate(String email, String rawPassword) {
        User user = userRepository.findBasicUserByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Usuário não encontrado"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new InvalidCredentialsException("Senha incorreta");
        }

    }

    @Transactional
    public void register(RegisterRequestDTO request) {
        validateEmailUniqueness(request.getEmail());

        User user = createAndSaveUser(request);
        Long groupId = createAndSaveDefaultGroupFor(user);
        associateUserToGroup(user.getId(), groupId);
    }

    private void validateEmailUniqueness(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("Email ja cadastrado");
        }
    }

    private User createAndSaveUser(RegisterRequestDTO request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    private Long createAndSaveDefaultGroupFor(User user) {
        Group group = new Group();
        group.setName(user.getUsername());

        return groupRepository.save(group);
    }

    public void associateUserToGroup(Long userId, Long groupId) {
        try {
            UserGroup membership = new UserGroup();
            membership.setUserId(userId);
            membership.setGroupId(groupId);
            userGroupRepository.save(membership);
        } catch (DataAccessException e) {
            throw new GroupAssociationException("Falha ao associar usuário ao grupo", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresClienteComponent;
import com.eep.hospital.dto.ClienteDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.PerfilUsuario;
import com.eep.hospital.entity.UsuarioEntity;
import com.eep.hospital.repositorio.ClienteJpaRepository;
import com.eep.hospital.repositorio.UsuarioJpaRepository;
import com.eep.hospital.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("clienteServiceImpl")
public class ClienteServiceImpl implements ClienteService {


    @Autowired
    @Qualifier("clienteJpaRepository")
    private ClienteJpaRepository clienteJpaRepository;

    @Autowired
    @Qualifier("usuarioJpaRepository")
    private UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    @Qualifier("conversoresClienteComponent")
    ConversoresClienteComponent conversoresClienteComponent;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ClienteEntity guardar(ClienteDto clienteDto) {

        // Cifrar la contraseña antes de guardarla
        clienteDto.setPassword(passwordEncoder.encode(clienteDto.getPassword()));

        usuarioJpaRepository.save(new UsuarioEntity(clienteDto.getCorreo(),clienteDto.getPassword(), new PerfilUsuario("estandar")));

        return clienteJpaRepository.save(
                conversoresClienteComponent.convertirClienteAEntidad(clienteDto)
        );
    }
}

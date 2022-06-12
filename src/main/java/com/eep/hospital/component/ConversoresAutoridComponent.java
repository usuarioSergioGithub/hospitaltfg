package com.eep.hospital.component;

import com.eep.hospital.entity.PerfilUsuario;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component("conversoresAutoridComponent")
@NoArgsConstructor
public class ConversoresAutoridComponent {


    // Conversor de roles de usuario (como cadenas de texto) a autoridades que tomará spring security para el acceso a diversas partes de la web
    public Collection<? extends GrantedAuthority> mapearAutoridades(PerfilUsuario rol) {
        return Collections.singleton(new SimpleGrantedAuthority(rol.getRol()));

    }

}
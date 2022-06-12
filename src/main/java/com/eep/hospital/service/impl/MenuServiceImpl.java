package com.eep.hospital.service.impl;

import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.service.MenuService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

    @Override
    public MenuDto reiniciarElementos(){

        return new MenuDto();
    }

}

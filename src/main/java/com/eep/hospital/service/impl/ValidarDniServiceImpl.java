package com.eep.hospital.service.impl;


import com.eep.hospital.service.ValidarDniService;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service("validarDniServiceImpl")
public class ValidarDniServiceImpl implements ValidarDniService {

    @Override
    public boolean CalcularLetra(String dniIntroducido) {
        char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};

        // Numeros del DNI introducidos
        String numeros=dniIntroducido.substring(0,8);

        //Letra introducida
        char letra = dniIntroducido.charAt(dniIntroducido.length()-1);

        int indiceLetraValida= Integer.parseInt(numeros)%23;

        // Letra valida
        if(letras[indiceLetraValida]==letra) {
            return true;
        }else {
            return false;
        }
    }
}

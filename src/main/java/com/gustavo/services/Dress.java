package com.gustavo.services;

import com.gustavo.GettingDressed;
import org.springframework.stereotype.Service;

@Service
public class Dress implements GettingDressedService {


    Dress(){}

    @Override
    public String gettingDressed(String temperature, String commands) {
        return GettingDressed.dress(temperature, commands);
    }
}

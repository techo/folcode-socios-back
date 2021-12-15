package com.folcode.techoapi.excel.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.folcode.techoapi.excel.ExcelHelper.ExcelHelper;
import com.folcode.techoapi.models.entities.SocioEntity;
import com.folcode.techoapi.models.entities.repositories.SocioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ExcelService {
    @Autowired
    SocioRepository socioRepository;

    public void save(MultipartFile file) {

        try {


            List<SocioEntity> socios = ExcelHelper.excelToSocios(file.getInputStream());
            socioRepository.saveAll(socios);

        } catch (IOException e) {

            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<SocioEntity> socios = socioRepository.findAll();

        ByteArrayInputStream in = ExcelHelper.sociosToExcel(socios);
        return in;
    }

    public List<SocioEntity> getAllTutorials() {
        return socioRepository.findAll();
    }
}

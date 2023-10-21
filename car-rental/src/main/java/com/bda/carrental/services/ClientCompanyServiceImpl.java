package com.bda.carrental.services;

import com.bda.carrental.entidades.ClientCompany;
import com.bda.carrental.entidades.dto.ClientCompanyDto;
import com.bda.carrental.repositories.ClientCompanyRepository;
import com.bda.carrental.services.mappers.ClientCompanyDtoMapper;
import com.bda.carrental.services.mappers.ClientCompanyMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ClientCompanyServiceImpl implements ClientCompanyService {

    private final ClientCompanyRepository clientCompanyRepository;
    private final ClientCompanyDtoMapper dtoMapper;
    private final ClientCompanyMapper entityMapper;

    public ClientCompanyServiceImpl(ClientCompanyRepository clientCompanyRepository,
                                    ClientCompanyDtoMapper mapper,
                                    ClientCompanyMapper entityMapper) {
        this.clientCompanyRepository = clientCompanyRepository;
        this.dtoMapper = mapper;
        this.entityMapper = entityMapper;
    }

    @Override
    public ClientCompanyDto add(ClientCompanyDto entity) {
        Optional<ClientCompany> company = Stream.of(entity).map(entityMapper).findFirst();
        try {

            this.clientCompanyRepository.save(company.get());


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return company.map(dtoMapper).orElseThrow();
    }

    @Override
    public ClientCompanyDto update(ClientCompanyDto entity) {
        Optional<ClientCompany> company = Stream.of(entity).map(entityMapper).findFirst();
        company.ifPresent(clientCompanyRepository::save);
        return company.map(dtoMapper).orElseThrow();
    }

    @Override
    public ClientCompanyDto delete(Long id) {
        ClientCompanyDto company = this.getById(id);
        if (company != null) {
            Optional<ClientCompany> entity = Stream.of(company).map(entityMapper).findFirst();
            entity.ifPresent(clientCompanyRepository::delete);
        }
        return company;
    }

    @Override
    public ClientCompanyDto getById(Long id) {
        Optional<ClientCompany> company = this.clientCompanyRepository.findById(id);
        return company.map(dtoMapper).orElseThrow();
    }

    @Override
    public List<ClientCompanyDto> getAll() {
        List<ClientCompany> companies = this.clientCompanyRepository.findAll();
        return companies
                .stream()
                .map(dtoMapper)
                .toList();
    }
}

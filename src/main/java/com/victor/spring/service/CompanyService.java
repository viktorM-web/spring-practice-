package com.victor.spring.service;

import com.victor.spring.database.repository.CompanyRepository;
import com.victor.spring.dto.CompanyReadDto;
import com.victor.spring.listner.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.victor.spring.listner.entity.AccessType.READ;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity ->{
                    eventPublisher.publishEvent(new EntityEvent(entity, READ));
                    return new CompanyReadDto(entity.getId());
                });
    }
}

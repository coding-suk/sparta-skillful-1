package com.web.startspring.service.manager;

import com.web.startspring.dto.manager.ManagerRequestDto;
import com.web.startspring.entity.Manager;
import com.web.startspring.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService{

    private final ManagerRepository managerRepository;

    @Override
    @Transactional
    public Manager createManager(ManagerRequestDto managerRequestDto) {
        Manager manager = Manager.builder()
                .name(managerRequestDto.getName())
                .email(managerRequestDto.getEmail())
                .uuid(managerRequestDto.getUuid())
                .build();

        return managerRepository.createManager(manager);
    }

}

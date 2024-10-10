package com.web.startspring.service.manager;

import com.web.startspring.dto.manager.ManagerRequestDto;
import com.web.startspring.entity.Manager;

public interface ManagerService {

    //등록
    Manager createManager(ManagerRequestDto managerRequestDto);

}

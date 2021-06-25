package com.ruslanshakirov.crm.service;

import com.ruslanshakirov.crm.entity.BusinessProfile;
import com.ruslanshakirov.crm.repository.BusinessProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class BusinessProfileService extends BaseServiceImpl<BusinessProfile, BusinessProfileRepository> {
    public BusinessProfileService(BusinessProfileRepository repository) {
        super(repository);
    }
}

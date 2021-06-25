package com.ruslanshakirov.crm.controller;

import com.ruslanshakirov.crm.entity.BusinessProfile;
import com.ruslanshakirov.crm.service.BusinessProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business-profile")
public class BusinessProfileController extends BaseController<BusinessProfile, BusinessProfileService> {
    public BusinessProfileController(BusinessProfileService profileService) {
        super(profileService);
    }
}

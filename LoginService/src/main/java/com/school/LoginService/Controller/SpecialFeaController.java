package com.school.LoginService.Controller;

import com.school.LoginService.Model.Features;
import com.school.LoginService.Service.SpecialFeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fea")
@RestController
public class SpecialFeaController {

    @Autowired
    private SpecialFeaService specialFeaService;

    @PostMapping("/addFeaByPlanId/{planId}")
    public ResponseEntity<?> addService(@PathVariable int planId,@RequestBody Features features)
    {
        return specialFeaService.saveService(planId,features);

    }



    @GetMapping("/getAllFeatures")
    public ResponseEntity<?> getAllService()
    {
        return specialFeaService.getAllService();

    }

    @GetMapping("/getAllFeaByPlanId")
    public ResponseEntity<?> getAllfeaByPlanId(@RequestParam int planId)
    {
        return specialFeaService.getAllfeaByPlanId(planId);

    }


    @GetMapping("/getByFeaId")
    public ResponseEntity<?> getById(@RequestParam int serviceId)
    {
        return specialFeaService.getById(serviceId);

    }

    @PutMapping("/editByfeaId/{id}")
    public ResponseEntity<?> editById(@PathVariable int id,@RequestBody Features service)
    {
        return specialFeaService.editById(id,service);

    }


    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int serviceId )
    {
        return specialFeaService.deleteById(serviceId);

    }



}

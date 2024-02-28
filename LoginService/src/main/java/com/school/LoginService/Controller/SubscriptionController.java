package com.school.LoginService.Controller;

import com.school.LoginService.Model.Features;
import com.school.LoginService.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/subs")
@RestController
public class SubscriptionController {


    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/getAllSubs")
    public ResponseEntity<?> getAllSubs()
    {
        return subscriptionService.getAllSubs();

    }

    @GetMapping("/getBySubsId")
    public ResponseEntity<?> getBySubsId(@PathVariable int id)
    {
        return subscriptionService.getById(id);

    }

    @PutMapping("/editBySubsId/{id}")
    public ResponseEntity<?> editById(@PathVariable int id,
                                      @RequestParam(required = false) Double price,
                                      @RequestParam(required = false) int planId,
                                      @RequestParam(required = false) String phone,
                                      @RequestParam(required = false) boolean status)
    {
        return subscriptionService.editSubsById(id,price,planId,phone,status);

    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id)
    {
        return subscriptionService.deleteSubsById(id);

    }





}

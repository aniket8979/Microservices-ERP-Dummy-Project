package com.school.LoginService.Controller;


import com.school.LoginService.Exception.ResponseClass;
import com.school.LoginService.Model.RequestModel;
import com.school.LoginService.Repo.RequestsRepo;
import com.school.LoginService.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestsRepo requestsRepo;


    @PostMapping("/my-request")
    public ResponseEntity<?> myNewRequest(@RequestBody RequestModel requestData)
    {
        RequestModel newRequest = new RequestModel();

        newRequest.setReqId(requestData.getReqId());
        newRequest.setReqEmail(requestData.getReqEmail());
        newRequest.setReqPhone(requestData.getReqPhone());
        newRequest.setReqMsg(requestData.getReqMsg());
        newRequest.setReqDate(requestData.getReqDate());
        newRequest.setReqDesc(requestData.getReqDesc());
        requestsRepo.save(newRequest);
        return ResponseClass.responseSuccess("new request added");
    }

    @PutMapping("/update")
    public ResponseEntity<?> udpateRequest(
            @RequestParam String reqStatus,
            @RequestParam String reqMsg,
            @RequestParam String reqPhone,
            @RequestParam String reqId
        )
    {
        RequestModel updated = requestsRepo.findByReqId(reqId);
        updated.setReqStatus(reqStatus);
        updated.setReqMsg(reqMsg);
        updated.setReqStatus(reqPhone);
        requestsRepo.save(updated);
        return ResponseClass.responseSuccess("request data updated");

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRequest(
            @RequestParam String reqId
    )
    {
        RequestModel delete = requestsRepo.findByReqId(reqId);
        requestsRepo.delete(delete);
        return ResponseClass.responseSuccess("request deleted");
    }






}

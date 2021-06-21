package producertask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private ProducerTaskApplication.PubsubOutboundGateway messagingGateway;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
        value = "/task",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String create(@RequestBody Task request){
        messagingGateway.sendToPubsub(request.getName());
        return "OK";
    }

}

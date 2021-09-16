package baemin.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="order", url="http://localhost:8081")
public interface OrderService {

    @RequestMapping(method= RequestMethod.GET, path="/orders/getStock/{Id}")
    public void responseStock(@PathVariable Long Id);

}


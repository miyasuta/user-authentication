package customer.service_consumer.handlers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.Result;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.cds.CdsReadEventContext;

import cds.gen.consumerservice.ConsumerService_;
import cds.gen.consumerservice.MyCustomers_;
import cds.gen.customerservice.CustomerService_;

@Component
@ServiceName(ConsumerService_.CDS_NAME)
public class ConsumerServiceHandler implements EventHandler {

    private final CqnService customerService;

    public ConsumerServiceHandler(@Qualifier(CustomerService_.CDS_NAME) CqnService customerService) {
        this.customerService = customerService;
    }

    @On(entity = MyCustomers_.CDS_NAME)
    public void onReadMyCustomers(CdsReadEventContext context) {
        // Delegate the query to the remote CustomerService
        Result result = customerService.run(context.getCqn());
        context.setResult(result);
    }

}

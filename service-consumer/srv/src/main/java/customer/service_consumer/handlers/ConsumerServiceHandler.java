package customer.service_consumer.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sap.cds.Result;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cloud.security.token.SecurityContext;
import com.sap.cloud.security.token.Token;

import cds.gen.consumerservice.ConsumerService_;
import cds.gen.consumerservice.MyCustomers_;
import cds.gen.customerservice.CustomerService_;

@Component
@ServiceName(ConsumerService_.CDS_NAME)
public class ConsumerServiceHandler implements EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceHandler.class);

    private final CqnService customerService;

    public ConsumerServiceHandler(@Qualifier(CustomerService_.CDS_NAME) CqnService customerService) {
        this.customerService = customerService;
    }

    @On(entity = MyCustomers_.CDS_NAME)
    public void onReadMyCustomers(CdsReadEventContext context) {
        // Log token information
        Token token = SecurityContext.getToken();
        if (token != null) {
            logger.info("=== Token Information ===");
            logger.info("Token Claims: {}", token.getClaims());
            logger.info("Client ID: {}", token.getClientId());
            logger.info("User Name: {}", token.getPrincipal() != null ? token.getPrincipal().getName() : "N/A");
            logger.info("Grant Type: {}", token.getGrantType());

            // Log scope claim specifically
            if (token.hasClaim("scope")) {
                logger.info("Scopes: {}", token.getClaimAsString("scope"));
            }

            // Log app identifier
            if (token.hasClaim("azp")) {
                logger.info("Authorized Party (azp): {}", token.getClaimAsString("azp"));
            }

            logger.info("========================");
        } else {
            logger.warn("No token found in SecurityContext");
        }

        // Delegate the query to the remote CustomerService
        Result result = customerService.run(context.getCqn());
        context.setResult(result);
    }

}

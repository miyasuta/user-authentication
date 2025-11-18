using { CustomerService as remote } from './external/CustomerService';

service ConsumerService {
    @readonly
    entity MyCustomers as projection on remote.Customers;
}
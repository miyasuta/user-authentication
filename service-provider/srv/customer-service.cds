using { provider as db } from '../db/schema';

service CustomerService {
    entity Customers as projection on db.Customers;
}
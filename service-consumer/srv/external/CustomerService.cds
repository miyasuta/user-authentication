/* checksum : 43b3f981cd645096241440e4cda7fbae */
@cds.external : true
service CustomerService {
  @cds.external : true
  @cds.persistence.skip : true
  entity Customers {
    @Core.ComputedDefaultValue : true
    key ID : UUID not null;
    name : String(100);
  };
};


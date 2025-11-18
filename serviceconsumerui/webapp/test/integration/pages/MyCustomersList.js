sap.ui.define(['sap/fe/test/ListReport'], function(ListReport) {
    'use strict';

    var CustomPageDefinitions = {
        actions: {},
        assertions: {}
    };

    return new ListReport(
        {
            appId: 'ns.serviceconsumerui',
            componentId: 'MyCustomersList',
            contextPath: '/MyCustomers'
        },
        CustomPageDefinitions
    );
});
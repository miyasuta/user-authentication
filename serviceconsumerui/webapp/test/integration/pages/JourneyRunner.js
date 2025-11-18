sap.ui.define([
    "sap/fe/test/JourneyRunner",
	"ns/serviceconsumerui/test/integration/pages/MyCustomersList",
	"ns/serviceconsumerui/test/integration/pages/MyCustomersObjectPage"
], function (JourneyRunner, MyCustomersList, MyCustomersObjectPage) {
    'use strict';

    var runner = new JourneyRunner({
        launchUrl: sap.ui.require.toUrl('ns/serviceconsumerui') + '/test/flp.html#app-preview',
        pages: {
			onTheMyCustomersList: MyCustomersList,
			onTheMyCustomersObjectPage: MyCustomersObjectPage
        },
        async: true
    });

    return runner;
});


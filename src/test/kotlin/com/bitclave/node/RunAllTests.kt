package com.bitclave.node

import com.bitclave.node.account.AccountServiceTest
import com.bitclave.node.account.AuthControllerTest
import com.bitclave.node.clientData.ClientProfileControllerTest
import com.bitclave.node.clientData.ClientProfileServiceTest
import com.bitclave.node.requestData.RequestDataControllerTest
import com.bitclave.node.requestData.RequestDataServiceTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(value = [
    AuthControllerTest::class,
    AccountServiceTest::class,
    ClientProfileControllerTest::class,
    ClientProfileServiceTest::class,
    RequestDataControllerTest::class,
    RequestDataServiceTest::class
])
class RunAllTests
package com.example.spocktest

import com.example.spocktest.Controller.WebController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
@SpringBootTest
class SampleSpec extends Specification{

    @Autowired
    private WebController webController;

    def "assert bean creation"(){
        expect:"bean creation is successful"
        webController == null
    }
}

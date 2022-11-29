package com.example.spocktest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import spock.lang.Specification

@WebMvcTest()
class SampleMVCSpec extends Specification{

    @Autowired
    private MockMvc mvcController;

    def "/hello returns hello world!"(){
        expect:
        mvcController.perform(MockMvcRequestBuilders.get("/hello"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().response.contentAsString.toLowerCase() == "hello world!"
    }
}

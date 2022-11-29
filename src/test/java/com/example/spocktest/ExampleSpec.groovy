package com.example.spocktest

import com.example.spocktest.model.Palatte
import com.example.spocktest.model.Polygon
import com.example.spocktest.model.Renderer
import com.example.spocktest.model.ShapeFactory
import com.example.spocktest.model.TooFewSidesException
import spock.lang.Specification
import spock.lang.Subject

import java.awt.Color


class ExampleSpec extends Specification{

    @Subject
    private Polygon polygon = new Polygon(4)

    void setupSpec(){

    }

    void setup(){

    }
   def "one equals one"(){
       expect:
       1==1
   }

    def "demo when given then"(){
        given:
        def polygon = new Polygon(4)

        when:
        int sides = polygon.numberOfSides

        then:
        sides==4

    }
    def "should expect exceptions"(){
        when:
        new Polygon(0)
        then:
        thrown(TooFewSidesException)
    }

    def "should expect exceptions where invalid inputs are given"(){
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides"(){
        when:
        def polygon = new Polygon(sides)

        then:
        polygon.numberOfSides == sides

        where:
        sides << [4,-1,6,7]
    }
    def "when inline variable is used, should be able to create a polygon with #sides sides"(){
        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [4,-1,6,7]
    }

    def "should use data tables for calculating max"(){
        expect:
        Math.max(a,b) == max
        where:
        a | b | max
        1 | 3 | 3
        2 | 6 | 6
    }

    def "should able to mock a concrete class"(){
        given:
        Renderer renderer = Mock()
        @Subject
        def polygon = new Polygon(4, renderer)
        when:
        polygon.draw()
        then:
        4* renderer.drawLine()
    }

    def "should be able to create a stub"(){
        given:
        Palatte palatte = Stub()
        palatte.getPrimaryColor() >> Color.RED
        @Subject
        def renderer = new Renderer(palatte)

        expect:
        renderer.foreGroundColor == Color.RED
    }

    def "should use a helper method"(){
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        checkDefaultPolygon(polygon, renderer)
    }

    private void checkDefaultPolygon(Polygon polygon, Renderer renderer){
        assert polygon.numberOfSides == 4
        assert polygon.renderer == renderer
    }
    def "with: should use a helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        //when with is used if one fails whole test gets failed and it displays only one testcase
        with(polygon) {
            numberOfSides == 4
            renderer == renderer
        }

    }

    def "verifyall: demo"(){

        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        //when verifyall is used it displays all testcases which are failed
        verifyAll (polygon) {
            numberOfSides == 4
            renderer == renderer
        }
    }

    void cleanSpec(){

    }
    void clean(){

    }

}

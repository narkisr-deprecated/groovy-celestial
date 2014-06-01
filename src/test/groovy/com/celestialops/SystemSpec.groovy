package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class SystemSpec extends Specification {

  def system = new Systems()
  def type = new Types()
  def fixtures = new Fixtures()

  def 'System creation'(){
    when: 
      fixtures.lazyTypeCreate()
      def id = system.create(new Fixtures().dockerSystem).id 
    then:
      system.get(id).owner == 'admin'
    cleanup: 
      system.delete(id)
  }

  def 'System deletion'(){
    when: 
      fixtures.lazyTypeCreate() 
      def id = system.create(new Fixtures().dockerSystem).id 
      system.delete(id)
    then:
      system.get(id) == [:]
  }

  def 'System query'(){
    when: 
      fixtures.lazyTypeCreate() 
      def docker = new Fixtures().dockerSystem
      docker.machine.hostname = 'testme'
      def id = system.create(docker).id 
	def systems = system.query(1, 5, [must:[term:['machine.hostname':'testme']]])
    then:
	systems.systems.collect{it[0]}[0] == id.toString()
    cleanup: 
      system.delete(id)
  }
}

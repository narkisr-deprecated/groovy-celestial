package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class SystemSpec extends Specification {

  def system = new Systems()
  def type = new Types()

  def 'System creation'(){
    when: 
      type.create(new Fixtures().redisType)
      def id = system.create(new Fixtures().dockerSystem).json.id 
    then:
      system.get(id).json.owner == 'admin'
    cleanup: 
      system.delete(id)
  }

  def 'System deletion'(){
    when: 
      def id = system.create(new Fixtures().dockerSystem).json.id 
      system.delete(id)
    then:
      system.get(id).json == [:]
      type.delete('redis')
  }
}

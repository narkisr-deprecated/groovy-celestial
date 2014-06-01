package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*
import spock.lang.Shared


class TypeSpec  extends Specification {

  def @Shared type = new Types()

  def 'Type creation'(){
     when: 
       type.delete('redis')
	 type.create(new Fixtures().redisType)
     then:
       type.get('redis').type == 'redis'
     cleanup:
       type.delete('redis')
  }

  def 'Type deletion'(){
    when: 
	type.create(new Fixtures().redisType)
	type.delete('redis')
    then:
	type.get('redis') == [:]
  }
}

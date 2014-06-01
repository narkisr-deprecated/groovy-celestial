package com.celestialops

import groovy.json.JsonSlurper
import com.celestialops.client.*

class Fixtures {

  def dockerSystem =  new JsonSlurper().parse(new File('src/test/resources/docker-system.json'))

  def redisType = new JsonSlurper().parse(new File('src/test/resources/redis.json'))

  def lazyTypeCreate = {
    def type = new Types()
    if(type.get('redis') == [:]){
	type.create(new Fixtures().redisType)
    }
  }


}

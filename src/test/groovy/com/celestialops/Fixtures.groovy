package com.celestialops

import groovy.json.JsonSlurper

class Fixtures {

  def dockerSystem =  new JsonSlurper().parse(new File('src/test/resources/docker-system.json'))

  def redisType = new JsonSlurper().parse(new File('src/test/resources/redis.json'))

}

package com.celestialops

import spock.lang.Specification
import com.celestialops.client.*


class SystemSpec  extends Specification {

  def system = new Systems()

  def dockerSystem() {
    return [description: '', type: 'redis', owner: 'admin', env: 'dev',
      machine: [
       hostname: 'foobar',
       cpus: 4,
       memory: 512
      ],
     docker: [
      node: 'local', image: 'narkisr:latest',
      volumes: [ '/tmp:/tmp' ],
      'exposed-ports': [ '22/tcp' ],
      'port-bindings': [ '22/tcp:2221/0.0.0.0' ]
     ]
   ]
  }

  def 'System creation'(){
    when: 
      def id = system.create(dockerSystem()).json.id 
    then:
      system.get(id).json.owner == 'admin'
    cleanup: 
      system.delete(id)
  }

  def 'System deletion'(){
    when: 
      def id = system.create(dockerSystem()).json.id 
      system.delete(id)
    then:
      system.get(id).json == [:]
  }
}

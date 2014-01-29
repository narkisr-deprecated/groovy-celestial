package com.celestialops

class Fixtures {
  def dockerSystem = 
    [description: '', type: 'redis', owner: 'admin', env: 'dev',
     machine: [ hostname: 'foobar', cpus: 4, memory: 512],
     docker: [
      node: 'local', image: 'narkisr:latest',
      volumes: [ '/tmp:/tmp' ],
      'exposed-ports': [ '22/tcp' ],
      'port-bindings': [ '22/tcp:2221/0.0.0.0' ]
     ]
   ]

}
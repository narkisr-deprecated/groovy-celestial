package com.celestialops.client

import wslite.rest.*
import wslite.http.auth.*

class Serviceable {

  @Delegate RESTClient client = new RESTClient()

  def Serviceable(){
   def config = readConfig().celestial
   client.with {
     url = config.url
     httpClient.sslTrustAllCerts = true
     authorization = new HTTPBasicAuthorization(config.user, config.password)
     defaultAcceptHeader = 'application/json'
   }
  }

 def readConfig() {
  def url = this.class.getClassLoader().getResource("com/celestialops/client/Config.groovy");
  new ConfigSlurper().parse(url);
 }
}

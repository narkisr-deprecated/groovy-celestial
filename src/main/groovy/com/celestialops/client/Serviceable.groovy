package com.celestialops.client

import wslite.rest.*
import wslite.http.auth.*

class Serviceable {

  @Delegate RESTClient client = new RESTClient('https://localhost:8443/')

  def Serviceable(){
   client.with {
     httpClient.sslTrustAllCerts = true
     authorization = new HTTPBasicAuthorization("admin", "changeme")
     defaultAcceptHeader = 'application/json'
   }
  }
}
